package com.ktdsuniversity.edu.domain.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;

@Controller
public class BlogDataController {

	@Autowired BlogDataService blogDataService;
	
	@GetMapping("/blog/dashboard/{usrId}")
	public String viewBlogDashBoard(Model model, RequestExpireSoonCampaignVO requestExpireSoonCampaignVO){
		requestExpireSoonCampaignVO.setListSize(4);
		requestExpireSoonCampaignVO.setPageCount(1);
		ResponseExpireSoonListVO result = 
				this.blogDataService.readExpireSoonCampaignList(requestExpireSoonCampaignVO);
		model.addAttribute("list", result);
		
		model.addAttribute("search", requestExpireSoonCampaignVO);
		return "blog/dashboard";
	}
	
}
