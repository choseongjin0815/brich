package com.ktdsuniversity.edu.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 블로거와 광고주의 계정 관리 페이지
 */
@Controller
@RequestMapping("/{auth}/account")
public class UserAccountController {
	
	@GetMapping("/info")
	public String viewAccountInfoPage() {
		return "";
	}
	
	@GetMapping("/reset-password")
	public String viewResetPasswordPage() {
		return "";
	}
	
	@GetMapping("/subscription-info") 
	public String viewSubscriptionInfoPage() {
		return "/user/account/subscriptioninfo";
	}
}
