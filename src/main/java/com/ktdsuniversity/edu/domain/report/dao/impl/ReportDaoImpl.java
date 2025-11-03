package com.ktdsuniversity.edu.domain.report.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.report.dao.ReportDao;
import com.ktdsuniversity.edu.domain.report.vo.ReportSearchVO;
import com.ktdsuniversity.edu.domain.report.vo.request.RequestReportCreateVO;
import com.ktdsuniversity.edu.domain.report.vo.response.ResponseMyReportInfoVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Repository
public class ReportDaoImpl extends SqlSessionDaoSupport implements ReportDao {
	
    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.report.dao.impl.ReportDaoImpl.";
    
    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<CommonCodeVO> selectReportCategoryList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectReportCategoryList");
	}

	@Override
	public int insertReport(RequestReportCreateVO requestReportCreateVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertReport", requestReportCreateVO);
	}

	@Override
	public List<ResponseMyReportInfoVO> selectReportListByUsrId(String usrId) {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectReportListByUsrId", usrId);
	}
	
	@Override
	public int selectMyReportCount(ReportSearchVO reportSearchVO) {
	    return super.getSqlSession().selectOne(this.NAME_SPACE + "selectMyReportCount", reportSearchVO);
	}

	@Override
	public List<ResponseMyReportInfoVO> selectMyReportListWithPaging(ReportSearchVO reportSearchVO) {
	    return super.getSqlSession().selectList(this.NAME_SPACE + "selectMyReportListWithPaging", reportSearchVO);
	}
}