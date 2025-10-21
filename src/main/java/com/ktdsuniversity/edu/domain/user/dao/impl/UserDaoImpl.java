package com.ktdsuniversity.edu.domain.user.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.user.dao.UserDao;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.user.dao.impl.UserDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public UserVO selectUserByLogIdAndPwd(RequestUserLoginVO requestUserLoginVO) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectUserByLogIdAndPwd", requestUserLoginVO);
	}

	@Override
	public int insertNewUser(RequestUserRegistVO requestUserRegistVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertNewUser", requestUserRegistVO);
	}


}