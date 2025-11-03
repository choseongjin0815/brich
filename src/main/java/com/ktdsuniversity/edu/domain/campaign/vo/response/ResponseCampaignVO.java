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
	 
	 /**
	  * 포스팅 상태 코드
	  */
	 public String pstSttsCd;
	 
	 /**
	  * 포스팅 상태 코드 이름
	  */
	 public String pstSttsCdNm;
	 
	 /**
	  * 좋아요 여부
	  */
	 public String favYn;
	 
	 /**
	  * 신청 여부
	  */
	 public String adptYn;
	 
	 /**
	  * 공통코드 이름
	  */
	 public String SttsCdNm;
	 	 
	 public String getSttsCdNm() {
		return this.SttsCdNm;
	}

	 public void setSttsCdNm(String sttsCdNm) {
		 SttsCdNm = sttsCdNm;
	 }
	 
	 public String getPstSttsCdNm() {
			return this.pstSttsCdNm;
		}

	public void setPstSttsCdNm(String pstSttsCdNm) {
		this.pstSttsCdNm = pstSttsCdNm;
	}

	 public String getAdptYn() {
		return this.adptYn;
	}

	 public void setAdptYn(String adptYn) {
		 this.adptYn = adptYn;
	 }

	 public String getPstSttsCd() {
		return this.pstSttsCd;
	}

	 public void setPstSttsCd(String pstSttsCd) {
		 this.pstSttsCd = pstSttsCd;
	 }

	 public String getFavYn() {
		 return this.favYn;
	 }

	 public void setFavYn(String favYn) {
		 this.favYn = favYn;
	 }

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
				+ parentArea + ", pstSttsCd=" + pstSttsCd + ", pstSttsCdNm=" + pstSttsCdNm + ", favYn=" + favYn
				+ ", adptYn=" + adptYn + ", SttsCdNm=" + SttsCdNm + super.toString() + "]";
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
