package com.ktdsuniversity.edu.domain.user.dao;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;

public interface UserDao {

	public UserVO selectUserByLogIdAndPwd(RequestUserLoginVO requestUserLoginVO);

	public int insertNewUser(RequestUserRegistVO requestUserRegistVO);

}