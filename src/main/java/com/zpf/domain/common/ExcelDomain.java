package com.zpf.domain.common;

public class ExcelDomain {
	private String transCode;  //交易代码
	private String filePath;   //文件路径
	private String fileComment;//文件描述
	private String prefix;     //文件前缀
	private String fileAlias;  //文件别名
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileComment() {
		return fileComment;
	}
	public void setFileComment(String fileComment) {
		this.fileComment = fileComment;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getFileAlias() {
		return fileAlias;
	}
	public void setFileAlias(String fileAlias) {
		this.fileAlias = fileAlias;
	}
	
	
}
