package com.ktdsuniversity.edu.domain.user.dao;

import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserLoginVO;
import com.ktdsuniversity.edu.domain.user.vo.request.RequestUserRegistVO;

public interface UserDao {

	public UserVO selectUserByLogIdAndAutr(RequestUserLoginVO requestUserLoginVO);

	public int insertNewUser(RequestUserRegistVO requestUserRegistVO);

	public int selectUserCountByLogId(String logId);

	public int selectUnblockUserByLogId(String logId);

	public int updateLoginFailCountByLogId(String logId);

	public int updateBlockByLogid(String logId);

	public int updateLoginSuccessByLogId(String logId);

}