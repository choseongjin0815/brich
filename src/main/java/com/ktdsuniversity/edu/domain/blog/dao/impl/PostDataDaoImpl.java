package com.ktdsuniversity.edu.domain.blog.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.blog.dao.PostDataDao;
import com.ktdsuniversity.edu.domain.blog.vo.PostDataVO;


@Repository
public class PostDataDaoImpl extends SqlSessionDaoSupport implements PostDataDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.blog.dao.impl.PostDataDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public void insertPostData(PostDataVO post) {
		super.getSqlSession().insert(this.NAME_SPACE + "insertPostData", post);
	}


}