package com.zpf.domain.common;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KeyInfoDomain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pkId;
	private String pkName;
	private String tableName;
	private String fieldName;
	private long maxVal = 0L;
	private long nextKey = 0L;
	private long stepLen;
	private int keyLen;
	private String sysCd;
	private Lock lock = new ReentrantLock(false);
	
	public Lock getLock(){
		return this.lock;
	}

	public String getPkId() {
		return pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public long getNextKey() {
		return nextKey;
	}

	public void setNextKey(long nextKey) {
		this.nextKey = nextKey;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public long getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(long maxVal) {
		this.maxVal = maxVal;
	}

	public long getStepLen() {
		return stepLen;
	}

	public void setStepLen(long stepLen) {
		this.stepLen = stepLen;
	}

	public int getKeyLen() {
		return keyLen;
	}

	public void setKeyLen(int keyLen) {
		this.keyLen = keyLen;
	}

	public String getSysCd() {
		return sysCd;
	}

	public void setSysCd(String sysCd) {
		this.sysCd = sysCd;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("pkId:");
		sb.append(pkId);
		sb.append(",pkName:");
		sb.append(pkName);
		sb.append(",tableName:");
		sb.append(tableName);
		sb.append(",fieldName:");
		sb.append(fieldName);
		sb.append(",maxVal:");
		sb.append(maxVal);
		sb.append(",nextKey:");
		sb.append(nextKey);
		sb.append(",stepLen:");
		sb.append(stepLen);
		sb.append(",keyLen:");
		sb.append(keyLen);
		sb.append(",sysCd:");
		sb.append(sysCd);
		return sb.toString();
	}
}
