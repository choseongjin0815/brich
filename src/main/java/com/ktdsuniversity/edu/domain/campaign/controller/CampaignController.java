package com.ktdsuniversity.edu.domain.campaign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.campaign.service.CampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestApplicantVO;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdoptListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseApplicantListVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;

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
    public String readApplicantList(Model model, @PathVariable String cmpnId,
    								RequestApplicantVO requestApplicantVO) {
    	// TODO 캠페인 주인과 세션이 다를 때 접근 막을 것
//    	if (!board.getEmail().equals(loginUser.getEmail())) {
//			throw new HelloSpringException("잘못된 접근입니다.", "error/403");
//		}
    	
    	requestApplicantVO.setCmpnId(cmpnId);
    	if (requestApplicantVO.getOrder() != null) {
    		requestApplicantVO.setOrder(requestApplicantVO.getOrder().toUpperCase());
    	}
    	ResponseApplicantListVO applicantList = this.campaignService.readApplicantListById(requestApplicantVO);
    	
    	model.addAttribute("applicantList", applicantList);
    	model.addAttribute("search", requestApplicantVO);
    	
    	return "campaign/applicant";
    }
    
    @GetMapping("/adv/adoptChange")
    @ResponseBody
    public boolean doUpdateAdptYnAction(RequestApplicantVO requestApplicantVO,
    									@SessionAttribute(value="__LOGIN_USER__") UserVO loginUser) {
    	requestApplicantVO.setUsrId(loginUser.getUsrId());
    	System.out.println(requestApplicantVO);
    	boolean update = this.campaignService.updateAdptYnBycmpnApplyId(requestApplicantVO);
    	
    	if (update) {
    		return true;
    	}
    	
    	else {
    		return false;
    	}
    }
    
    @GetMapping("/adv/adopt/{cmpnId}")
    public String readAdoptList(Model model, @PathVariable String cmpnId,
    		RequestApplicantVO requestApplicantVO) {
    	log.info("com");
    	requestApplicantVO.setCmpnId(cmpnId);
    	
    	ResponseAdoptListVO adoptList = this.campaignService.readResponseAdoptListByCmpnId(requestApplicantVO);
    	model.addAttribute("adoptList", adoptList);
    	System.out.println(adoptList);
    	return "campaign/adopt";
    }
}