package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Service
public class CampaignServiceImpl implements CampaignService {
	
	private static final Logger log = LoggerFactory.getLogger(CampaignServiceImpl.class);

    @Autowired
    private CampaignDao campaignDao;

	@Override
	public CampaignVO readCampaignDetail(String campaignId) {
		CampaignVO detail = campaignDao.selectCampaignDetailById(campaignId);
		return detail;
	}

	@Override
	public ResponseCampaignListVO readCampaignListAndCategory (RequestSearchCampaignVO requestSearchCampaignVO) {
		
		ResponseCampaignListVO responseCampaignListVO = new ResponseCampaignListVO();
		
		// Level 1 카테고리 목록 구하기
		List<CommonCodeVO> CategoryList = campaignDao.selectCategoryList();
		responseCampaignListVO.setCategoryList(CategoryList);
		log.info("카테고리 목록 : "+ CategoryList.toString());
		
		// Level 2 조회조건 세팅
		if(requestSearchCampaignVO.getCategory() != null ) {
			// 부모 카테고리 조회
			String searchCatagory = campaignDao.selectCategoryParent(requestSearchCampaignVO.getCategory());
			requestSearchCampaignVO.setCategory(searchCatagory);
			log.info("조회할 카테고리 번호 : " + searchCatagory);
		}
		if(requestSearchCampaignVO.getSortBy() == null) {
			// 초기 정렬 세팅
			requestSearchCampaignVO.setSortBy("latest");
		}
		// Level 3 조회
		responseCampaignListVO.setResponseCampaignList(campaignDao.selectCampaignListCategoryAndSortBy(requestSearchCampaignVO));
			
			
			
		
		
		return responseCampaignListVO;
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