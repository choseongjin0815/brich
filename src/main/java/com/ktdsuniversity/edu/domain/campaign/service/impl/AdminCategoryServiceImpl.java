package com.ktdsuniversity.edu.domain.campaign.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	

}
