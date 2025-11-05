package com.ktdsuniversity.edu.domain.pay.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.pay.dao.PayDao;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.domain.pay.vo.response.ResponsePaymentVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

@Repository
public class PayDaoImpl extends SqlSessionDaoSupport implements PayDao{
	
    private final String NAME_SPACE = "com.ktdsuniversity.edu.domain.pay.dao.impl.PayDaoImpl.";

    @Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

	@Override
	public List<CommonCodeVO> selectPayInfoList() {
		return super.getSqlSession().selectList(this.NAME_SPACE + "selectPayInfoList");
	}

	@Override
	public CommonCodeVO selectPayInfo(String cdId) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectPayInfo", cdId);
	}

	@Override
	public int insertBeforeSubscribePaymentInfoSave(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertBeforeSubscribePaymentInfoSave" , requestPaymentVO);
	}

	@Override
	public int insertBeforeCampaignPaymentInfoSave(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().insert(this.NAME_SPACE + "insertBeforeCampaignPaymentInfoSave" , requestPaymentVO);
	}

	@Override
	public ResponsePaymentVO selectBeforeSaveInfo(String pKkey) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectBeforeSaveInfo" , pKkey);
	}

	@Override
	public int updatePaymentSuccessSubscribe(RequestPaymentVO requestPaymentVO) {
		return super.getSqlSession().update(this.NAME_SPACE + "updatePaymentSuccessSubscribe", requestPaymentVO);
	}

	@Override
	public String selectSbscrptnCd(String easyAmount) {
		return super.getSqlSession().selectOne(this.NAME_SPACE + "selectSbscrptnCd", easyAmount);
	}
    
    
}
