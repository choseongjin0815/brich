package com.ktdsuniversity.edu.domain.report.dao;

import java.util.List;

import com.ktdsuniversity.edu.domain.report.vo.request.RequestReportCreateVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

public interface ReportDao {

	public List<CommonCodeVO> selectReportCategoryList();

	public int insertReport(RequestReportCreateVO requestReportCreateVO);

}