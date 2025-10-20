package com.ktdsuniversity.edu.domain.file.vo;

import com.ktdsuniversity.edu.global.common.BaseVO;

/**
 * @TableName FL_GRP
 * @TableComment null
 */
public class FileGroupVO extends BaseVO{

    /**
     * @ColumnName FL_GRP_ID
     * @ColumnType VARCHAR2(50)
     * @ColumnComment 파일 그룹 구분을 위한 고유 아이디
     */
    private String flGrpId;

    /**
     * @ColumnName FL_CNT
     * @ColumnType NUMBER(3, 0)
     * @ColumnComment 첨부 파일 그룹에 포함된 파일의 개수
     */
    private int flCnt;


    public String getFlGrpId() {
        return this.flGrpId;
    }
    
    public void setFlGrpId(String flGrpId) {
        this.flGrpId = flGrpId;
    }
    
    public int getFlCnt() {
        return this.flCnt;
    }
    
    public void setFlCnt(int flCnt) {
        this.flCnt = flCnt;
    }

	@Override
	public String toString() {
		return "FileGroupVO [flGrpId=" + flGrpId + ", flCnt=" + flCnt + ", toString()=" + super.toString() + "]";
	}
    
    
}