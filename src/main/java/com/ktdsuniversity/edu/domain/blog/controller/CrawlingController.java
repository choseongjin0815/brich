package com.ktdsuniversity.edu.domain.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ktdsuniversity.edu.domain.blog.dao.PostDataDao;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataVO;
import com.ktdsuniversity.edu.domain.blog.vo.RequestPostDataResultVO;

@RestController
@RequestMapping("/api")
public class CrawlingController {

	@Autowired
	private PostDataDao postDataDao;
	
	@PostMapping("/results")
	public String ResponseInitPostData(@RequestBody RequestPostDataResultVO request){
		
		for(PostDataVO post: request.getPostList()) {
			post.setUsrId(request.getUsrId());
			postDataDao.insertPostData(post);
		}
		
		return "success";
	}
}
