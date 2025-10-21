package com.ktdsuniversity.edu.domain.report.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.report.dao.PenaltyHistoryDao;

@Repository
public class PenaltyHistoryDaoImpl extends SqlSessionDaoSupport implements PenaltyHistoryDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.report.dao.impl.PenaltyHistoryDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }


}