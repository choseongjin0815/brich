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
    
    /**
     * 임시 메인 페이지
     * @return 메인 페이지 view
     */
    @GetMapping("/main")
    public String viewMainPage() {
    	return "main";
    }
    /**
     * 로그인 페이지 
     * @return 로그인 페이지
     */
    @GetMapping("/login")
    public String viewLoginPage() {
    	return "/user/login";
    }
    
    /**
     * 
     * @param httpSession 로그인 정보를 담아줄 세션 
     * @param nextUrl 로그인 이후 원래 보려 했던 페이지로 이동하기 위한 url정보
     * @param requestUserLoginVO 로그인에 필요한 아이디와 이메일
     * @return 직접 로그인 경로에 로그인 한 경우는 각 권한별 메인페이지, 
     * 		   서비스를 이용하다가 세션이 끊긴 경우라면 원래 보던 url로 이동
     */
    @PostMapping("/login")
    public String doUserLoginAction(HttpSession httpSession, @RequestParam String nextUrl, RequestUserLoginVO requestUserLoginVO) {
    	
    	UserVO loginUser = this.userService.readUser(requestUserLoginVO);
    	
    	if(loginUser != null) {
    		httpSession.setAttribute("__LOGIN_USER__", loginUser);
    	}
    	return "redirect:/campaignmain";

    }

    /**
     * 로그아웃 
     * @param httpSession 로그인 정보를 담은 세션 
     * @return 로그인 페이지로 이동(추후 수정) 
     */
    @GetMapping("/logout")
	public String doLogoutAction(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/login";
	}
    
    /**
     * 회원 가입 유형 선택 페이지 (광고주, 블로거)
     * @return 회원 가입 유형 선택 view
     */
    @GetMapping("/choose-role")
    public String viewChooseRolePage() {
    	System.out.println("choose-role");
    	return "/user/chooserole";
    }
    
    /**
     * 이용약관 동의 페이지 
     * @param role 블로거, 광고주 어떤 유형으로 가입하려는지 담은 파라미터
     * @param model 권한 데이터를 담아줄 Model 
     * @return 이용약관 동의 view 
     */
    @GetMapping("/terms/{role}")
    public String viewTermPage(@PathVariable String role, Model model) {
    	if(role.equals("blogger") || role.equals("advertiser")) {
    		model.addAttribute("role", role);
    		return "/user/term";
    	}
    	//에러 처리 할 예정
    	return null;
    	
    }
    
    /**
     * 회원가입 페이지
     * @param role 블로거, 광고주 어떤 유형으로 가입하려는지 담은 파라미터
     * @param model 권한 데이터를 담아줄 Model 
     * @return 회원가입 view
     */
    @GetMapping("/regist/{role}")
    public String viewRegistPage(@PathVariable String role, Model model) {
    	if(role.equals("blogger") || role.equals("advertiser")) {
    		model.addAttribute("role", role);
    	} else {
    		return null;
    	}
    	return"/user/regist";
    }
    
    /**
     * 회원가입
     * @param requestUserRegistVO 회원가입에 필요한 데이터를 담은 VO
     * @return 각 권한별 메인 페이지로 이동시켜줄 예정
     */
    @PostMapping("/regist")
    public String doUserRegistAction(RequestUserRegistVO requestUserRegistVO) {
    	System.out.println(requestUserRegistVO);
    	boolean registResult = this.userService.createNewUser(requestUserRegistVO);
    	return "";
    }
}