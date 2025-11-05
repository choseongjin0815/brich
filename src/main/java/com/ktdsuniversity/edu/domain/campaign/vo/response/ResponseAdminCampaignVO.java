package com.ktdsuniversity.edu.domain.campaign.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.domain.campaign.vo.CampaignVO;
import com.ktdsuniversity.edu.domain.file.vo.FileVO;

public class ResponseAdminCampaignVO extends CampaignVO {

	private String flGrpId;
	private String flCnt;
	private List<FileVO> fileVoList;
	private int adptCnt;
	private List<String> area;
	private String parentArea;
	private String sttsCdNm;
	
	public String getFlGrpId() {
		return this.flGrpId;
	}
	public void setFlGrpId(String flGrpId) {
		this.flGrpId = flGrpId;
	}
	public String getFlCnt() {
		return this.flCnt;
	}
	public void setFlCnt(String flCnt) {
		this.flCnt = flCnt;
	}
	public List<FileVO> getFileVoList() {
		return this.fileVoList;
	}
	public void setFileVoList(List<FileVO> fileVoList) {
		this.fileVoList = fileVoList;
	}
	public int getAdptCnt() {
		return this.adptCnt;
	}
	public void setAdptCnt(int adptCnt) {
		this.adptCnt = adptCnt;
	}
	public List<String> getArea() {
		return this.area;
	}
	public void setArea(List<String> area) {
		this.area = area;
	}
	public String getParentArea() {
		return this.parentArea;
	}
	public void setParentArea(String parentArea) {
		this.parentArea = parentArea;
	}
	public String getSttsCdNm() {
		return this.sttsCdNm;
	}
	public void setSttsCdNm(String sttsCdNm) {
		this.sttsCdNm = sttsCdNm;
	}
	
	@Override
	public String toString() {
		return "ResponseAdminCampaignVO [flGrpId=" + flGrpId + ", flCnt=" + flCnt + ", fileVoList=" + fileVoList
				+ ", adptCnt=" + adptCnt + ", area=" + area + ", parentArea=" + parentArea + ", sttsCdNm=" + sttsCdNm
				+ "]";
	}

}
