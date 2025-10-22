package com.ktdsuniversity.edu.domain.campaign.vo;

import java.util.List;

public class ResponseApplicantListVO {

	private List<ApplicantVO> applicantList;
	
	private int adoptCount;
	
	private CampaignVO campaignInfo;
	
	public List<ApplicantVO> getApplicantList() {
		return this.applicantList;
	}

	public void setApplicantList(List<ApplicantVO> applicantList) {
		this.applicantList = applicantList;
	}

	public int getAdoptCount() {
		return this.adoptCount;
	}

	public void setAdoptCount(int adoptCount) {
		this.adoptCount = adoptCount;
	}

	public CampaignVO getCampaignInfo() {
		return this.campaignInfo;
	}

	public void setCampaignInfo(CampaignVO campaignInfo) {
		this.campaignInfo = campaignInfo;
	}

	@Override
	public String toString() {
		return "ApplicantListVO [applicantList=" + this.applicantList + "]";
	}
}
