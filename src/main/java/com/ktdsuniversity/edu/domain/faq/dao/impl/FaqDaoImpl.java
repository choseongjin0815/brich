package com.ktdsuniversity.edu.domain.faq.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.faq.dao.FaqDao;

@Repository
public class FaqDaoImpl extends SqlSessionDaoSupport implements FaqDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.faq.dao.impl.FaqDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


}