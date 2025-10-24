package com.ktdsuniversity.edu.domain.campaign.vo.response;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostAdoptVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;

public class ResponseAdoptVO extends CampaignPostAdoptVO {

	private String cdNm;
	private UserVO userInfo;
	
	public String getCdNm() {
		return this.cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public UserVO getUserInfo() {
		return this.userInfo;
	}
	public void setUserInfo(UserVO userInfo) {
		this.userInfo = userInfo;
	}
	
	@Override
	public String toString() {
		return "ResponseAdoptVO [cdMn=" + cdNm + ", userInfo=" + userInfo + super.toString() + "]";
	}
}
