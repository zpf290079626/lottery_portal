package com.zpf.utils.excel2007;

public class ExcelReaderUtil {

    // excel2007扩展名
    public static final String EXCEL07_EXTENSION = ".xlsx";

    /**
     * 读取Excel文件，大数据读入仅支持07版本,仅读取第一个sheet页
     * 
     * 
     * @param fileName
     *            文件后缀必须为.xlsx
     * @param columnCount
     *            读取excel的列数
     * @throws Exception
     * @author zhangpengfei
     * @since 2016-06-29
     * 
     */
    public static IRowReader readExcel(String filePath, int columnCount) throws Exception {

        IRowReader reader = new RowReaderByColCount(columnCount);
        if (filePath.endsWith(EXCEL07_EXTENSION)) {
            Excel2007Reader excel07 = new Excel2007Reader();
            excel07.setRowReader(reader);
            excel07.processOneSheet(filePath, 0); // 仅读取第一个sheet页
        }
        else {
            throw new Exception("文件格式错误，fileName的扩展名只能是xlsx。");
        }

        return reader;
    }
    
    /**
     * 读取Excel文件，大数据读入仅支持07版本,仅读取第一个sheet页
     * 
     * 
     * @param fileName
     *            文件后缀必须为.xlsx
     * @param cls
     *            与excel内容关联的带有注解的bean
     * @throws Exception
     * @author zhangpengfei
     * @since 2016-06-29
     * 
     */
    public static IRowReader readExcel(String filePath, Class<?> cls) throws Exception {

        IRowReader reader = new RowReaderByClass(cls);
        if (filePath.endsWith(EXCEL07_EXTENSION)) {
            Excel2007Reader excel07 = new Excel2007Reader();
            excel07.setRowReader(reader);
            excel07.processOneSheet(filePath, 0); // 仅读取第一个sheet页
        }
        else {
            throw new Exception("文件格式错误，fileName的扩展名只能是xlsx。");
        }

        return reader;
    }
}