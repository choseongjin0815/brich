package com.ktdsuniversity.edu.domain.campaign.service;

import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseApplicantListVO;

public interface CampaignService {

	ResponseApplicantListVO readApplicantListById(String cmpnId);

	boolean updateAdptYnBycmpnApplyId(ApplicantVO applicantVO);

}