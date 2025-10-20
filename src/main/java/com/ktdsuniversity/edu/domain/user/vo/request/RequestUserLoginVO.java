package com.ktdsuniversity.edu.domain.user.vo.request;

public class RequestUserLoginVO {

	private String logId;

	private String pswrd;

	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getPswrd() {
		return this.pswrd;
	}

	public void setPswrd(String pswrd) {
		this.pswrd = pswrd;
	}

	@Override
	public String toString() {
		return "RequestUserLoginVO [logId=" + logId + ", pswrd=" + pswrd + "]";
	}
	
	
}
