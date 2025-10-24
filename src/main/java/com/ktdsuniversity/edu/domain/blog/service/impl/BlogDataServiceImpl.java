package com.ktdsuniversity.edu.domain.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;



@Service
public class BlogDataServiceImpl implements BlogDataService{

	@Autowired
	private CampaignDao campaignDao;
	
	@Override
	public ResponseExpireSoonListVO readExpireSoonCampaignList(RequestExpireSoonCampaignVO requestExpireSoonCampaignVO) {
		
		List<CampaignVO> list = this.campaignDao.selectExpireSoonCampaign(requestExpireSoonCampaignVO);
		
		ResponseExpireSoonListVO result = new ResponseExpireSoonListVO();
		result.setList(list);
		int totalCount = 8;
		requestExpireSoonCampaignVO.setPageCount(totalCount);;
		return result;
	}

}
