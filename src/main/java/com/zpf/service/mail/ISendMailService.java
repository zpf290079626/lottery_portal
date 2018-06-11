package com.zpf.service.mail;

import com.zpf.domain.common.MailParamDomain;

/**
 * @author zhangpf
 *
 */
public interface ISendMailService {
	/**
	 * 
	 * @param mail
	 * @return 返回错误信息
	 */
	String sendMail(MailParamDomain mail);
}
