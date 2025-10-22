package com.ktdsuniversity.edu.domain.user.vo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class RequestUserRegistVO{
	
	private String usrId;
	@NotEmpty(message="필수 입력입니다.")
	private String logId;
	
	@NotEmpty(message="필수 입력입니다.")
	private String nm;
	
	@Email(message="이메일 형식으로 작성하세요.")
	@NotEmpty(message="필수 입력입니다.")
	private String eml;
	
	@NotEmpty(message="필수 입력입니다.")
	private String pswrd;
	
	@NotEmpty(message="필수 입력입니다.")
	private String pswrdConfirm;
	
	private String autr;
	
	private String cmpny;
	
	private String salt;

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

	public String getPswrdConfirm() {
		return this.pswrdConfirm;
	}

	public void setPswrdConfirm(String pswrdConfirm) {
		this.pswrdConfirm = pswrdConfirm;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "RequestUserRegistVO [usrId=" + usrId + ", logId=" + logId + ", nm=" + nm + ", eml=" + eml + ", pswrd="
				+ pswrd + ", pswrdConfirm=" + pswrdConfirm + ", autr=" + autr + ", cmpny=" + cmpny + ", salt=" + salt
				+ ", toString()=" + super.toString() + "]";
	}
	
}
