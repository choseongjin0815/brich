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
	 
	 

}
