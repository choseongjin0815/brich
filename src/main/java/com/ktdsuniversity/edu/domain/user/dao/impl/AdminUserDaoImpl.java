package com.ktdsuniversity.edu.domain.user.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.user.dao.AdminUserDao;
import com.ktdsuniversity.edu.domain.user.vo.AdminAdvertiserDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminBloggerDetailVO;
import com.ktdsuniversity.edu.domain.user.vo.AdminUserListVO;
import com.ktdsuniversity.edu.domain.user.vo.BlogCategoryVO;
import com.ktdsuniversity.edu.domain.user.vo.UserAreaVO;

@Repository
public class AdminUserDaoImpl extends SqlSessionDaoSupport implements AdminUserDao {
	
	private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.user.dao.impl.AdminUserDaoImpl.";
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<AdminUserListVO> selectAdminUserList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectAdminUserList");
	}

	@Override
	public String selectAdminUserAutrById(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminUserAutrById", usrId);
	}

	@Override
	public AdminBloggerDetailVO selectAdminBloggerDetailById(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminBloggerDetailById", usrId);
	}

	@Override
	public List<CampaignVO> selectBloggerCmpnProgressList(String usrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CampaignVO> selectBloggerCmpnCompletedList(String usrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAreaVO> selectBloggerAreaList(String usrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogCategoryVO> selectBloggerCategoryList(String usrId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AdminAdvertiserDetailVO selectAdminAdvertiserDetailById(String usrId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectAdminAdvertiserDetailById", usrId);
	}

	@Override
	public List<CampaignVO> selectAdvertiserCmpnProgressList(String usrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CampaignVO> selectAdvertiserCmpnCompletedList(String usrId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileGroupVO> selectAdminUserFileList(String usrId) {
		// TODO Auto-generated method stub
		return null;
	}

}
