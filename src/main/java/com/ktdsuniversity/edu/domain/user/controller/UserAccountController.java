package com.ktdsuniversity.edu.domain.user.controller;

import java.util.Map;

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
import com.ktdsuniversity.edu.domain.user.vo.response.ResponseUserSubscriptionInfoVO;
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
	public AjaxResponse doResetPassword(@SessionAttribute(name = "__LOGIN_USER__") UserVO loginUser
			                          , RequestUserAccountPasswordVO requestUserAccountPasswordVO) {
		
		String usrId = loginUser.getUsrId();
		requestUserAccountPasswordVO.setUsrId(usrId);
		log.info("{}", requestUserAccountPasswordVO);
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		
		if(requestUserAccountPasswordVO.getCurrentPswrd().equals("")|| 
		   requestUserAccountPasswordVO.getNewPswrd().equals("") ||
		   requestUserAccountPasswordVO.getCurrentPswrd().equals("")) {
		   ajaxResponse.setBody(Map.of("result", "모든 값을 입력해야합니다."));
    	   return ajaxResponse;
		} 
		
		if (!requestUserAccountPasswordVO.getNewPswrd().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*]{8,16}$")) {
    		ajaxResponse.setBody(Map.of("result", "비밀번호는 8~16자까지 가능하며 영문자와 숫자를 혼합해야합니다."));
    		return ajaxResponse;
    	} 
		
		if(!requestUserAccountPasswordVO.getNewPswrd().equals(requestUserAccountPasswordVO.getNewPswrdConfirm())) {
			ajaxResponse.setBody(Map.of("result", "비밀번호가 일치하지 않습니다."));
			return ajaxResponse;
		}
		
		boolean updatePassword = this.userService.updatePswrdByUsrId(requestUserAccountPasswordVO);
		
		//입력한 기존 비밀번호가 DB에 저장된 비밀번호와 같지 않은 경우
		if(updatePassword == false) {
			ajaxResponse.setBody(Map.of("result", "현재 비밀번호가 일치하지 않습니다."));
			return ajaxResponse;
		}
		
		ajaxResponse.setBody(Map.of("result", "비밀번호가 재설정 되었습니다!"));
		
		
		return ajaxResponse;
	}
	
	/**
	 * 구독 정보 페이지 
	 */
	@GetMapping("/subscription-info") 
	public String viewSubscriptionInfoPage(@SessionAttribute(name = "__LOGIN_USER__") UserVO loginUser
										, Model model) {  
		String usrId = loginUser.getUsrId();
		String auth = loginUser.getAutr();
		log.info("auth {}", auth);
		if(auth.equals("1002")) {
			ResponseUserSubscriptionInfoVO subscriptionInfo = this.userService.readSubscriptionInfoByUserId(usrId);
			log.info("subInfo:  {}", subscriptionInfo);
			model.addAttribute("subInfo", subscriptionInfo);
		}
		
		return "/user/account/subscriptioninfo";
	}
}
