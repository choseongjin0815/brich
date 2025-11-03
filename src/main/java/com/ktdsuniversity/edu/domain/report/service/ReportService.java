package com.ktdsuniversity.edu.domain.report.service;

import com.ktdsuniversity.edu.domain.report.vo.request.RequestReportCreateVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseReportVO;

public interface ReportService {

	public ResponseReportVO readTargetInfoByTargetUsrId(String targetUsrId);

	public boolean createNewReport(RequestReportCreateVO requestReportCreateVO);

}