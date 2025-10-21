package com.ktdsuniversity.edu.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/main")
    public String viewMainPage() {
    	
    	return "main";
    }
    
    @GetMapping("/login")
    public String viewLoginPage() {
    	return "/user/login";
    }
    
    @PostMapping("/login")
    public String doUserLoginAction(HttpSession httpSession, @RequestParam String nextUrl, RequestUserLoginVO requestUserLoginVO) {
    	
    	UserVO loginUser = this.userService.readUser(requestUserLoginVO);
    	
    	if(loginUser != null) {
    		httpSession.setAttribute("__LOGIN_USER__", loginUser);
    	}
    	return "redirect:" + nextUrl;
    }

    @GetMapping("/logout")
	public String doLogoutAction(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/login";
	}
    
    //회원가입 유형 선택 페이지
    @GetMapping("/choose-role")
    public String viewChooseRolePage() {
    	System.out.println("choose-role");
    	return "/user/chooserole";
    }
    
    @GetMapping("/terms/{role}")
    public String viewTermPage(@PathVariable String role, Model model) {
    	if(role.equals("blogger") || role.equals("advertiser")) {
    		model.addAttribute("role", role);
    		return "/user/term";
    	}
    	//에러 처리 할 예정
    	return null;
    	
    }
    
    @GetMapping("/regist/{role}")
    public String viewRegistPage(RequestUserRegistVO requestUserRegistVO,
    							 @PathVariable String role,
    							 Model model) {
    	if(role.equals("blogger") || role.equals("advertiser")) {
    		model.addAttribute("role", role);
    	} else {
    		return null;
    	}
    	return"/user/regist";
    }
    
    @PostMapping("/regist")
    public String doUserRegistAction(RequestUserRegistVO requestUserRegistVO) {
    	System.out.println(requestUserRegistVO);
    	boolean registResult = this.userService.createNewUser(requestUserRegistVO);
    	return "";
    }
}