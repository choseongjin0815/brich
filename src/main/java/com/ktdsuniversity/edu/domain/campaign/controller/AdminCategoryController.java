package com.ktdsuniversity.edu.domain.campaign.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.domain.campaign.service.AdminCategoryService;
import com.ktdsuniversity.edu.domain.campaign.vo.AdminCampaignCategoryVO;

@Controller
public class AdminCategoryController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminCategoryController.class);
	
	@Autowired
	private AdminCategoryService adminCategoryService;
	
	@GetMapping("/admin/category_manage")
	public String viewAdminCategoryManagePage(Model model) {
		
		List<AdminCampaignCategoryVO> campaignCategoryList = this.adminCategoryService.readCampaignCategoryList();
		
		log.info("제발: " + campaignCategoryList.get(2).getLevel());
		log.info("제발: " + campaignCategoryList.get(2).getCdId());
		log.info("제발: " + campaignCategoryList.get(2).getCdNm());
		log.info("제발: " + campaignCategoryList.get(2).getPrntCdId());
		log.info("제발: " + campaignCategoryList.get(2).getPrntCdNm());
		log.info("제발: " + campaignCategoryList.get(2).getUseYn());
		log.info("제발: " + campaignCategoryList.get(2).getSrt());
		
		model.addAttribute("campaignCategoryList", campaignCategoryList);
		
		return "/campaign/admin_category_manage";
		
	}
	

}
