package com.ktdsuniversity.edu.domain.blog.service;


import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.blog.vo.BlogDetailStatVO;
import com.ktdsuniversity.edu.domain.blog.vo.BlogIndexVO;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataInsertVO;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogIndexListVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestBlogInfoVO;
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

	List<PostDataVO> readPostStatsByDate(String usrId, String date);

	List<BlogIndexVO> readDailyIndex(String usrId, String date);

	boolean runPythonBlogStats(String blgAddrs);

	boolean runPythonBlogTitle(String blgAddrs);

	double selectMostRecentIndex(String usrId);

	List<BlogDetailStatVO> readBlogDetailStat(String usrId);





}
