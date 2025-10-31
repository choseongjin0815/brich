package com.ktdsuniversity.edu.domain.campaign.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.dao.AdminCategoryDao;
import com.ktdsuniversity.edu.domain.campaign.vo.AdminCampaignCategoryVO;

@Repository
public class AdminCategoryDaoImpl extends SqlSessionDaoSupport implements AdminCategoryDao {
	
	private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.campaign.dao.impl.AdminCategoryDaoImpl.";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<AdminCampaignCategoryVO> selectCampaignCategoryList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectCampaignCategoryList");
	}

}
