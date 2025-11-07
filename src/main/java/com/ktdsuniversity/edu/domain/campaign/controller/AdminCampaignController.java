package com.ktdsuniversity.edu.domain.campaign.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ktdsuniversity.edu.domain.campaign.service.AdminCampaignService;
import com.ktdsuniversity.edu.domain.campaign.vo.request.RequestAdminSearchCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignListVO;
import com.ktdsuniversity.edu.domain.campaign.vo.response.ResponseAdminCampaignVO;

@Controller
public class AdminCampaignController {

	private static final Logger log = LoggerFactory.getLogger(AdminCampaignController.class);
	
	@Autowired
	private AdminCampaignService adminCampaignService;
	
	/**
	 * 캠페인 관리 - 목록 페이지
	 * @param requestAdminSearchCampaignVO
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/campaign-list")
	public String viewAdminCampaignListPage(RequestAdminSearchCampaignVO requestAdminSearchCampaignVO, Model model) {
		
		ResponseAdminCampaignListVO campaignListAndCategory = this.adminCampaignService.readAdminCampaignListAndCategory(requestAdminSearchCampaignVO);
		
		model.addAttribute("category", campaignListAndCategory.getCategoryList());
		model.addAttribute("campaignList", campaignListAndCategory.getResponseAdminCampaignList());
		model.addAttribute("search", requestAdminSearchCampaignVO);
		
		return "/campaign/admin_campaign_list";
	}
	
	@GetMapping("/admin/campaign-detail/{cmpnId}")
	public String viewAdminCampaignDetailPage(@PathVariable String cmpnId, Model model) {
		
		ResponseAdminCampaignVO detail = this.adminCampaignService.readAdminCampaignDetail(cmpnId);
		
    	log.info("어드민 캠페인 상세 보기 결과: " + detail.toString());
    	model.addAttribute("detail", detail);
    	
		return "/campaign/admin_campaign_detail";
	}
	
}
