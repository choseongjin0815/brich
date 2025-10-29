package com.ktdsuniversity.edu.domain.blog.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestModifyBlogAddrsVO;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;
import com.ktdsuniversity.edu.domain.user.dao.UserDao;
import com.ktdsuniversity.edu.global.util.PythonExecutor;



@Service
public class BlogDataServiceImpl implements BlogDataService{

	@Autowired
	private CampaignDao campaignDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public ResponseExpireSoonListVO readExpireSoonCampaignList(RequestExpireSoonCampaignVO requestExpireSoonCampaignVO) {
		
		List<CampaignVO> list = this.campaignDao.selectExpireSoonCampaign(requestExpireSoonCampaignVO);
		
		ResponseExpireSoonListVO result = new ResponseExpireSoonListVO();
		result.setList(list);
		int totalCount = 50;
		requestExpireSoonCampaignVO.setPageCount(totalCount);;
		return result;
	}

	@Transactional
	@Override
	public boolean runPythonVerification(RequestModifyBlogAddrsVO requestModifyBlogAddrsVO, String code) {
		String pythonOutput = PythonExecutor.runPython("C:\\Users\\User\\Desktop\\project\\brich-project\\src\\main\\resources\\static\\crawler\\verification-crawler.py", requestModifyBlogAddrsVO.getBlgAddrs(), code);
		int updateCount = 0;
		if(pythonOutput.contains("Verification successful")) {
			updateCount = userDao.updateBlgAddrsById(requestModifyBlogAddrsVO);
			}
		return updateCount > 0;
		
	}

	@Override
	public String generateVerificationCode() {
		return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}


	

}
