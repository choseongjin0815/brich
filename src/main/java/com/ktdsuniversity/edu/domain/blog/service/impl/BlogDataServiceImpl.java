package com.ktdsuniversity.edu.domain.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;



@Service
public class BlogDataServiceImpl implements BlogDataService{

	@Autowired
	private CampaignDao campaignDao;
	
	@Override
	public ResponseExpireSoonListVO readExpireSoonCampaignList() {
		
		List<CampaignVO> list = this.campaignDao.selectExpireSoonCampaign();
		
		ResponseExpireSoonListVO result = new ResponseExpireSoonListVO();

		result.setList(list);
		return result;
	}

}
