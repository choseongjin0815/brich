package com.ktdsuniversity.edu.domain.campaign.vo;

import java.util.List;

public class ResponseApplicantListVO {

	private String cmpnState;

	private List<ApplicantVO> applicantList;

	public String getCmpnState() {
		return this.cmpnState;
	}
	
	public void setCmpnState(String cmpnState) {
		this.cmpnState = cmpnState;
	}
	
	public List<ApplicantVO> getApplicantList() {
		return this.applicantList;
	}

	public void setApplicantList(List<ApplicantVO> applicantList) {
		this.applicantList = applicantList;
	}

	@Override
	public String toString() {
		return "ApplicantListVO [applicantList=" + this.applicantList + "]";
	}
}
