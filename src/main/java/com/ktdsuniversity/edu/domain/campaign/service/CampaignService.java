package com.ktdsuniversity.edu.domain.campaign.service;



import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestCreateCmpnVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestDenyVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignwriteVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;


public interface CampaignService {

	ResponseCampaignVO readCampaignDetail(String campaignId);

	ResponseCampaignVO readCampaignDetail(String campaignId, String usrId);
	
	ResponseCampaignListVO readCampaignListAndCategory(RequestSearchCampaignVO requestSearchCampaignVO);

	ResponseApplicantListVO readApplicantListById(RequestApplicantVO requestApplicantVO);

	boolean updateAdptYnByCmpnPstAdptId(RequestApplicantVO requestApplicantVO);

	ResponseAdoptListVO readResponseAdoptListByCmpnId(RequestApplicantVO requestApplicantVO);

	ResponseCampaignListVO readSubmittedMyCampaignByBlgId(String blgId);

	int favCampaignDo(String blgId, String campaignId);

	ResponseCampaignListVO readOnGoingMyCampaignByBlgId(String blgId);

	ResponseCampaignListVO readClosedMyCampaignByBlgId(String blgId);

	ResponseCampaignListVO readFavMyCampaignByBlgId(String blgId);

	int applyCampaign(String campaignId, String blgId);

	boolean updatePstSttsApproveByCmpnPstAdoptId(RequestApplicantVO requestApplicantVO);

	boolean createDenyByCmpnPstAdoptId(RequestDenyVO requestDenyVO);

	ResponseCampaignwriteVO createCampaign();

	List<CommonCodeVO> readDistrictByCdId(String cdId);

	boolean createNewCampaign(RequestCreateCmpnVO requestCreateCmpnVO);

	ResponseCampaignListVO readCampaignListByUsrId(RequestSearchCampaignVO requestSearchCampaignVO);

	ResponseCampaignListVO readDenyHistoryByCmpnId(RequestSearchCampaignVO requestSearchCampaignVO);
}