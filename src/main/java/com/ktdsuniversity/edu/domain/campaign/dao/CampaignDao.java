package com.ktdsuniversity.edu.domain.campaign.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface CampaignDao {

	CampaignVO selectCampaignDetailById(String campaignId);

	List<CommonCodeVO> selectCategoryList();

	List<ApplicantVO> selectApplicantListByCmpnId(String cmpnId);

	String selectCmpnStateByCmpnId(String cmpnId);

	int updateAdptYnBycmpnApplyId(ApplicantVO applicantVO);

	List<ResponseCampaignVO> selectCampaignListCategoryAndSortBy(RequestSearchCampaignVO requestSearchCampaignVO);

	String selectCategoryParent(String selectCategroy);

}