package com.ktdsuniversity.edu.domain.campaign.service;



import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;


public interface CampaignService {

	ResponseCampaignVO readCampaignDetail(String campaignId);

	ResponseCampaignListVO readCampaignListAndCategory(RequestSearchCampaignVO requestSearchCampaignVO);

	ResponseApplicantListVO readApplicantListById(RequestApplicantVO requestApplicantVO);

	boolean updateAdptYnBycmpnApplyId(RequestApplicantVO requestApplicantVO);

	ResponseAdoptListVO readResponseAdoptListByCmpnId(RequestApplicantVO requestApplicantVO);

}