package com.zpf.domain.param;

import com.zpf.utils.excel2007.annotation.ExcelField;

/**
 * @author zhangpengfei e-mail:pengfei19890227@163.com
 * @version 创建时间：2017年8月27日 下午6:43:28 类说明
 */
public class SpsDomain {
	@ExcelField(colName = "records", colDesc = "records", index = 0)
	private String records;
	@ExcelField(colName = "originalMessages", colDesc = "originalMessages", index = 1)
	private String originalMessages;
	@ExcelField(colName = "solutionName", colDesc = "solutionName", index = 2)
	private String solutionName;
	@ExcelField(colName = "mobileVersion", colDesc = "mobileVersion", index = 3)
	private String mobileVersion;
	@ExcelField(colName = "version", colDesc = "version", index = 4)
	private String version;
	@ExcelField(colName = "language", colDesc = "language", index = 5)
	private String language;
	@ExcelField(colName = "convertToEnglish", colDesc = "convertToEnglish", index = 6)
	private String convertToEnglish;
	@ExcelField(colName = "userVocDate", colDesc = "userVocDate", index = 7)
	private String userVocDate;
	@ExcelField(colName = "mailFwdDate", colDesc = "mailFwdDate", index = 8)
	private String mailFwdDate;
	@ExcelField(colName = "bysWorkDate", colDesc = "bysWorkDate", index = 9)
	private String bysWorkDate;
	@ExcelField(colName = "senderMailAddress", colDesc = "senderMailAddress", index = 10)
	private String senderMailAddress;
	@ExcelField(colName = "countryId", colDesc = "countryId", index = 11)
	private String countryId;
	@ExcelField(colName = "country", colDesc = "country", index = 12)
	private String country;
	@ExcelField(colName = "supportedCountry", colDesc = "supportedCountry", index = 13)
	private String supportedCountry;
	@ExcelField(colName = "investigationMailCategory", colDesc = "investigationMailCategory", index = 14)
	private String investigationMailCategory;
	@ExcelField(colName = "issueCommnets", colDesc = "issueCommnets", index = 15)
	private String issueCommnets;
	@ExcelField(colName = "vocType", colDesc = "vocType", index = 16)
	private String vocType;
	@ExcelField(colName = "detailType", colDesc = "detailType", index = 17)
	private String detailType;
	@ExcelField(colName = "sameWith", colDesc = "sameWith", index = 18)
	private String sameWith;
	@ExcelField(colName = "actionTaken", colDesc = "actionTaken", index = 19)
	private String actionTaken;
	@ExcelField(colName = "printerBrands", colDesc = "printerBrands", index = 20)
	private String printerBrands;
	@ExcelField(colName = "printermModelName", colDesc = "printermModelName", index = 21)
	private String printermModelName;
	@ExcelField(colName = "tatSinceUserSent", colDesc = "tatSinceUserSent", index = 22)
	private String tatSinceUserSent;
	@ExcelField(colName = "tatSinceMailForwared", colDesc = "tatSinceMailForwared", index = 23)
	private String tatSinceMailForwared;
	@ExcelField(colName = "tatSinceBysWorking", colDesc = "tatSinceBysWorking", index = 24)
	private String tatSinceBysWorking;
	@ExcelField(colName = "replyDate", colDesc = "replyDate", index = 25)
	private String replyDate;
	@ExcelField(colName = "subVoc", colDesc = "subVoc", index = 26)
	private String subVoc;
	@ExcelField(colName = "replyDetails", colDesc = "replyDetails", index = 27)
	private String replyDetails;
	@ExcelField(colName = "tryOrNot", colDesc = "tryOrNot", index = 28)
	private String tryOrNot;
	@ExcelField(colName = "resolved", colDesc = "resolved", index = 29)
	private String resolved;
	@ExcelField(colName = "needUserToReply", colDesc = "needUserToReply", index = 30)
	private String needUserToReply;
	@ExcelField(colName = "gotReply", colDesc = "gotReply", index = 31)
	private String gotReply;
	@ExcelField(colName = "waitrR_D", colDesc = "waitrR_D", index = 32)
	private String waitrR_D;
	@ExcelField(colName = "needTracking", colDesc = "needTracking", index = 33)
	private String needTracking;
	@ExcelField(colName = "Status", colDesc = "Status", index = 34)
	private String Status;
	@ExcelField(colName = "worloadMins", colDesc = "worloadMins", index = 35)
	private String worloadMins;
	@ExcelField(colName = "counts", colDesc = "counts", index = 36)
	private String counts;
	@ExcelField(colName = "toCustomer", colDesc = "toCustomer", index = 37)
	private String toCustomer;
	@ExcelField(colName = "typeAnalysis", colDesc = "typeAnalysis", index = 38)
	private String typeAnalysis;
	@ExcelField(colName = "submitDefectBySsolution", colDesc = "submitDefectBySsolution", index = 39)
	private String submitDefectBySsolution;

	private String mtUser;
	private String mtTime;

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public String getOriginalMessages() {
		return originalMessages;
	}

	public void setOriginalMessages(String originalMessages) {
		this.originalMessages = originalMessages;
	}

	public String getSolutionName() {
		return solutionName;
	}

	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}

	public String getMobileVersion() {
		return mobileVersion;
	}

	public void setMobileVersion(String mobileVersion) {
		this.mobileVersion = mobileVersion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getConvertToEnglish() {
		return convertToEnglish;
	}

	public void setConvertToEnglish(String convertToEnglish) {
		this.convertToEnglish = convertToEnglish;
	}

	public String getUserVocDate() {
		return userVocDate;
	}

	public void setUserVocDate(String userVocDate) {
		this.userVocDate = userVocDate;
	}

	public String getMailFwdDate() {
		return mailFwdDate;
	}

	public void setMailFwdDate(String mailFwdDate) {
		this.mailFwdDate = mailFwdDate;
	}

	public String getBysWorkDate() {
		return bysWorkDate;
	}

	public void setBysWorkDate(String bysWorkDate) {
		this.bysWorkDate = bysWorkDate;
	}

	public String getSenderMailAddress() {
		return senderMailAddress;
	}

	public void setSenderMailAddress(String senderMailAddress) {
		this.senderMailAddress = senderMailAddress;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSupportedCountry() {
		return supportedCountry;
	}

	public void setSupportedCountry(String supportedCountry) {
		this.supportedCountry = supportedCountry;
	}

	public String getInvestigationMailCategory() {
		return investigationMailCategory;
	}

	public void setInvestigationMailCategory(String investigationMailCategory) {
		this.investigationMailCategory = investigationMailCategory;
	}

	public String getIssueCommnets() {
		return issueCommnets;
	}

	public void setIssueCommnets(String issueCommnets) {
		this.issueCommnets = issueCommnets;
	}

	public String getVocType() {
		return vocType;
	}

	public void setVocType(String vocType) {
		this.vocType = vocType;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getSameWith() {
		return sameWith;
	}

	public void setSameWith(String sameWith) {
		this.sameWith = sameWith;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public String getPrinterBrands() {
		return printerBrands;
	}

	public void setPrinterBrands(String printerBrands) {
		this.printerBrands = printerBrands;
	}

	public String getPrintermModelName() {
		return printermModelName;
	}

	public void setPrintermModelName(String printermModelName) {
		this.printermModelName = printermModelName;
	}

	public String getTatSinceUserSent() {
		return tatSinceUserSent;
	}

	public void setTatSinceUserSent(String tatSinceUserSent) {
		this.tatSinceUserSent = tatSinceUserSent;
	}

	public String getTatSinceMailForwared() {
		return tatSinceMailForwared;
	}

	public void setTatSinceMailForwared(String tatSinceMailForwared) {
		this.tatSinceMailForwared = tatSinceMailForwared;
	}

	public String getTatSinceBysWorking() {
		return tatSinceBysWorking;
	}

	public void setTatSinceBysWorking(String tatSinceBysWorking) {
		this.tatSinceBysWorking = tatSinceBysWorking;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public String getSubVoc() {
		return subVoc;
	}

	public void setSubVoc(String subVoc) {
		this.subVoc = subVoc;
	}

	public String getReplyDetails() {
		return replyDetails;
	}

	public void setReplyDetails(String replyDetails) {
		this.replyDetails = replyDetails;
	}

	public String getTryOrNot() {
		return tryOrNot;
	}

	public void setTryOrNot(String tryOrNot) {
		this.tryOrNot = tryOrNot;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getNeedUserToReply() {
		return needUserToReply;
	}

	public void setNeedUserToReply(String needUserToReply) {
		this.needUserToReply = needUserToReply;
	}

	public String getGotReply() {
		return gotReply;
	}

	public void setGotReply(String gotReply) {
		this.gotReply = gotReply;
	}

	public String getWaitrR_D() {
		return waitrR_D;
	}

	public void setWaitrR_D(String waitrR_D) {
		this.waitrR_D = waitrR_D;
	}

	public String getNeedTracking() {
		return needTracking;
	}

	public void setNeedTracking(String needTracking) {
		this.needTracking = needTracking;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getWorloadMins() {
		return worloadMins;
	}

	public void setWorloadMins(String worloadMins) {
		this.worloadMins = worloadMins;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getToCustomer() {
		return toCustomer;
	}

	public void setToCustomer(String toCustomer) {
		this.toCustomer = toCustomer;
	}

	public String getTypeAnalysis() {
		return typeAnalysis;
	}

	public void setTypeAnalysis(String typeAnalysis) {
		this.typeAnalysis = typeAnalysis;
	}

	public String getSubmitDefectBySsolution() {
		return submitDefectBySsolution;
	}

	public void setSubmitDefectBySsolution(String submitDefectBySsolution) {
		this.submitDefectBySsolution = submitDefectBySsolution;
	}

	public String getMtUser() {
		return mtUser;
	}

	public void setMtUser(String mtUser) {
		this.mtUser = mtUser;
	}

	public String getMtTime() {
		return mtTime;
	}

	public void setMtTime(String mtTime) {
		this.mtTime = mtTime;
	}

}
