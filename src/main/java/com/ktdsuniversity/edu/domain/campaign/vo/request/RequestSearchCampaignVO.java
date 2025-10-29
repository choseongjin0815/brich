package com.ktdsuniversity.edu.domain.campaign.vo.request;

/**
 * 캠페인 메인 정렬 기준을 입력받는 VO
 */
public class RequestSearchCampaignVO {
	
	
	public String category;
	public String sortBy;
	public String searchKeyword;
	public String loginId;
	
	
	public String getLoginId() {
		return this.loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public RequestSearchCampaignVO(String category, String sortBy, String searchKeyword, String loginId) {
		super();
		this.category = category;
		this.sortBy = sortBy;
		this.searchKeyword = searchKeyword;
		this.loginId = loginId;
	}
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSortBy() {
		return this.sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSearchKeyword() {
		return this.searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	

	@Override
	public String toString() {
		return "RequestSearchCampaignVO [category=" + category + ", sortBy=" + sortBy + ", searchKeyword="
				+ searchKeyword + ", loginId=" + loginId + "]";
	}
	
	

}
