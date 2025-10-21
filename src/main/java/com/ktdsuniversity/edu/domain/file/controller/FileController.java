package com.ktdsuniversity.edu.domain.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.file.service.FileService;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

}