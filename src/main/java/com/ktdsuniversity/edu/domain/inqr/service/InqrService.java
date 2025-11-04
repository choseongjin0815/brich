package com.ktdsuniversity.edu.domain.inqr.service;

import java.util.List;

import com.ktdsuniversity.edu.domain.inqr.vo.request.RequestInqrCreateVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface InqrService {

	public List<CommonCodeVO> readInqrCategory();

	public boolean createNewInqr(RequestInqrCreateVO requestInqrCreateVO);

}