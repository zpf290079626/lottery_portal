package com.zpf.domain.login;

import java.io.Serializable;

public class UserLoginVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String password;
	private String loginIp;
	private String sbno;
	private String sbnm;
	private String sysCode;// 系统编码，在系统登录时需要指定。@20130725 @Author SmallTree
	private String[] roles;
	private String[] menus;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getMenus() {
		return menus;
	}

	public void setMenus(String[] menus) {
		this.menus = menus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getSbno() {
		return sbno;
	}

	public void setSbno(String sbno) {
		this.sbno = sbno;
	}

	public String getSbnm() {
		return sbnm;
	}

	public void setSbnm(String sbnm) {
		this.sbnm = sbnm;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

}
