package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.domain.campaign.dao.AdminCampaignDao;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.service.AdminCampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Service
public class AdminCampaignServiceImpl implements AdminCampaignService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminCampaignServiceImpl.class);
	
	@Autowired
	private AdminCampaignDao adminCampaignDao;
	
	@Autowired
	private CampaignDao campaignDao;

	@Override
	public ResponseAdminCampaignListVO readAdminCampaignListAndCategory(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO) {
		
		ResponseAdminCampaignListVO responseAdminCampaignListVO = new ResponseAdminCampaignListVO();
		
		// 상위 카테고리 목록 구하기
		List<CommonCodeVO> categoryList = this.campaignDao.selectCategoryList();
		responseAdminCampaignListVO.setCategoryList(categoryList);
		log.info("어드민 카테고리 목록: "+ categoryList.toString());
		
		log.info("어드민 getCategory(): " + requestAdminSearchCampaignVO.getCategory());
		if(requestAdminSearchCampaignVO.getCategory() != null) {
			String searchCategory = this.adminCampaignDao.selectAdminCategoryParent(requestAdminSearchCampaignVO.getCategory());
			requestAdminSearchCampaignVO.setCategory(searchCategory);
			log.info("어드민 조회할 카테고리 번호: " + searchCategory);
		}
		
		requestAdminSearchCampaignVO.setListSize(16);
		
		// 초기 정렬 세팅
		if(requestAdminSearchCampaignVO.getSortBy() == null) {
			requestAdminSearchCampaignVO.setSortBy("latest");
		}
		
		responseAdminCampaignListVO.setResponseAdminCampaignList(this.adminCampaignDao.selectAdminCampaignListCategoryAndSortBy(requestAdminSearchCampaignVO));
		
		// 지역명 두 글자로 자르기
		List<ResponseAdminCampaignVO> campaignList = responseAdminCampaignListVO.getResponseAdminCampaignList();
		for(ResponseAdminCampaignVO vo : campaignList) {
			if(vo.getParentArea() != null) {
				vo.setParentArea(vo.getParentArea().substring(0, 2));
			}
		}
		
		return responseAdminCampaignListVO;
	}


}
