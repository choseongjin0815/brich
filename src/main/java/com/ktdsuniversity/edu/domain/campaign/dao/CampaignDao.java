package com.ktdsuniversity.edu.domain.campaign.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;

public interface CampaignDao {

	List<ApplicantVO> selectApplicantListByCmpnId(String cmpnId);

	String selectCmpnStateByCmpnId(String cmpnId);

	int updateAdptYnBycmpnApplyId(ApplicantVO applicantVO);

}