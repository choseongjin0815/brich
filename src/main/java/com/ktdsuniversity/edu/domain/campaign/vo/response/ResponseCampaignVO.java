package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;

public class ResponseCampaignVO extends CampaignVO{
	
	 /**
	  * 좋아요 수
	  */
	 public int likeCnt;
	 /**
	  * 현재 신청자 수 
	  */
	 public int adptCnt;
	 
	 /**
	  * 지역 이름
	  */
	 public List<String> area;
	 
	 /**
	  * 부모지역 이름
	  */
	 public String parentArea;

	 public int getLikeCnt() {
		 return this.likeCnt;
	 }

	 public void setLikeCnt(int likeCnt) {
		 this.likeCnt = likeCnt;
	 }

	 public int getAdptCnt() {
		 return this.adptCnt;
	 }

	 public void setAdptCnt(int adptCnt) {
		 this.adptCnt = adptCnt;
	 }

	 @Override
	public String toString() {
		return "ResponseCampaignVO [likeCnt=" + likeCnt + ", adptCnt=" + adptCnt + ", area=" + area + ", parentArea="
				+ parentArea + "] " + super.toString();
	}

	 public List<String> getArea() {
		 return this.area;
	 }

	 public void setArea(List<String> area) {
		 this.area = area;
	 }

	 public String getParentArea() {
		 return this.parentArea;
	 }

	 public void setParentArea(String parentArea) {
		 this.parentArea = parentArea;
	 }
	 
	 

}
