package com.ktdsuniversity.edu.domain.campaign.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.blog.controller.SearchBlogController;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseApplicantListVO;

@Controller
public class CampaignController {
	
	private static final Logger log = LoggerFactory.getLogger(CampaignController.class);
	
    @Autowired
    private CampaignService campaignService;
    
    @GetMapping("/campaigndetail/{campaignId}")
    public String campaignDetailPage(@PathVariable String campaignId, Model model,
    							 @SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser ) {
    	
    	CampaignVO detail = campaignService.readCampaignDetail(campaignId);
    	model.addAttribute("detail", detail);
    	return "campaign/campaigndetail";
    }
    
    @GetMapping("/campaignmain")
    public String campaignMainPage(RequestSearchCampaignVO requestSearchCampaignVO, Model model,
    						   @SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser){
    	
    	log.info( "입력 파라미터 값 : " + requestSearchCampaignVO.toString());
    	ResponseCampaignListVO CampaignListAndCategory = campaignService.readCampaignListAndCategory(requestSearchCampaignVO);
    	
    	model.addAttribute("category", CampaignListAndCategory.getCategoryList());
    	model.addAttribute("campaignList", CampaignListAndCategory.getResponseCampaignList());
    	model.addAttribute("search", requestSearchCampaignVO);
    	
    	log.info( "캠페인 리스트 조회결과 : " + CampaignListAndCategory.getResponseCampaignList().toString());
    	return "campaign/campaignmain";
    }
    
    
    @GetMapping("/adv/applicant/{cmpnId}")
    public String readApplicantList(Model model, @PathVariable String cmpnId) {
    	ResponseApplicantListVO applicantList = this.campaignService.readApplicantListById(cmpnId);
    	
    	model.addAttribute("applicantList", applicantList);
    	return "campaign/applicant";
    }
    
    @GetMapping("/adv/adoptChange")
    @ResponseBody
    public boolean doUpdateAdptYn(ApplicantVO applicantVO) {
    	boolean update = this.campaignService.updateAdptYnBycmpnApplyId(applicantVO);
    	
    	if (update) {
    		return true;
    	}
    	
    	else {
    		return false;
    	}
    }
}