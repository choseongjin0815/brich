package com.ktdsuniversity.edu.domain.campaign.vo;

import java.util.List;

import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public class CampaignListVO {
	
	/**
	 * level 1 카테고리 목록
	 */
	public List<CommonCodeVO> category;
	
	/**
	 * 캠페인 리스트
	 */
	public List<CampaignVO> campaignList;



	public List<CommonCodeVO> getCategory() {
		return this.category;
	}

	public void setCategory(List<CommonCodeVO> category) {
		this.category = category;
	}

	public List<CampaignVO> getCampaignList() {
		return this.campaignList;
	}

	public void setCampaignList(List<CampaignVO> campaignList) {
		this.campaignList = campaignList;
	}

	@Override
	public String toString() {
		return "CampaignListVO [category=" + category + ", campaignList=" + campaignList + "]";
	}
	
	
}
