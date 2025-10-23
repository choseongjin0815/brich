package com.ktdsuniversity.edu.domain.campaign.dao;


import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;

public interface CampaignDao {

	CampaignVO selectCampaignDetailById(String campaignId);

	List<CommonCodeVO> selectCategory();

	List<ApplicantVO> selectApplicantListByCmpnId(String cmpnId);

	String selectCmpnStateByCmpnId(String cmpnId);

	int updateAdptYnBycmpnApplyId(ApplicantVO applicantVO);

	List<CampaignVO> selectExpireSoonCampaign();

}