package com.ktdsuniversity.edu.domain.campaign.vo.response;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostAdoptVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;

public class ResponseAdoptVO extends CampaignPostAdoptVO {

	private String postCdNm;
	private UserVO userInfo;
	
	public String getPostCdNm() {
		return this.postCdNm;
	}
	public void setPostCdNm(String postCdNm) {
		this.postCdNm = postCdNm;
	}
	public UserVO getUserInfo() {
		return this.userInfo;
	}
	public void setUserInfo(UserVO userInfo) {
		this.userInfo = userInfo;
	}
	
	@Override
	public String toString() {
		return "ResponseAdoptVO [postCdNm=" + postCdNm + ", userInfo=" + userInfo + "]";
	}
}
