package com.ktdsuniversity.edu.domain.campaign.service;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignListVO;
import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseApplicantListVO;

public interface CampaignService {

	CampaignVO readCampaignDetail(String campaignId);

	ResponseCampaignListVO readCampaignListAndCategory(RequestSearchCampaignVO requestSearchCampaignVO);

	ResponseApplicantListVO readApplicantListById(RequestApplicantVO requestApplicantVO);

	boolean updateAdptYnBycmpnApplyId(RequestApplicantVO requestApplicantVO);

	ResponseAdoptListVO readResponseAdoptListByCmpnId(RequestApplicantVO requestApplicantVO);

}