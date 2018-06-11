/**
 * 基于Dubbo的分布式系统架构视频教程，吴水成，wu-sc@foxmail.com，学习交流QQ群：367211134 .
 */
package com.zpf.domain.common;

import java.io.Serializable;

public class MailParamDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 发件人 **/
	private String from;
	/** 收件人 **/
	private String to;
	/** 主题 **/
	private String subject;
	/** 邮件内容 **/
	private String content;

	public MailParamDomain() {
	}

	public MailParamDomain(String to, String subject, String content) {
		this.to = to;
		this.subject = subject;
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
