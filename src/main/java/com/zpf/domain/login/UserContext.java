package com.zpf.domain.login;

import java.io.Serializable;

public class UserContext implements Serializable {

	private static final long serialVersionUID = 2333451765447939127L;
	private String userId;
	private String userName;
	private String loginIp;
	private String loginId;
	private String sessionId;
	private String sysMgerType;// 特殊角色标识
	private String loginErrorMsg = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSysMgerType() {
		return sysMgerType;
	}

	public void setSysMgerType(String sysMgerType) {
		this.sysMgerType = sysMgerType;
	}

	public String getLoginErrorMsg() {
		return loginErrorMsg;
	}

	public void setLoginErrorMsg(String loginErrorMsg) {
		this.loginErrorMsg = loginErrorMsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
