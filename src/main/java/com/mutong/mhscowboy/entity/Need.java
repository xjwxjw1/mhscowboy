package com.mutong.mhscowboy.entity;

public class Need extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8431688364450179540L;
	private Integer nid;
	private String matnum;
	private String name;
	private String person;
	private String time;
	private Integer demand;
	private String auditing;
	private Integer proId;
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getMatnum() {
		return matnum;
	}
	public void setMatnum(String matnum) {
		this.matnum = matnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getDemand() {
		return demand;
	}
	public void setDemand(Integer demand) {
		this.demand = demand;
	}
	public String getAuditing() {
		return auditing;
	}
	public void setAuditing(String auditing) {
		this.auditing = auditing;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Need [nid=" + nid + ", matnum=" + matnum + ", name=" + name + ", person=" + person + ", time=" + time
				+ ", demand=" + demand + ", auditing=" + auditing + ", proId=" + proId + "]";
	}
	
}
