package com.ktdsuniversity.edu.domain.blog.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.domain.blog.dao.PostDataDao;
import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.blog.vo.BlogIndexVO;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataInsertVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogIndexListVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestModifyBlogAddrsVO;
import com.ktdsuniversity.edu.domain.campaign.dao.CampaignDao;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;
import com.ktdsuniversity.edu.domain.user.dao.UserDao;
import com.ktdsuniversity.edu.global.util.PythonExecutor;



@Service
public class BlogDataServiceImpl implements BlogDataService{

	private final String NAME_SPACE = "C:\\Users\\User\\Desktop\\project\\brich-project\\src\\main\\resources\\static\\crawler\\";
	
	@Autowired
	private CampaignDao campaignDao;
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PostDataDao postDataDao;
	
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
		String pythonOutput = PythonExecutor.runPython(NAME_SPACE + "verification-crawler.py", requestModifyBlogAddrsVO.getBlgAddrs(), code);
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

	@Override
	public boolean runPythonInitialPostData(String blgAddrs) {
		String pythonOutput = PythonExecutor.runPython(NAME_SPACE + "post-data-signup-crawler.py", blgAddrs);
		
		return false;
	}

	@Transactional
	@Override
	public boolean insertPostData(PostDataInsertVO post) {
		int insertCount = postDataDao.insertPostData(post); 
		
		return insertCount > 0;
	}

	@Override
	public List<BlogIndexVO> readBlogIndexList(String usrId) {
		List<BlogIndexVO> list = postDataDao.selectBlogIndex(usrId);
		
		return list;
	}


	

}
