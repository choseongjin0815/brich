package com.ktdsuniversity.edu.domain.campaign.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface CampaignDao {

	CampaignVO selectCampaignDetailById(String campaignId);

	List<CommonCodeVO> selectCategory();

}