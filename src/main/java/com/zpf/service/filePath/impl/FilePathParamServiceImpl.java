package com.zpf.service.filePath.impl;

import com.zpf.dao.filePath.ExcelMapper;
import com.zpf.domain.common.ExcelDomain;
import com.zpf.service.filePath.IFilePathParamService;
import com.zpf.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO 高并发情况下 生成的文件路径可能会有问题，到时候需要拼接区分唯一的标识
 * 
 * @author zhangpengfei e-mail:pengfei19890227@163.com
 * @version 创建时间：2017年8月27日 下午9:48:28 类说明
 */
@Service("filePathParamService")
public class FilePathParamServiceImpl implements IFilePathParamService {

	@Autowired
	private ExcelMapper excelMapper;

	@Override
	public ExcelDomain getExcelDomain(String transCode) throws Exception {

		ExcelDomain excelDomain = excelMapper.queryFilePathParam(transCode);
		String fileName = null;
		String filePath = excelDomain.getFilePath(); // 文件路径
		String fileAlias = excelDomain.getFileAlias(); // 文件别名
		String filePrefix = excelDomain.getPrefix(); // 文件前缀
		if (filePath == null || filePath.trim().equals("")) {
			throw new Exception("filePath不存在");
		}
		if (fileAlias == null || fileAlias.trim().equals("")) {
			throw new Exception("文件名不存在");
		}
		if (fileAlias != null && !fileAlias.trim().equals("")) {
			String fileType = fileAlias.trim().substring(fileAlias.trim().indexOf(".") + 1);
			if (!fileType.equals("xlsx")) {
				throw new Exception("文件类型只支持xlsx");
			}
		}
		fileName = filePrefix + DateUtil.today("yyyyMMddHHmmssSSS") + fileAlias;
		filePath = filePath + fileName;

		excelDomain.setFilePath(filePath);
		excelDomain.setFileAlias(fileName);

		return excelDomain;
	}

}
