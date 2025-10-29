package com.ktdsuniversity.edu.domain.blog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.domain.blog.dao.PostDataDao;
import com.ktdsuniversity.edu.domain.blog.service.BlogDataService;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataInsertVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestModifyBlogAddrsVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestPostDataResultVO;
import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class CrawlingController {

	@Autowired
	private PostDataDao postDataDao;
	
	@Autowired
	private BlogDataService blogDataService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/results")
	public String ResponseInitPostData(@RequestBody RequestPostDataResultVO request) {
	    
	    for (PostDataInsertVO post : request.getPostList()) {
	        post.setBlgAddrs(request.getBlgAddrs()); 

	        postDataDao.insertPostData(post);
	    }

	    return "success";
	}
	
    @PostMapping("/verify-code")
    public Map<String, Object> generateVerificationCode(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        String code = blogDataService.generateVerificationCode();
        session.setAttribute("VERIFY_CODE", code); // 세션에 저장
        result.put("code", code);
        return result;
    }
	
	@PostMapping("/verify")
	public Map<String, Object> ResponseVerifyBlogger(@RequestBody Map<String, String> request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        RequestModifyBlogAddrsVO blogInfo = new RequestModifyBlogAddrsVO();
        blogInfo.setBlgAddrs(request.get("blogUrl"));
        blogInfo.setId(request.get("usrId"));

        String code = (String) session.getAttribute("VERIFY_CODE");
        boolean verified = blogDataService.runPythonVerification(blogInfo, code);
        
        

        result.put("success", verified);
        return result;

	}
}
