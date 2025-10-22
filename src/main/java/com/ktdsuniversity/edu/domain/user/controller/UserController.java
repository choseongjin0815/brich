package com.ktdsuniversity.edu.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;

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
    public String doUserLoginAction(HttpSession httpSession, RequestUserLoginVO requestUserLoginVO) {
    	
    	UserVO loginUser = this.userService.readUser(requestUserLoginVO);
    	
    	if(loginUser != null) {
    		httpSession.setAttribute("__LOGIN_USER__", loginUser);
    	}
//    	return "redirect:/main";
    	return "redirect:/campaignmain";
    }

}