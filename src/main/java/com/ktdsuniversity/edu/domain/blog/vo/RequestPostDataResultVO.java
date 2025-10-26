package com.ktdsuniversity.edu.domain.blog.vo;

import java.util.List;

public class RequestPostDataResultVO {

	private String usrId;
	private List<PostDataVO> postList;
	
	
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public List<PostDataVO> getPostList() {
		return postList;
	}
	public void setPostList(List<PostDataVO> postList) {
		this.postList = postList;
	}
	
	
	
}
