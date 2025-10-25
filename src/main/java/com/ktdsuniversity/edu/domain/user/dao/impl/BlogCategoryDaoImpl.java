package com.ktdsuniversity.edu.domain.user.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.user.dao.BlogCategoryDao;
import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;

@Repository
public class BlogCategoryDaoImpl extends SqlSessionDaoSupport implements BlogCategoryDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.user.dao.impl.BlogCategoryDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public int insertBlogCategory(BlogCategoryVO blogCategoryVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertBlogCategory", blogCategoryVO);
	}




}