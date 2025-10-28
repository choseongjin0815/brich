package com.ktdsuniversity.edu.domain.blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.blog.vo.RequestExpireSoonCampaignVO;
import com.ktdsuniversity.edu.domain.campaign.vo.ResponseExpireSoonListVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.util.PythonExecutor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDataController {

	@Autowired BlogDataService blogDataService;
	
	@GetMapping("/blog/{usrId}/dashboard")
	public String viewBlogDashBoard(@PathVariable String usrId, HttpSession session, Model model, RequestExpireSoonCampaignVO requestExpireSoonCampaignVO){
	    
		UserVO loginUser = (UserVO) session.getAttribute("__LOGIN_USER__");
	    if (loginUser == null || !loginUser.getUsrId().equals(usrId)) {
	        return "redirect:/access-denied";
	    }
		
		requestExpireSoonCampaignVO.setListSize(4);
		requestExpireSoonCampaignVO.setPageCount(1);
		ResponseExpireSoonListVO result = 
				this.blogDataService.readExpireSoonCampaignList(requestExpireSoonCampaignVO);
		model.addAttribute("list", result);
		model.addAttribute("user", loginUser);
		model.addAttribute("paginator", requestExpireSoonCampaignVO);
		return "blog/dashboard";
	}
	
	@GetMapping("/blog/{usrId}/manage")
	public String viewBlogManagePage(@PathVariable String usrId, HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("__LOGIN_USER__");
		if (loginUser == null || !loginUser.getUsrId().equals(usrId)) {
			System.out.println(usrId);
	        return "redirect:/access-denied";
	    }
		if(loginUser.getBlgAddrs() == null) {
			return "redirect:/blog/"+ loginUser.getUsrId() + "/verification";
		}
		
		return "/blog/manage";
	}
	
	@GetMapping("/blog/{usrId}/verification")
	public String viewBlogVerificationPage(@PathVariable String usrId, HttpSession session, Model model) {
		UserVO loginUser = (UserVO) session.getAttribute("__LOGIN_USER__");
	    if (loginUser == null || !loginUser.getUsrId().equals(usrId)) {
	        return "redirect:/access-denied";
	    }
	    
		return "/blog/verification";
	}
	

}
