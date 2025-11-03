package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.campaign.dao.AdminCategoryDao;
import com.ktdsuniversity.edu.domain.campaign.service.AdminCategoryService;
import com.ktdsuniversity.edu.domain.campaign.vo.AdminCampaignCategoryVO;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminCategoryServiceImpl.class);
	
	@Autowired
	private AdminCategoryDao adminCategoryDao;

	@Override
	public List<AdminCampaignCategoryVO> readCampaignCategoryList() {
		return this.adminCategoryDao.selectCampaignCategoryList();
	}

	@Override
	public List<AdminCampaignCategoryVO> readChildrenCategoryList(String parentCdId) {
		return this.adminCategoryDao.selectChildrenCategoryList(parentCdId);
	}

	@Override
	public List<AdminCampaignCategoryVO> readParentCategoryList(String excludeCdId) {
		return this.adminCategoryDao.selectParentCategoryList(excludeCdId);
	}

	@Transactional
	@Override
	public boolean createNewCampaignCategory(AdminCampaignCategoryVO adminCampaignCategoryVO) {
		
		// cdId, srt (제일 큰 값으로 받아오기, DLT_YN 상관없이, SELECT)
		String lastCdId = this.adminCategoryDao.selectLastCampaignCdId();
		lastCdId = (Integer.parseInt(lastCdId) + 1) + "";
		
		int lastSrt = this.adminCategoryDao.selectLastSrtNumber();
		lastSrt += 1;
		
		adminCampaignCategoryVO.setCdId(lastCdId);
		adminCampaignCategoryVO.setSrt(lastSrt);
		
		int insertCount = this.adminCategoryDao.insertNewCampaignCategory(adminCampaignCategoryVO);
		
		// 카테고리 추가 (INSERT)
		return insertCount > 0;
	}
	
	

}
