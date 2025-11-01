package com.ktdsuniversity.edu.domain.campaign.vo.request;

public class RequestPostSubmitVO {
	
	public String postTitle;
	
	public String postUrl;
	
	public String cmpnId;
	
	public String blgId;
	
	public String postSubmitChgCn;
	
	public String getPostSubmitChgCn() {
		return postSubmitChgCn;
	}

	public void setPostSubmitChgCn(String postSubmitChgCn) {
		this.postSubmitChgCn = postSubmitChgCn;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostUrl() {
		return postUrl;
	}

	public void setPostUrl(String postUrl) {
		this.postUrl = postUrl;
	}

	public String getCmpnId() {
		return cmpnId;
	}

	public void setCmpnId(String cmpnId) {
		this.cmpnId = cmpnId;
	}

	public String getBlgId() {
		return blgId;
	}

	public void setBlgId(String blgId) {
		this.blgId = blgId;
	}

	
	@Override
	public String toString() {
		return "RequestPostSubmitVO [postTitle=" + postTitle + ", postUrl=" + postUrl + ", cmpnId=" + cmpnId
				+ ", blgId=" + blgId + ", postSubmitChgCn=" + postSubmitChgCn + "]";
	}

}
