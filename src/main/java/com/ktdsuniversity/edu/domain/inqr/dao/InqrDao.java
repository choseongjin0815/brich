package com.ktdsuniversity.edu.domain.inqr.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.inqr.vo.request.RequestInqrCreateVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface InqrDao {

	public List<CommonCodeVO> selectInqrCategory();

	public int insertInqr(RequestInqrCreateVO requestInqrCreateVO);

}