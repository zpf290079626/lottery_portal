package com.zpf.domain.login;

import java.io.Serializable;

public class UserRegisterDomain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String password;
	private String tel;
	private String email;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInputValiCode() {
		return inputValiCode;
	}
	public void setInputValiCode(String inputValiCode) {
		this.inputValiCode = inputValiCode;
	}
	private String inputValiCode;
}
