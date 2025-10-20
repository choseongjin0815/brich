package com.ktdsuniversity.edu.domain.report.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.report.dao.ReportDao;
import com.ktdsuniversity.edu.domain.report.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

}