package com.ktdsuniversity.edu.domain.blog.service.impl;

import com.ktdsuniversity.edu.domain.blog.dao.GoldenKeyWordDao;
import com.ktdsuniversity.edu.domain.blog.service.GoldenKeyWordService;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoldenKeyWordServiceImpl implements GoldenKeyWordService {

    private static final Logger log = LoggerFactory.getLogger(GoldenKeyWordServiceImpl.class);


    @Autowired
    private GoldenKeyWordDao goldenKeyWordDao;

    @Override
    public List<CommonCodeVO> selectUserCategoryKeywords(String usrId) {

        List<CommonCodeVO> result = goldenKeyWordDao.selectUserCategories(usrId);

        log.info("들어왔수다 {}", result);

        return result;
    }
}
