package com.ktdsuniversity.edu.domain.blog.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.blog.vo.BlogIndexVO;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataVO;

public interface PostDataDao {

	int insertPostData(PostDataVO post);

	List<BlogIndexVO> selectBlogIndex(String usrId);

}