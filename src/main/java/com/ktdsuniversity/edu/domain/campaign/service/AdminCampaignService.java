package com.ktdsuniversity.edu.domain.campaign.service;

import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;

public interface AdminCampaignService {

	ResponseAdminCampaignListVO readAdminCampaignListAndCategory(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO);

	ResponseAdminCampaignVO readAdminCampaignDetail(String cmpnId);

}
