package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignDao campaignDao;

	@Override
	public ResponseApplicantListVO readApplicantListById(String cmpnId) {
		List<ApplicantVO> applicant = this.campaignDao.selectApplicantListByCmpnId(cmpnId);
		String cmpnState = this.campaignDao.selectCmpnStateByCmpnId(cmpnId);
		
		ResponseApplicantListVO applicantList = new ResponseApplicantListVO();
		applicantList.setApplicantList(applicant);
		applicantList.setCmpnState(cmpnState);
		return applicantList;
	}

	@Override
	@Transactional
	public boolean updateAdptYnBycmpnApplyId(ApplicantVO applicantVO) {
		int updateCount = this.campaignDao.updateAdptYnBycmpnApplyId(applicantVO);
		
		return updateCount > 0;
	}

}