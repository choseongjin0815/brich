package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.AbstractSearchVO;
import com.ktdsuniversity.edu.global.common.BaseVO;

public class ApplicantVO extends AbstractSearchVO {

	private String cmpnPstAdptId;

	private String adptYn;
	// 요청에 사용
	private String campId;
	private String order;
	private String sortCol;
	
	private UserVO userInfo;
	
	public String getCampId() {
		return this.campId;
	}
	public void setCampId(String campId) {
		this.campId = campId;
	}
	
	public String getCmpnPstAdptId() {
		return this.cmpnPstAdptId;
	}
	public void setCmpnPstAdptId(String cmpnPstAdptId) {
		this.cmpnPstAdptId = cmpnPstAdptId;
	}
	public String getAdptYn() {
		return this.adptYn;
	}
	public void setAdptYn(String adptYn) {
		this.adptYn = adptYn;
	}
	public UserVO getUserInfo() {
		return this.userInfo;
	}
	public void setUserInfo(UserVO userInfo) {
		this.userInfo = userInfo;
	}
	public String getOrder() {
		return this.order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSortCol() {
		return this.sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	@Override
	public String toString() {
		return "ApplicantVO [cmpnPstAdptId=" + cmpnPstAdptId + ", adptYn=" + adptYn + ", userInfo=" + userInfo
				+ ", campId=" + campId + ", order=" + order + ", sortCol=" + sortCol + "]";
	}
	
}
