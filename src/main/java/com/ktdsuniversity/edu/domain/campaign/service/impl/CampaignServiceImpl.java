package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignDao campaignDao;

	@Override
	public CampaignVO readCampaignDetail(String campaignId) {
		CampaignVO detail = campaignDao.selectCampaignDetailById(campaignId);
		return detail;
	}

	@Override
	public CampaignListVO readCampaignList(RequestSearchCampaignVO requestSearchCampaignVO) {
		
		CampaignListVO campaignListVO = new CampaignListVO();
		
		// Level 1 카테고리 목록 구하기
		List<CommonCodeVO> Category = campaignDao.selectCategory();
		campaignListVO.setCategory(Category);
		// 캠페인 목록 조회
		// case 1 최초조회
		if(requestSearchCampaignVO.getCategory() == null && requestSearchCampaignVO.getSortBy() == null
				&& requestSearchCampaignVO.getSearchKeyword() == null) {
			
		}
		// case 2 캠페인, 정렬순 조회
		else {
			
		}
		
		return campaignListVO;
	}

	public ResponseApplicantListVO readApplicantListById(ApplicantVO applicantVO) {
		List<ApplicantVO> applicant = this.campaignDao.selectApplicantListByCmpnId(applicantVO);
		int adoptCount = this.campaignDao.selectAdoptCount(applicantVO.getCampId());
		CampaignVO campaignInfo = this.campaignDao.selectCampaignInfoByCmpnId(applicantVO.getCampId());
		
		ResponseApplicantListVO applicantList = new ResponseApplicantListVO();
		applicantList.setApplicantList(applicant);
		applicantList.setAdoptCount(adoptCount);
		applicantList.setCampaignInfo(campaignInfo);
		
		return applicantList;
	}

	@Override
	@Transactional
	public boolean updateAdptYnBycmpnApplyId(ApplicantVO applicantVO) {
		int updateCount = this.campaignDao.updateAdptYnBycmpnApplyId(applicantVO);
		
		return updateCount > 0;
	}

}