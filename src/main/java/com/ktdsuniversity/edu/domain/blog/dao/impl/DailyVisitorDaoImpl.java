package com.ktdsuniversity.edu.domain.blog.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.blog.dao.DailyVisitorDao;



@Repository
public class DailyVisitorDaoImpl extends SqlSessionDaoSupport implements DailyVisitorDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.blog.dao.impl.DailyVisitorDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


}