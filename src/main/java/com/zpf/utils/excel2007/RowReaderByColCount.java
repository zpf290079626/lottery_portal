package com.zpf.utils.excel2007;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

public class RowReaderByColCount implements IRowReader {

    private List<List<String>> readInfo = new ArrayList<List<String>>(); // 将各行数据放到

    private Integer colCount = null; // 列数

    RowReaderByColCount(Integer colCount) {
        this.colCount = colCount;
    }

    /*
     * 业务逻辑实现方法
     * 
     * @see com.eprosun.util.excel.IRowReader#getRows(int, int, java.util.List)
     */
    @Override
    public void getRows(int sheetIndex, int curRow, Map<Integer, String> rowDataMap) throws SAXException {

        if (curRow > MAX_ROW_COUNT) {
            throw new SAXException("当前文件中最大行数已超过：" + MAX_ROW_COUNT + "请将文件分割为多个文件后再导入！");
        }

        // 填充空白行
        for (int j = this.readInfo.size(); j < curRow - 1; j++) {
            this.readInfo.add(new ArrayList<String>());
        }

        List<String> lineInfo = new ArrayList<String>();
        for (int i = 0; i < colCount; i++) {
            lineInfo.add(null == rowDataMap.get(i) ? "" : rowDataMap.get(i).trim()); // cell值不存在时置为空字符串
        }

        this.readInfo.add(lineInfo);
    }

    /*
     * 获取excel文件中所有的结果集
     */
    @Override
    public List<List<String>> getReadInfo() {
        return this.readInfo;
    }

    @Override
    public List<String> getRowHead() {
        return this.readInfo.get(0); // 返回第一行数据
    }

    /*
     * 按照分页获取数据
     */
    @Override
    public List<List<String>> getReadInfoByPage(int pageSize, int index) {

        int rowCount = this.getRowCount();

        int start = pageSize * (index - 1);
        int end = pageSize * index;
        if (end > rowCount) {
            end = rowCount;
        }

        List<List<String>> readInfo = new ArrayList<List<String>>();
        for (int i = start; i < end; i++) {
            readInfo.add(this.readInfo.get(i));
        }
        return readInfo;
    }

    /*
     * 按照分页获取数据后，并忽略首行的模板
     */
    @Override
    public List<List<String>> getReadInfoByPageIgnoreHead(int pageSize, int index) {

        int rowCount = this.getRowCount();

        int start = 1 + pageSize * (index - 1);
        int end = 1 + pageSize * index;
        if (end > rowCount) {
            end = rowCount;
        }

        List<List<String>> readInfo = new ArrayList<List<String>>();
        for (int i = start; i < end; i++) {
            readInfo.add(this.readInfo.get(i));
        }
        return readInfo;
    }

    @Override
    public int getColCount() {
        return this.colCount;
    }

    @Override
    public int getRowCount() {
        return readInfo.size();
    }

}