package com.ktdsuniversity.edu.domain.blog.service;


import java.util.List;

import com.ktdsuniversity.edu.domain.blog.vo.BlogIndexVO;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataInsertVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogIndexListVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestModifyBlogAddrsVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;

public interface BlogDataService {

	ResponseExpireSoonListVO readExpireSoonCampaignList(RequestExpireSoonCampaignVO requestExpireSoonCampaignVO);

	boolean runPythonVerification(RequestModifyBlogAddrsVO request, String code);

	String generateVerificationCode();

	boolean runPythonInitialPostData(String blgAddrs);

	boolean insertPostData(PostDataInsertVO post);

	List<BlogIndexVO> readBlogIndexList(String usrId);


}
