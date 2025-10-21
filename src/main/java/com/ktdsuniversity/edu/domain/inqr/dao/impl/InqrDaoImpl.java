package com.ktdsuniversity.edu.domain.inqr.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.inqr.dao.InqrDao;

@Repository
public class InqrDaoImpl extends SqlSessionDaoSupport implements InqrDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.inqr.dao.impl.InqrDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


}