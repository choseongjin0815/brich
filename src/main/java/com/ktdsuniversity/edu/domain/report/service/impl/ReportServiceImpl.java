package com.ktdsuniversity.edu.domain.report.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ktdsuniversity.edu.domain.file.dao.FileDao;
import com.ktdsuniversity.edu.domain.file.dao.FileGroupDao;
import com.ktdsuniversity.edu.domain.file.util.MultipartFileHandler;
import com.ktdsuniversity.edu.domain.file.vo.FileGroupVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;
import com.ktdsuniversity.edu.domain.report.controller.ReportController;
import com.ktdsuniversity.edu.domain.report.dao.ReportDao;
import com.ktdsuniversity.edu.domain.report.service.ReportService;
import com.ktdsuniversity.edu.domain.report.vo.ReportSearchVO;
import com.ktdsuniversity.edu.domain.report.vo.request.RequestReportCreateVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseMyReportInfoVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseReportVO;
import com.ktdsuniversity.edu.domain.user.dao.UserDao;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;
import com.ktdsuniversity.edu.global.util.SessionUtil;

@Service
public class ReportServiceImpl implements ReportService {

	private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

	 @Autowired
	 private MultipartFileHandler multipartFileHandler;
	
    @Autowired
    private ReportDao reportDao;
    
    @Autowired
    private UserDao userDao;
    
	@Autowired
	private FileGroupDao fileGroupDao;
	
	@Autowired
	private FileDao fileDao;

	@Override
	public ResponseReportVO readTargetInfoByTargetUsrId(String targetUsrId) {
		
		ResponseReportVO report = new ResponseReportVO();
		
		//신고의 카테고리 목록을 받아온다.
		List<CommonCodeVO> reportCategory = this.reportDao.selectReportCategoryList();
		//신고대상의 정보를 받아온다.
		UserVO user = this.userDao.selectUserByUserId(targetUsrId);
				
		report.setTargetId(targetUsrId);
		report.setRptCategory(reportCategory);
		
		//상대가 광고주면 사업자명 등록
		if(user.getAutr().equals("1004")) {
			report.setTargetName(user.getCmpny());
		}
		else {
			report.setTargetName(user.getNm());
		}
		
		return report;
	}

	@Override
	public boolean createNewReport(RequestReportCreateVO requestReportCreateVO) {
    	List<FileVO> uploadResult = this.multipartFileHandler.upload(requestReportCreateVO.getFile());
    	
    	if(uploadResult != null && uploadResult.size() > 0) {
			//1.File Group Insert
			FileGroupVO fileGroupVO = new FileGroupVO();
			fileGroupVO.setFlCnt(uploadResult != null ? uploadResult.size(): 0);
			int insertGroupCount = this.fileGroupDao.insertFileGroup(fileGroupVO);
			
			//2.File Insert
			
			for(FileVO result : uploadResult) {
				result.setFlGrpId(fileGroupVO.getFlGrpId());
				int insertFileCount = this.fileDao.insertFile(result);
			}
			//게시글에 첨부되어있는 파일 그룹의 아이디가 무엇인지 알수있다.
			requestReportCreateVO.setRptFlGrpId(fileGroupVO.getFlGrpId());
		}
    	
    	int insertResult = this.reportDao.insertReport(requestReportCreateVO);

		return insertResult > 0;
	}

	@Override
	public List<ResponseMyReportInfoVO> selectListByUsrId(String usrId) {
		return this.reportDao.selectReportListByUsrId(usrId);
	}
	
	@Override
	public List<ResponseMyReportInfoVO> readMyReportListWithPaging(ReportSearchVO reportSearchVO) {
	    // 1. 총 신고 건수 조회
	    int reportCount = this.reportDao.selectMyReportCount(reportSearchVO);
	    
	    // 2. 페이지 정보 설정
	    reportSearchVO.setPageCount(reportCount);
	    
	    // 3. 신고 목록 조회
	    List<ResponseMyReportInfoVO> reportList = this.reportDao.selectMyReportListWithPaging(reportSearchVO);
	    
	    return reportList;
	}

}