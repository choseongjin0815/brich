package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public class ResponseCampaignListVO {
	
	
	/**
	 * 캠페인 리스트
	 */
	public List<ResponseCampaignVO> ResponseCampaignList;
	
	/**
	 * 카테고리 리스트
	 */
	public List<CommonCodeVO> CategoryList;
	


	

	public List<CommonCodeVO> getCategoryList() {
		return this.CategoryList;
	}

	public void setCategoryList(List<CommonCodeVO> categoryList) {
		CategoryList = categoryList;
	}

	public List<ResponseCampaignVO> getResponseCampaignList() {
		return this.ResponseCampaignList;
	}

	public void setResponseCampaignList(List<ResponseCampaignVO> responseCampaignList) {
		ResponseCampaignList = responseCampaignList;
	}

	@Override
	public String toString() {
		return "ResponseCampaignListVO [ResponseCampaignList=" + ResponseCampaignList + ", CategoryList=" + CategoryList
				+ "]";
	}
}
