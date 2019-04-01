package com.mutong.mhscowboy.entity;


public class Project extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4678183792105602913L;
	private Integer pid;
	private String pronum;
	private String applicant;
	private String name;
	private String time;
	private String person;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPronum() {
		return pronum;
	}
	public void setPronum(String pronum) {
		this.pronum = pronum;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Project [pid=" + pid + ", pronum=" + pronum + ", applicant=" + applicant + ", name=" + name + ", time="
				+ time + ", person=" + person + "]";
	}
	
}
