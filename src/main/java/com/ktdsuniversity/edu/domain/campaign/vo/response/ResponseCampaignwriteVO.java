package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public class ResponseCampaignwriteVO {

	List<CommonCodeVO> categoryList;
	List<CommonCodeVO> doAndCityList;
	
	public List<CommonCodeVO> getCategoryList() {
		return this.categoryList;
	}
	public void setCategoryList(List<CommonCodeVO> categoryList) {
		this.categoryList = categoryList;
	}
	public List<CommonCodeVO> getDoAndCityList() {
		return this.doAndCityList;
	}
	public void setDoAndCityList(List<CommonCodeVO> doAndCityList) {
		this.doAndCityList = doAndCityList;
	}
	
	@Override
	public String toString() {
		return "ResponseCampaignwriteVO [categoryList=" + categoryList + ", doAndCityList=" + doAndCityList + "]";
	}
}
