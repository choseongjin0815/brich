package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.BaseVO;

public class ApplicantVO extends BaseVO {

	private String cmpnPstAdptId;

	private String adptYn;
	private UserVO userInfo;
	
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
	
	@Override
	public String toString() {
		return "ApplicantVO [adptYn=" + this.adptYn + ", userInfo=" + this.userInfo + "]";
	}
}
