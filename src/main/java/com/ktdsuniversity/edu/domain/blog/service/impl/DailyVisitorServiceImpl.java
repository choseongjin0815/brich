package com.ktdsuniversity.edu.domain.blog.service.impl;

import com.ktdsuniversity.edu.domain.blog.dao.DailyVisitorDao;
import com.ktdsuniversity.edu.domain.blog.service.DailyVisitorService;
import com.ktdsuniversity.edu.domain.blog.vo.DailyVisitorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyVisitorServiceImpl implements DailyVisitorService {

    @Autowired
    private DailyVisitorDao dailyVisitorDao;


    @Override
    public List<DailyVisitorVO> selectDailyVisitors(String usrId) {
        List<DailyVisitorVO> list = dailyVisitorDao.selectDailyVisitor(usrId);
        return list;
    }
}
