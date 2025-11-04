package com.ktdsuniversity.edu.domain.inqr.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.inqr.dao.InqrDao;
import com.ktdsuniversity.edu.domain.inqr.service.InqrService;
import com.ktdsuniversity.edu.domain.inqr.vo.request.RequestInqrCreateVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Service
public class InqrServiceImpl implements InqrService {

	 @Autowired
	 private MultipartFileHandler multipartFileHandler;
	
	@Autowired
	private InqrDao inqrDao;

	@Autowired
	private FileGroupDao fileGroupDao;
	
	@Autowired
	private FileDao fileDao;
	
	@Override
	public List<CommonCodeVO> readInqrCategory() {
		return this.inqrDao.selectInqrCategory();
	}

	@Override
	public boolean createNewInqr(RequestInqrCreateVO requestInqrCreateVO) {

		List<FileVO> uploadResult = this.multipartFileHandler.upload(requestInqrCreateVO.getFile());

		if (uploadResult != null && uploadResult.size() > 0) {
			// 1.File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(uploadResult != null ? uploadResult.size() : 0);
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);

			// 2.File Insert

			for (FileVO result : uploadResult) {
				result.setFlGrpId(fileGroupVO.getFlGrpId());
				int insertFileCount = this.fileDao.insertFile(result);
			}
			// 게시글에 첨부되어있는 파일 그룹의 아이디가 무엇인지 알수있다.
			requestInqrCreateVO.setInqrFlGrpId(fileGroupVO.getFlGrpId());
		}

		int insertResult = this.inqrDao.insertInqr(requestInqrCreateVO);
		
		return insertResult > 0;
	}

}