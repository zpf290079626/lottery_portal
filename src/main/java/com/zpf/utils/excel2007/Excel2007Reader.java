package com.zpf.utils.excel2007;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author zhangpengfei 抽象Excel2007读取器，excel2007的底层数据结构是xml文件，采用SAX的事件驱动的方法解析
 *         xml，需要继承DefaultHandler，在遇到文件内容时，事件会触发，这种做法可以大大降低 内存的耗费，特别使用于大数据量的文件。
 * 
 */
public class Excel2007Reader extends DefaultHandler {

    private static final String SHEET_PRELUDE = "rId";
    // 共享字符串表
    private SharedStringsTable sst;
    // 上一次的内容
    private String lastContents;
    private boolean nextIsString;

    private int sheetIndex = -1;
    private Map<Integer, String> rowDataMap = new HashMap<Integer, String>();
    // 当前行
    private int curRow = 0;
    // 当前列
    private int curCol = 0;
    // 日期标志
    private boolean dateFlag;

    private IRowReader rowReader;

    public void setRowReader(IRowReader rowReader) {
        this.rowReader = rowReader;
    }

    /**
     * 只遍历一个电子表格，其中sheetId为要遍历的sheet索引，从1开始，1-3......
     * 如果sheetId不大于0 则获取第一个sheet页
     * @param
     * @param sheetId
     * @throws Exception
     */
    public void processOneSheet(String filePath, int sheetId) throws Exception {
        OPCPackage pkg = OPCPackage.open(filePath);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);
        InputStream sheet2 = null;
        // 根据 rId# 或 rSheet# 查找sheet
        if (sheetId > 0) {
            sheet2 = r.getSheet(SHEET_PRELUDE + sheetId);
        } else {
            Iterator<InputStream> sheets = r.getSheetsData();
            sheet2 = sheets.next();
        }
        
        sheetIndex++;
        InputSource sheetSource = new InputSource(sheet2);
        parser.parse(sheetSource);
        sheet2.close();
    }

    /**
     * 遍历工作簿中所有的电子表格
     * 
     * @param
     * @throws Exception
     */
    public void process(String filePath) throws Exception {
        OPCPackage pkg = OPCPackage.open(filePath);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);
        Iterator<InputStream> sheets = r.getSheetsData();
        while (sheets.hasNext()) {
            curRow = 0;
            sheetIndex++;
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
        }
    }

    public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        this.sst = sst;
        parser.setContentHandler(this);
        return parser;
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {

        // c => 单元格
        if ("c".equals(name)) {
            // 如果下一个元素是 SST 的索引，则将nextIsString标记为true
            String cellType = attributes.getValue("t"); // t=s表示这个单元格有值，里面的v标签即为值的id，id对应到sharedstring.xm里的id对应的值
            if ("s".equals(cellType)) {
                nextIsString = true;
            }
            else {
                nextIsString = false;
            }
            // 日期格式
            String cellDateType = attributes.getValue("s");
            if ("1".equals(cellDateType)) { // 1：日期格式；2：数值类型
                dateFlag = true;
            }
            else {
                dateFlag = false;
            }

            // 获得当前列的列号
            curCol = this.strToNum(attributes.getValue("r").replaceAll("\\d", ""));
        } else if ("row".equals(name)) {
            curRow = Integer.parseInt(attributes.getValue("r"));
        }

        // 置空
        lastContents = "";
    }

    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {

        // 根据SST的索引值的到单元格的真正要存储的字符串
        // 这时characters()方法可能会被调用多次
        if (nextIsString) {
            try {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
            }
            catch (Exception e) {

            }
        }
        if ("v".equals(name) || "t".equals(name)) { // t元素也有可能存放数值
            String value = lastContents.trim();
            // 日期格式处理
            if (dateFlag) { // 2003版本excel转2007版本时，会存在类型标记错误的问题
                // 2017-04-05 不将数据转为日期
//                try {
//                    Date date = HSSFDateUtil.getJavaDate(Double.valueOf(value));
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    value = dateFormat.format(date);
//                }
//                catch (Exception e) {
//                }
            }

            if (curCol <= rowReader.getColCount()) { // 如果列标大于要读取数据的个数，则不加载
                rowDataMap.put(curCol - 1, value);
            }
        }
        else {
            // 如果标签名称为 row ，这说明已到行尾，调用 getRows() 方法
            if (name.equals("row")) {
                try {
                    rowReader.getRows(sheetIndex, curRow, rowDataMap);
                }
                catch (Exception e) {
                    throw new SAXException(e);
                }
                rowDataMap.clear();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 得到单元格内容的值
        lastContents += new String(ch, start, length);
    }

    // 将字母转换为数字（26进制）
    private int strToNum(String str) {

        int num = 0;
        int result = 0;
        int len = str.length();

        for (int i = 0; i < len; i++) {
            char ch = str.charAt(len - i - 1);
            num = (int) (ch - 'A' + 1);
            num *= Math.pow(26, i);
            result += num;
        }

        return result;
    }
}
