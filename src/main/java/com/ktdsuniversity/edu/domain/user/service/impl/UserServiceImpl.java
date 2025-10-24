package com.ktdsuniversity.edu.domain.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.user.dao.UserDao;
import com.ktdsuniversity.edu.domain.user.service.UserService;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
	public UserVO readUser(RequestUserLoginVO requestUserLoginVO) {
		UserVO usrVO = this.userDao.selectUserByLogIdAndPwd(requestUserLoginVO);
		
		return usrVO;
		
	}

    @Transactional
	@Override
	public boolean createNewUser(RequestUserRegistVO requestUserRegistVO) {
		int insertResult= this.userDao.insertNewUser(requestUserRegistVO);
		return insertResult > 0;
	}

}