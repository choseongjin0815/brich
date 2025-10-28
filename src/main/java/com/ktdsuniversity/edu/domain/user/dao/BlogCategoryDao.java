package com.ktdsuniversity.edu.domain.user.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;

public interface BlogCategoryDao {

	List<BlogCategoryVO> selectUserBlogCategoryById(String usrId);

	int updateBlogCategoryAsDelete(Map<String, Object> deleteParamMap);

	List<String> selectDeletedCategoryById(Map<String, Object> searchParamMap);

	int updateCategoryAsReactive(Map<String, Object> reactiveParamMap);

	int insertNewBlogCategory(Map<String, Object> insertParamMap);

}