package com.ktdsuniversity.edu.domain.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.chat.controller.ChatController;
import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserAccountPasswordVO;
import com.ktdsuniversity.edu.domain.user.vo.response.ResponseUserInfoVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

/**
 * 블로거와 광고주의 계정 관리 페이지
 */
@Controller
@RequestMapping("/{auth}/account")
public class UserAccountController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(UserAccountController.class);

	
	/**
	 * 계정 정보 페이지
	 */
	@GetMapping("/info")
	public String viewAccountInfoPage(@SessionAttribute(name = "__LOGIN_USER__") UserVO loginUser
									, Model model) {
		String usrId = loginUser.getUsrId();
		
		ResponseUserInfoVO userInfo = this.userService.readUserByUserId(usrId);

		model.addAttribute("userInfo", userInfo);
		
		log.info("{}", userInfo.getAreaList());
		
		return "/user/account/info";
	}
	/**
	 * 비밀번호 재설정 페이지
	 */
	@GetMapping("/reset-password")
	public String viewResetPasswordPage(@SessionAttribute(name = "__LOGIN_USER__") UserVO loginUser) {
		String usrId = loginUser.getUsrId();
		return "/user/account/resetpassword";
	}
	
	/**
	 * 비밀번호 재설정 요청
	 */
	@PostMapping("/reset-password")
	@ResponseBody
	public AjaxResponse doResetPassword(RequestUserAccountPasswordVO requestUserAccountPasswordVO) {
		
		log.info("{}", requestUserAccountPasswordVO);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		
		
		return ajaxResponse;
	}
	
	/**
	 * 구독 정보 페이지 
	 */
	@GetMapping("/subscription-info") 
	public String viewSubscriptionInfoPage(@SessionAttribute(name = "__LOGIN_USER__") UserVO loginUser) {
		String usrId = loginUser.getUsrId();
		return "/user/account/subscriptioninfo";
	}
}
