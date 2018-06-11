package com.zpf.domain.common;

public class ModelJson {

	private String message;
	private Object obj;
	private boolean success;
	private boolean operSucc;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public boolean isOperSucc() {
		return operSucc;
	}
	public void setOperSucc(boolean operSucc) {
		this.operSucc = operSucc;
	}
	
}
