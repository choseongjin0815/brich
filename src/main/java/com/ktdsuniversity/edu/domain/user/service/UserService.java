package com.ktdsuniversity.edu.domain.user.service;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;

public interface UserService {

	public UserVO readUser(RequestUserLoginVO requestUserLoginVO);

}