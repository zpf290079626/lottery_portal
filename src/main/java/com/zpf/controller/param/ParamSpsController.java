package com.zpf.controller.param;
/** 
* @author zhangpengfei  e-mail:pengfei19890227@163.com 
* @version 创建时间：2017年8月26日 下午11:38:28 
* 类说明 
*/

import com.zpf.constants.CommonConstants;
import com.zpf.domain.param.SpsDomain;
import com.zpf.utils.DateUtil;
import com.zpf.utils.excel2007.ExcelReaderUtil;
import com.zpf.utils.excel2007.IRowReader;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ClassName: ParamSpsController
 * @Description: 参数表SPS相关
 * @author: zhangpengfei E-mail:pengfei19890227@163.com
 * @date: 2017年8月26日 下午11:38:36
 */
@Controller
@RequestMapping("/paramSpsController")
public class ParamSpsController {
	private static final Logger logger = Logger.getLogger(ParamSpsController.class);

	@Autowired
	private PlatformTransactionManager transactionManager;

	@RequestMapping("/spsMain")
	public String gotoDmpar(ModelMap modelMap) {
		return "/param/sps/spsMain";
	}

	/**
	 * enctype="multipart/form-data"
	 * 
	 * @return
	 */
	@RequestMapping("/upload")
	public Map<String, String> batchInsert(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		Map<String, String> result = new HashMap<String, String>();
		String errMsg = "";

		// 获得APP路径
		String path = request.getSession().getServletContext().getRealPath(CommonConstants.UPLOAD_FILE_PATH);
		String fileName = DateUtil.today("yyyyMMddHHmmssSSS") + file.getOriginalFilename();
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String filePath = path + fileName;
		try {
			IRowReader reader = ExcelReaderUtil.readExcel(filePath, SpsDomain.class);
			int count = reader.getColCount();
			if (count > 2000) {
				errMsg = "目前不支持特大数据导入，当前数据行已超过2000";
				result.put("errMsg", errMsg);
				return result;
			}
			
			// records,originalMessages,solutionName,mobileVersion,version,language,convertToEnglish,userVocDate,mailFwdDate,bysWorkDate,senderMailAddress,countryId,country,supportedCountry,investigationMailCategory,issueCommnets,vocType,detailType,sameWith,actionTaken,printerBrands,printermModelName,tatSinceUserSent,tatSinceMailForwared,tatSinceBysWorking,replyDate,subVoc,replyDetails,tryOrNot,resolved,needUserToReply,gotReply,waitrR_D,needTracking,Status,worloadMins,counts,toCustomer,typeAnalysis,submitDefectBySsolution,
			
			

		} catch (Exception e) {
			logger.error(e);
		}

		// ExcelReaderUtil
		return result;
	}

	/**
	 * 批量导入数据
	 * 
	 * @param objParams
	 */
	private void dealData(Map<String, Object> objParams) {

		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			transactionManager.commit(status);
		} catch (DataAccessException e) {
			throw e;
		}
	}
}
