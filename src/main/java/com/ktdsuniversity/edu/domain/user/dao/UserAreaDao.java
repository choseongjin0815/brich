package com.ktdsuniversity.edu.domain.user.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.user.vo.UserAreaVO;
import com.ktdsuniversity.edu.global.common.AreaCode;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface UserAreaDao {

	public List<AreaCode> selectUserAreaByUserId(String usrId);

}