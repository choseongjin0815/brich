package com.ktdsuniversity.edu.domain.campaign.dao;


import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignPostAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface CampaignDao {

	CampaignVO selectCampaignDetailById(String campaignId);

	List<CommonCodeVO> selectCategoryList();

	List<ResponseApplicantVO> selectApplicantListByCmpnId(RequestApplicantVO requestApplicantVO);

	int updateAdptYnByCmpnPstAdptId(RequestApplicantVO requestApplicantVO);

	int selectAdoptCountByCmpnId(String cmpnId);

	CampaignVO selectCampaignInfoByCmpnId(String cmpnId);

	int selectApplicantCountByCmpnId(RequestApplicantVO requestApplicantVO);

	String selectCampaignStateByCmpnPstAdptId(String cmpnPstAdptId);

	List<ResponseAdoptVO> selectAdoptListByCmpnId(RequestApplicantVO requestApplicantVO);

	List<ResponseCampaignVO> selectCampaignListCategoryAndSortBy(RequestSearchCampaignVO requestSearchCampaignVO);

	String selectCategoryParent(String selectCategroy);

	List<CampaignVO> selectExpireSoonCampaign();

}