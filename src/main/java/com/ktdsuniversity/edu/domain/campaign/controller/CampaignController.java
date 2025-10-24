package com.ktdsuniversity.edu.domain.campaign.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ktdsuniversity.edu.domain.campaign.vo.ApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseApplicantListVO;

@Controller
public class CampaignController {

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
    	CampaignListVO campaignListVO = campaignService.readCampaignList(requestSearchCampaignVO);
    	model.addAttribute("category", campaignListVO.category);
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