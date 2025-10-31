package com.ktdsuniversity.edu.domain.user.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserAreaVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.AreaCode;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public class ResponseUserInfoVO {

	private UserVO userVO;
	
	private List<AreaCode> areaList;
	
	private List<CommonCodeVO> categoryList;

	public UserVO getUserVO() {
		return this.userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public List<AreaCode> getAreaList() {
		return this.areaList;
	}

	public void setAreaList(List<AreaCode> areaList) {
		this.areaList = areaList;
	}

	public List<CommonCodeVO> getCategoryList() {
		return this.categoryList;
	}

	public void setCategoryList(List<CommonCodeVO> categoryList) {
		this.categoryList = categoryList;
	}

	@Override
	public String toString() {
		return "ResponseUserInfoVO [userVO=" + userVO + ", areaList=" + areaList + ", categoryList=" + categoryList
				+ "]";
	}
	
}
