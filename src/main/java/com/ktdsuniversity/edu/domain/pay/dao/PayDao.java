package com.ktdsuniversity.edu.domain.pay.dao;

import java.util.List;

import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface PayDao {

	List<CommonCodeVO> selectPayInfo();

}
