package com.ktdsuniversity.edu.domain.report.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.report.dao.ReportDao;

@Repository
public class ReportDaoImpl extends SqlSessionDaoSupport implements ReportDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.report.dao.impl.ReportDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


}