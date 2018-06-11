package com.zpf.domain.common;

import java.io.Serializable;
import java.lang.String;

/**
 * <P>systemParam</P>
 */
public class SystemParamDomain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序号
	 */
	private String paramId;
	/**
	 * 参数名称
	 */
	private String paramName;
	/**
	 * 参数值
	 */
	private String paramValue;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 操作者
	 */
	private String mtUser;
	/**
	 * 操作者用户名
	 */
	private String mtUserName;
	/**
	 * 操作时间
	 */
	private String mtTime;
	/**
	 * 状态
	 */
	private String status;

	/**
	 * 返回序号
	 * @return 返回序号
	 */
	public String getParamId() {
		return this.paramId;
	}
	/**
	 * 设置序号
	 * @param paramId 序号
	 */
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	/**
	 * 返回参数名称
	 * @return 返回参数名称
	 */
	public String getParamName() {
		return this.paramName;
	}
	/**
	 * 设置参数名称
	 * @param paramName 参数名称
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	/**
	 * 返回参数值
	 * @return 返回参数值
	 */
	public String getParamValue() {
		return this.paramValue;
	}
	/**
	 * 设置参数值
	 * @param paramValue 参数值
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	/**
	 * 返回备注
	 * @return 返回备注
	 */
	public String getMemo() {
		return this.memo;
	}
	/**
	 * 设置备注
	 * @param memo 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 返回操作者
	 * @return 返回操作者
	 */
	public String getMtUser() {
		return this.mtUser;
	}
	/**
	 * 设置操作者
	 * @param mtUser 操作者
	 */
	public void setMtUser(String mtUser) {
		this.mtUser = mtUser;
	}
	/**
	 * 返回操作者用户名
	 * @return 返回操作者用户名
	 */
	public String getMtUserName() {
		return this.mtUserName;
	}
	/**
	 * 设置操作者用户名
	 * @param mtUser 操作者用户名
	 */
	public void setMtUserName(String mtUserName) {
		this.mtUserName = mtUserName;
	}
	/**
	 * 返回操作时间
	 * @return 返回操作时间
	 */
	public String getMtTime() {
		return this.mtTime;
	}
	/**
	 * 设置操作时间
	 * @param mtTime 操作时间
	 */
	public void setMtTime(String mtTime) {
		this.mtTime = mtTime;
	}
	/**
	 * 返回状态
	 * @return 返回状态
	 */
	public String getStatus() {
		return this.status;
	}
	/**
	 * 设置状态
	 * @param status 状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
