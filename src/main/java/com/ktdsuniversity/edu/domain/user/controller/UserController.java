package com.ktdsuniversity.edu.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.blog.controller.SearchBlogController;
import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserFindIdVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserResetPasswordVO;
import com.ktdsuniversity.edu.global.common.AjaxResponse;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
	private static final Logger log = LoggerFactory.getLogger(SearchBlogController.class);

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
    public String doUserLoginAction(@Valid RequestUserLoginVO requestUserLoginVO
    		                      , BindingResult bindingResult
    		                      , Model model
    		                      , HttpSession httpSession
    		                      , @RequestParam String nextUrl) {
    	
    	if(bindingResult.hasErrors()) {
    		model.addAttribute("inputData", requestUserLoginVO);
    		return "/user/login";
    	}
    	UserVO loginUser = this.userService.readUser(requestUserLoginVO);
    	
    	if(loginUser != null) {
    		httpSession.setAttribute("__LOGIN_USER__", loginUser);
    	}
<<<<<<< HEAD
    	return "redirect:/campaignmain";

=======
    	return "redirect:" + nextUrl;
>>>>>>> aca9deb45842d7c9b0375e6318bc2129a8edab9f
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
    public String doUserRegistAction(@Valid RequestUserRegistVO requestUserRegistVO
    							   , BindingResult bindingResult
    							   , Model model) {
    	//서버단 Validation
    	if(bindingResult.hasErrors()) {
    	   	log.info("requestUserRegistVO: {}", requestUserRegistVO);
    		model.addAttribute("registData", requestUserRegistVO);
    		
    		//블로거 회원 가입 검증 실패
    		if(requestUserRegistVO.getAutr().equals("1002")) {
    			 model.addAttribute("role", "blogger");
    			 
    			 log.info("{}",model.getAttribute("registData"));
    			return "/user/regist";	
    		}
    		//광고주 회원 가입 검증 실패
    		else if(requestUserRegistVO.getAutr().equals("1007")){
   			 model.addAttribute("role", "advertiser");
    			return "/user/regist";	
    		}
    	}
    	//password 일치 검사 
    	if(!requestUserRegistVO.getPswrd().equals(requestUserRegistVO.getPswrdConfirm())) {
    		//블로거 비밀번호 확인 실패
    		if(requestUserRegistVO.getAutr().equals("1002")) {
    			 model.addAttribute("role", "blogger");
    			 model.addAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
    			return "/user/regist";	
    		}
    		//광고주 비밀번호 확인 실패
    		else if(requestUserRegistVO.getAutr().equals("1007")){
   			 model.addAttribute("role", "advertiser");
   			 model.addAttribute("passwordError", "비밀번호가 일치하지 않습니다.");
    			return "/user/regist";	
    		}
    	}
    	
    	boolean registResult = this.userService.createNewUser(requestUserRegistVO);
    	//가입이 완료되면 로그인 페이지로 이동시킨다.
    	return "redirect:/login";
    }
    
    @GetMapping("/duplicate-id/check")
    @ResponseBody
    public AjaxResponse doDuplicateEmailCheckAction (@RequestParam String logId) {
    	log.info("logId : {}", logId);
    	int count = this.userService.readUserIdByLogId(logId);
    	AjaxResponse ajaxResponse = new AjaxResponse();
    	ajaxResponse.setBody(count);
    	
    	if (!logId.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*]{8,16}$")) {
    		ajaxResponse.setBody(2);
    	} 
    	
    	return ajaxResponse;
    }
    
    @GetMapping("/find/id")
    public String viewFindIdPage() {
    	return "user/findid";
    }
    
    @GetMapping("/find/id/{nm}")
    public String viewFindIdSuccess(@PathVariable(required=false) String nm
    							  , HttpSession httpSession
    							  , Model model) {
    	//세션에 있는 인증되어 있는 이메일을 가져온다.
    	String email = (String)httpSession.getAttribute("verifiedEmail");
    	log.info("email: {}", email);
 

    	
    	RequestUserFindIdVO requestUserFindIdVO = new RequestUserFindIdVO();
    	requestUserFindIdVO.setEml(email);
    	requestUserFindIdVO.setNm(nm);
    	String logId = this.userService.readLogIdByNameAndEmail(requestUserFindIdVO);
    	
    	if(email == null || logId == null || nm == "non") {
    		model.addAttribute("checked", "해당하는 정보가 없습니다.");
    		return "user/findid";
    	}
    	int start = 3; 
    	int end = 7;   

    	String masked = logId.substring(0, start)   // 앞부분 그대로
    	               + "****"                   // 중간은 마스킹
    	               + logId.substring(end);      // 나머지 그대로
    	model.addAttribute("findedId", masked);
    	
    	return "user/findresult";
    }
    
    @GetMapping("/reset/password")
    public String viewResetPasswordCheckPage() {
    	return "/user/resetpassword";
    }
    
    @PostMapping("/reset/password")
    public String doPasswordResetAction(RequestUserResetPasswordVO resetPasswordInfo) {
    	boolean updateResult = this.userService.updatePswrdByLogIdAndPswrd(resetPasswordInfo);   	
    	return "redirect:/login";
    }
}