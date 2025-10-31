package com.ktdsuniversity.edu.domain.blog.vo;

public class BlogIndexVO {

	private String usrId;
	private String statDt;
	private double indxVal;
	private double indxValAvg5d;
	
	
	public String getUsrId() {
		return this.usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getStatDt() {
		return this.statDt;
	}
	public void setStatDt(String statDt) {
		this.statDt = statDt;
	}
	public double getIndxVal() {
		return this.indxVal;
	}
	public void setIndxVal(double indxVal) {
		this.indxVal = indxVal;
	}
	public double getIndxValAvg5d() {
		return this.indxValAvg5d;
	}
	public void setIndxValAvg5d(double indxValAvg5d) {
		this.indxValAvg5d = indxValAvg5d;
	}
	
	@Override
	public String toString() {
		return "BlogIndexVO [usrId=" + this.usrId + ", statDt=" + this.statDt + ", indxVal=" + this.indxVal + ", indxValAvg5d="
				+ this.indxValAvg5d + "]";
	}
}
