package com.ktdsuniversity.edu.domain.file.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

}