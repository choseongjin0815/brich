package com.ktdsuniversity.edu.domain.campaign.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName CMPN_PYMNT
 * @TableComment null
 */
public class CampaignPaymentVO extends BaseVO{

    /**
     * @ColumnName CMPN_PYMNT_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 결제를  구분지을 고유한 아이디
     */
    private String cmpnPymntId;

    /**
     * @ColumnName CMPN_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 캠페인 테이블 PK
     */
    private String cmpnId;

    /**
     * @ColumnName PYMNT_CD
     * @ColumnType CHAR(4)
     * @ColumnComment 공통 테이블의 결제 상태코드
     */
    private String pymntCd;

    /**
     * @ColumnName AMNT
     * @ColumnType NUMBER(7, 0)
     * @ColumnComment 캠페인 결제 금액
     */
    private int amnt;

    /**
     * @ColumnName PG_PYMNT_CD
     * @ColumnType VARCHAR2(100)
     * @ColumnComment 결제 대행사에서 주는 코드
     */
    private String pgPymntCd;

  
    public String getCmpnPymntId() {
        return this.cmpnPymntId;
    }
    
    public void setCmpnPymntId(String cmpnPymntId) {
        this.cmpnPymntId = cmpnPymntId;
    }
    
    public String getCmpnId() {
        return this.cmpnId;
    }
    
    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }
    
    public String getPymntCd() {
        return this.pymntCd;
    }
    
    public void setPymntCd(String pymntCd) {
        this.pymntCd = pymntCd;
    }
    
    public int getAmnt() {
        return this.amnt;
    }
    
    public void setAmnt(int amnt) {
        this.amnt = amnt;
    }
    
    public String getPgPymntCd() {
        return this.pgPymntCd;
    }
    
    public void setPgPymntCd(String pgPymntCd) {
        this.pgPymntCd = pgPymntCd;
    }

	@Override
	public String toString() {
		return "CampaignPaymentVO [cmpnPymntId=" + cmpnPymntId + ", cmpnId=" + cmpnId + ", pymntCd=" + pymntCd
				+ ", amnt=" + amnt + ", pgPymntCd=" + pgPymntCd + ", toString()=" + super.toString() + "]";
	}
    
   
}