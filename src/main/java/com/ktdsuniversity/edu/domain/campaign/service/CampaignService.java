package com.ktdsuniversity.edu.domain.campaign.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseApplicantListVO;

public interface CampaignService {

	CampaignVO readCampaignDetail(String campaignId);

	CampaignListVO readCampaignList(RequestSearchCampaignVO requestSearchCampaignVO);

	ResponseApplicantListVO readApplicantListById(ApplicantVO applicantVO);

	boolean updateAdptYnBycmpnApplyId(ApplicantVO applicantVO);

}