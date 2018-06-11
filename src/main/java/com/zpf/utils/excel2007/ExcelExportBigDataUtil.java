package com.zpf.utils.excel2007;

import com.zpf.domain.common.ExcelDomain;
import com.zpf.service.filePath.IFilePathParamService;
import com.zpf.utils.DataUtil;
import com.zpf.utils.excel2007.annotation.ExcelField;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 支持大量数据导出的excel Util 内存不会溢出
 *
 * @author zhangpengfei
 * @since 2016-10-28
 */
public class ExcelExportBigDataUtil {

    private static final int MAX_EXPORT_COUNT = 300000;
    private Logger log = Logger.getLogger(ExcelExportBigDataUtil.class);
    @Autowired
    private IFilePathParamService filePathParamService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param clsList
     *            传入带有注解的javaBean
     * @param sqlList
     *            传入拼接好，可以直接执行的sql文本
     * @param transCode
     * @throws Exception
     */
    public ExcelDomain exportBySQL(List<Class<?>> clsList, List<StringBuilder> sqlList, List<String> sheetNameList,
        String transCode) throws Exception {

        if (clsList.size() != sqlList.size() || sqlList.size() != sheetNameList.size()) {
            throw new Exception("传入参数错误：clsList、dataList、sheetNameList 的大小需要相同");
        }
        ExcelDomain excelDomain = filePathParamService.getExcelDomain(transCode);

        String filePath = excelDomain.getFilePath();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OutputStream os = null;

        try {
            SXSSFWorkbook wb = new SXSSFWorkbook(1000);
            for (int index = 0; index < sqlList.size(); index++) {
                StringBuilder sql = sqlList.get(index);
                // 校验待导出的数据量
                this.checkRowCount(sql.toString());

                conn = jdbcTemplate.getDataSource().getConnection();
                ps = conn.prepareStatement(sql.toString(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                rs = ps.executeQuery();

                Sheet sh = wb.createSheet(sheetNameList.get(index));

                List<ExcelField> excelFieldList = this.getFieldAnnotation(clsList.get(index));

                // 写头信息
                Row headRow = sh.createRow(0);
                for (ExcelField excelField : excelFieldList) {
                    Cell cell = headRow.createCell(excelField.index());
                    cell.setCellValue(excelField.colDesc());
                }
                // 写数据
                while (rs.next()) {
                    Row dataRow = sh.createRow(rs.getRow());
                    for (ExcelField excelField : excelFieldList) {
                        Object data = rs.getObject(excelField.colName());
                        String value = null;
                        if (data instanceof Blob) {
                            value = "";
                        } else {
                            value = DataUtil.convertSqlDataToString(data);
                        }
                        Cell cell = dataRow.createCell(excelField.index());
                        cell.setCellValue(value);
                    }
                }

            }

            os = new FileOutputStream(filePath, false);
            wb.write(os);
            return excelDomain;
        } catch (Exception e) {
            log.error(e.fillInStackTrace(), e);
            throw new Exception("生成文件失败");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    log.error(e.fillInStackTrace(), e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    log.error(e.fillInStackTrace(), e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    log.error(e.fillInStackTrace(), e);
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    log.error(e.fillInStackTrace(), e);
                }
            }
        }
    }

    /**
     * 将传入的data根据bean中的注解，写入excel文件
     *
     * @param clsList bean list
     * @param dataList 数据list
     * @param sheetNameList sheet页名称
     * @author zhangpengfei
     */
    public ExcelDomain exportByDataList(List<Class<?>> clsList, List<List<Object>> dataList, List<String> sheetNameList,
        String transCode) throws SecurityException, Exception {

        if (clsList.size() != dataList.size() || dataList.size() != sheetNameList.size()) {
            throw new Exception("传入参数错误：clsList、dataList、sheetNameList 的大小需要相同");
        }
        ExcelDomain excelDomain = filePathParamService.getExcelDomain(transCode);
        if (dataList.size() > ExcelExportBigDataUtil.MAX_EXPORT_COUNT) {
            throw new Exception("导出的数据量超过" + ExcelExportBigDataUtil.MAX_EXPORT_COUNT + "；请细化查询条件，以减少数据量！");
        }

        String filePath = excelDomain.getFilePath();
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        for (int index = 0; index < clsList.size(); index++) {
            Sheet sh = wb.createSheet(sheetNameList.get(index));
            List<ExcelField> excelFieldList = this.getFieldAnnotation(clsList.get(index));

            // 写头信息
            Row headRow = sh.createRow(0);
            Font font = wb.createFont();
            CellStyle style = wb.createCellStyle();
            setBorder(style, font);// 设置边框
            style.setFillBackgroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);// 背景色
            style.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 前景填充样式
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
            font.setFontName("微软雅黑");
            style.setFont(font);
            for (ExcelField excelField : excelFieldList) {
                Cell cell = headRow.createCell(excelField.index());
                cell.setCellValue(excelField.colDesc());
                cell.setCellStyle(style);
                sh.autoSizeColumn(excelField.index()); // 设置列宽，根据列头自动扩展
            }

            int rowIndex = 1;
            for (Object data : dataList.get(index)) {
                Row row = sh.createRow(rowIndex++);
                for (ExcelField excelField : excelFieldList) {
                    Method method = data.getClass().getDeclaredMethod("get"
                        + excelField.colName().substring(0, 1).toUpperCase() + excelField.colName().substring(1));
                    Object value = method.invoke(data);
                    Cell cell = row.createCell(excelField.index());
                    cell.setCellValue(DataUtil.convertSqlDataToString(value));
                }
            }
        }

        OutputStream os = null;
        try {
            os = new FileOutputStream(filePath, false);
            wb.write(os);
        } catch (Exception e) {
            log.error(e.fillInStackTrace(), e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    log.error(e.fillInStackTrace(), e);
                }
            }
        }

        return excelDomain;
    }

    /**
     * 校验待导出的数据的行数是否大于允许导出的最大行数
     */
    private void checkRowCount(String sql) throws Exception {

        sql = "select count(1) as my_count from ( " + sql + ")";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            int count = (Integer) rs.getObject("my_count");
            if (count > ExcelExportBigDataUtil.MAX_EXPORT_COUNT) {
                throw new Exception("数据量太大,最大导出数据量为:" + ExcelExportBigDataUtil.MAX_EXPORT_COUNT);
            }
        } catch (Exception e) {
            log.error(e.fillInStackTrace(), e);
            throw new Exception(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    log.error(e.fillInStackTrace(), e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    log.error(e.fillInStackTrace(), e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    log.error(e.fillInStackTrace(), e);
                }
            }
        }

    }

    // 获取bean的注解信息
    private List<ExcelField> getFieldAnnotation(Class<?> bean) {

        Field[] fields = bean.getDeclaredFields();
        List<ExcelField> cols = new ArrayList<ExcelField>();
        for (Field field : fields) {
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (null != excelField) {
                cols.add(excelField);
            }
        }
        return cols;
    }

    private static void setBorder(CellStyle style, Font font) {
        style.setBorderLeft(CellStyle.SOLID_FOREGROUND);
        style.setBorderRight(CellStyle.SOLID_FOREGROUND);
        style.setBorderTop(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(CellStyle.SOLID_FOREGROUND);
        font.setFontName("宋体");
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
