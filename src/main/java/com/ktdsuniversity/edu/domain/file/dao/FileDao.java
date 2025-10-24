package com.ktdsuniversity.edu.domain.file.dao;

import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.file.vo.request.RequestDownloadVO;

public interface FileDao {

	public int insertFile(FileVO result);

	public int updateDownloadCount(RequestDownloadVO requestDownloadVO);

	public FileVO selectFileVO(RequestDownloadVO requestDownloadVO);

}