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
import com.ktdsuniversity.edu.domain.blog.vo.RequestPostDataResultVO;

@RestController
@RequestMapping("/api")
public class CrawlingController {

	@Autowired
	private PostDataDao postDataDao;
	
	@Autowired
	private BlogDataService blogDataService;
	
	@PostMapping("/results")
	public String ResponseInitPostData(@RequestBody RequestPostDataResultVO request) {
	    
	    for (PostDataInsertVO post : request.getPostList()) {
	        post.setBlgAddrs(request.getBlgAddrs()); 

	        postDataDao.insertPostData(post);
	    }

	    return "success";
	}
	
	
	@PostMapping("/verify")
	public Map<String, Object> ResponseVerifyBlogger(@RequestBody Map<String, String> request) {
        Map<String, Object> result = new HashMap<>();
        String blogUrl = request.get("blogUrl");
        String usrId = request.get("usrId");

        boolean verified = blogDataService.runPythonVerification(blogUrl, usrId);
        result.put("success", verified);
        return result;

	}
}
