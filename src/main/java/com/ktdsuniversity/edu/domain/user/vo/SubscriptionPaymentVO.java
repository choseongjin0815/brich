package com.ktdsuniversity.edu.domain.user.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName SBSCRPTN_PYMNT
 * @TableComment null
 */
public class SubscriptionPaymentVO extends BaseVO{

    /**
     * @ColumnName SBSCRPTN_PYMNT_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 구독 결제를  구분지을 고유한 아이디
     */
    private String sbscrptnPymntId;

    /**
     * @ColumnName USR_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 구독 상품을 결제할 블로거의 아이디
     */
    private String usrId;

    /**
     * @ColumnName PYMNT_CD
     * @ColumnType CHAR(4)
     * @ColumnComment 공통 테이블의 결제 상태코드
     */
    private String pymntCd;

    /**
     * @ColumnName SBSCRPTN_CD
     * @ColumnType CHAR(4)
     * @ColumnComment 구독 상태 코드
     */
    private String sbscrptnCd;

    /**
     * @ColumnName PG_PYMNT_CD
     * @ColumnType VARCHAR2(100)
     * @ColumnComment 결제 대행사에서 주는 코드
     */
    private String pgPymntCd;

    /**
     * @ColumnName AMNT
     * @ColumnType NUMBER(7, 0)
     * @ColumnComment 구독 결제 금액
     */
    private int amnt;


    public String getSbscrptnPymntId() {
        return this.sbscrptnPymntId;
    }
    
    public void setSbscrptnPymntId(String sbscrptnPymntId) {
        this.sbscrptnPymntId = sbscrptnPymntId;
    }
    
    public String getUsrId() {
        return this.usrId;
    }
    
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    
    public String getPymntCd() {
        return this.pymntCd;
    }
    
    public void setPymntCd(String pymntCd) {
        this.pymntCd = pymntCd;
    }
    
    public String getSbscrptnCd() {
        return this.sbscrptnCd;
    }
    
    public void setSbscrptnCd(String sbscrptnCd) {
        this.sbscrptnCd = sbscrptnCd;
    }
    
    public String getPgPymntCd() {
        return this.pgPymntCd;
    }
    
    public void setPgPymntCd(String pgPymntCd) {
        this.pgPymntCd = pgPymntCd;
    }
    
    public int getAmnt() {
        return this.amnt;
    }
    
    public void setAmnt(int amnt) {
        this.amnt = amnt;
    }

	@Override
	public String toString() {
		return "SubscriptionPaymentVO [sbscrptnPymntId=" + sbscrptnPymntId + ", usrId=" + usrId + ", pymntCd=" + pymntCd
				+ ", sbscrptnCd=" + sbscrptnCd + ", pgPymntCd=" + pgPymntCd + ", amnt=" + amnt + ", toString()="
				+ super.toString() + "]";
	}
    
   
}