package com.ktdsuniversity.edu.domain.blog.service;

import com.ktdsuniversity.edu.global.common.CommonCodeVO;

import java.util.List;

public interface GoldenKeyWordService {
    List<CommonCodeVO> selectUserCategoryKeywords(String usrId);
}
