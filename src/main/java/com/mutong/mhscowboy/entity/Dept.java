package com.mutong.mhscowboy.entity;

public class Dept extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8212794733253237105L;
	private Integer did;
	private String deptname;
	private String mgr;
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getMgr() {
		return mgr;
	}
	public void setMgr(String mgr) {
		this.mgr = mgr;
	}
	@Override
	public String toString() {
		return "Dept [did=" + did + ", deptname=" + deptname + ", mgr=" + mgr + "]";
	}
	
}
