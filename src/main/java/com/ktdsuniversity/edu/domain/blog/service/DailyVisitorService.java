package com.ktdsuniversity.edu.domain.blog.service;

import com.ktdsuniversity.edu.domain.blog.vo.DailyVisitorVO;

import java.util.List;

public interface DailyVisitorService {

    List<DailyVisitorVO> selectDailyVisitors(String usrId);
}
