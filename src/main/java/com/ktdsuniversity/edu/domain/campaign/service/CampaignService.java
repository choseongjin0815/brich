package com.ktdsuniversity.edu.domain.campaign.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;

public interface CampaignService {

	CampaignVO readCampaignDetail(String campaignId);

	CampaignListVO readCampaignList(RequestSearchCampaignVO requestSearchCampaignVO);

}