package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class ResponseAdoptListVO {

	private List<ResponseAdoptVO> adoptList;
	private CampaignVO campaignInfo;
	
	public List<ResponseAdoptVO> getAdoptList() {
		return this.adoptList;
	}
	public void setAdoptList(List<ResponseAdoptVO> adoptList) {
		this.adoptList = adoptList;
	}
	public CampaignVO getCampaignInfo() {
		return this.campaignInfo;
	}
	public void setCampaignInfo(CampaignVO campaignInfo) {
		this.campaignInfo = campaignInfo;
	}
	
	@Override
	public String toString() {
		return "ResponseAdoptListVO [adoptList=" + adoptList + ", campaignInfo=" + campaignInfo + "]";
	}
}
