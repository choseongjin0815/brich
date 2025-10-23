package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

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

	public ResponseApplicantListVO readApplicantListById(RequestApplicantVO requestApplicantVO) {
		int applicantCount = this.campaignDao.selectApplicantCountByCmpnId(requestApplicantVO);
		requestApplicantVO.setPageCount(applicantCount);
		
		List<ResponseApplicantVO> applicant = this.campaignDao.selectApplicantListByCmpnId(requestApplicantVO);
		int adoptCount = this.campaignDao.selectAdoptCountByCmpnId(requestApplicantVO.getCmpnId());
		CampaignVO campaignInfo = this.campaignDao.selectCampaignInfoByCmpnId(requestApplicantVO.getCmpnId());
		
		ResponseApplicantListVO applicantList = new ResponseApplicantListVO();
		applicantList.setApplicantList(applicant);
		applicantList.setAdoptCount(adoptCount);
		applicantList.setCampaignInfo(campaignInfo);
		
		return applicantList;
	}
	
	@Override
	@Transactional
	public boolean updateAdptYnBycmpnApplyId(RequestApplicantVO requestApplicantVO) {
		String cmpnState = this.campaignDao.selectCampaignStateByCmpnPstAdptId(requestApplicantVO.getCmpnPstAdptId());
		if (!cmpnState.equals("2006")) {
			// TODO : Ajax 에러 처리 하기
			throw new IllegalArgumentException("선정 단계가 아닙니다.");
		}
		
		int updateCount = this.campaignDao.updateAdptYnByCmpnPstAdptId(requestApplicantVO);
		
		return updateCount > 0;
	}

	@Override
	public ResponseAdoptListVO readResponseAdoptListByCmpnId(RequestApplicantVO requestApplicantVO) {
		List<ResponseAdoptVO> adopt = this.campaignDao.selectAdoptListByCmpnId(requestApplicantVO);
		CampaignVO campaign = this.campaignDao.selectCampaignInfoByCmpnId(requestApplicantVO.getCmpnId());
		
		ResponseAdoptListVO adoptList = new ResponseAdoptListVO();
		adoptList.setAdoptList(adopt);
		adoptList.setCampaignInfo(campaign);
		
		return adoptList;
	}

}