package com.ktdsuniversity.edu.domain.pay.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.pay.dao.PayDao;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Repository
public class PayDaoImpl extends SqlSessionDaoSupport implements PayDao{
	
    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.pay.dao.impl.PayDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<CommonCodeVO> selectPayInfo() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectPayInfo");
	}
    
    
}
