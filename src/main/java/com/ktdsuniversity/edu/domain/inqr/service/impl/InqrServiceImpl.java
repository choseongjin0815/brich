package com.ktdsuniversity.edu.domain.inqr.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.inqr.dao.InqrDao;
import com.ktdsuniversity.edu.domain.inqr.service.InqrService;

@Service
public class InqrServiceImpl implements InqrService {

    @Autowired
    private InqrDao inqrDao;

}