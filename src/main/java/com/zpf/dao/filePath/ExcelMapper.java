package com.zpf.dao.filePath;


import com.zpf.domain.common.ExcelDomain;

/**
 * @author zhangpengfei e-mail:pengfei19890227@163.com
 * @version 创建时间：2017年8月27日 下午9:40:21 类说明
 */
public interface ExcelMapper {
	public ExcelDomain queryFilePathParam(String transCode);
}
