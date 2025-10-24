package com.ktdsuniversity.edu.domain.user.vo;

public class AdminUserBaseInfoVO {
	
	private String usrId;
	private String logId;
	private String eml;
	private String nm;
	private String autr;
	
	private String rcntLgnScsDt;
	private int pnltCnt;
	private String crtDt;
	
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getLogId() {
		return this.logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getEml() {
		return this.eml;
	}
	public void setEml(String eml) {
		this.eml = eml;
	}
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getAutr() {
		return this.autr;
	}
	public void setAutr(String autr) {
		this.autr = autr;
	}
	public String getRcntLgnScsDt() {
		return this.rcntLgnScsDt;
	}
	public void setRcntLgnScsDt(String rcntLgnScsDt) {
		this.rcntLgnScsDt = rcntLgnScsDt;
	}
	public int getPnltCnt() {
		return this.pnltCnt;
	}
	public void setPnltCnt(int pnltCnt) {
		this.pnltCnt = pnltCnt;
	}
	public String getCrtDt() {
		return this.crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	
}
