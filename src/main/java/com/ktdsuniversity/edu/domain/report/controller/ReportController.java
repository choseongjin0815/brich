package com.ktdsuniversity.edu.domain.report.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.report.service.ReportService;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

}