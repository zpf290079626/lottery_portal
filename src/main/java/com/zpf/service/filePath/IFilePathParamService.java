package com.zpf.service.filePath;

import com.zpf.domain.common.ExcelDomain;

/** 
* @author zhangpengfei  e-mail:pengfei19890227@163.com 
* @version 创建时间：2017年8月27日 下午9:45:44 
* 类说明 
*/
public interface IFilePathParamService {
	public ExcelDomain getExcelDomain(String transCode) throws Exception;
}
