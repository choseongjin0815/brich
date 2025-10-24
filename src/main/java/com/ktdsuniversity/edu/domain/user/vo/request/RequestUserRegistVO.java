package com.ktdsuniversity.edu.domain.user.vo.request;

public class RequestUserRegistVO{
	
	private String usrId;
	
	private String logId;
	
	private String nm;
	
	private String eml;
	
	private String pswrd;
	
	private String autr;
	
	private String cmpny;

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

	public String getNm() {
		return this.nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getEml() {
		return this.eml;
	}

	public void setEml(String eml) {
		this.eml = eml;
	}

	public String getPswrd() {
		return this.pswrd;
	}

	public void setPswrd(String pswrd) {
		this.pswrd = pswrd;
	}

	public String getAutr() {
		return this.autr;
	}

	public void setAutr(String autr) {
		this.autr = autr;
	}

	public String getCmpny() {
		return this.cmpny;
	}

	public void setCmpny(String cmpny) {
		this.cmpny = cmpny;
	}

	@Override
	public String toString() {
		return "RequestUserRegistVO [usrId=" + usrId + ", logId=" + logId + ", nm=" + nm + ", eml=" + eml + ", pswrd="
				+ pswrd + ", autr=" + autr + ", cmpny=" + cmpny + ", toString()=" + super.toString() + "]";
	}

	
	
}
