package com.ktdsuniversity.edu.domain.campaign.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;

public interface CampaignDao {

	CampaignVO selectCampaignDetailById(String campaignId);

	List<CommonCodeVO> selectCategory();

	List<ApplicantVO> selectApplicantListByCmpnId(ApplicantVO applicantVO);

	int updateAdptYnBycmpnApplyId(ApplicantVO applicantVO);

	int selectAdoptCount(String campId);

	CampaignVO selectCampaignInfoByCmpnId(String campId);

}