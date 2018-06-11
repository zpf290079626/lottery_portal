package com.zpf.service.mail.impl;

import com.zpf.domain.common.MailParamDomain;
import com.zpf.service.mail.ISendMailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("sendMailService")
public class SendMailServiceImpl implements ISendMailService {
	private static final Logger logger = Logger.getLogger(SendMailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;// spring配置中定义
	@Autowired
	private SimpleMailMessage simpleMailMessage;// spring配置中定义
	
	@Override
	public String sendMail(MailParamDomain mail) {

		String errMsg = "";
		try {
			simpleMailMessage.setFrom(simpleMailMessage.getFrom()); // 发送人,从配置文件中取得
			simpleMailMessage.setTo(mail.getTo()); // 接收人
			simpleMailMessage.setSubject(mail.getSubject());
			simpleMailMessage.setText(mail.getContent());
			mailSender.send(simpleMailMessage);
			logger.info("邮件发送成功");
		} catch (Exception e) {
			logger.error(e);
			errMsg = e.getMessage();
		}

		return errMsg;
	}
}
