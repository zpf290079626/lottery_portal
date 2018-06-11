package com.zpf.utils.excel2007;

import java.util.List;
import java.util.Map;

public interface IRowReader {

    static final int MAX_ROW_COUNT = 200000; // 读取的最大行数
    
    /**
     * 业务逻辑实现方法
     * 
     * @param sheetIndex
     * @param curRow
     * @param rowlist
     * @throws Exception
     */
    public void getRows(int sheetIndex, int curRow, Map<Integer, String> rowDataMap) throws Exception;

    // 获取数据集合
    public List<?> getReadInfo();

    // 获取首行模板数据
    public List<String> getRowHead();

    // 按照分页获取每一页的数据集合
    public List<?> getReadInfoByPage(int pageSize, int index);

    // 按照分页获取每一页的数据集合(忽略首行模板)
    public List<?> getReadInfoByPageIgnoreHead(int pageSize, int index);

    // 获得excel的列数
    public int getColCount();

    // 获得excel的行数
    public int getRowCount();
}
