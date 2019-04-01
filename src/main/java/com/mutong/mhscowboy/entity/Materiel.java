package com.mutong.mhscowboy.entity;

public class Materiel extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3484776845093289301L;
	private Integer mid;
	private String matnum;
	private String name;
	private String unit;
	private Integer num;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Materiel [mid=" + mid + ", matnum=" + matnum + ", name=" + name + ", unit=" + unit + ", num=" + num
				+ "]";
	}
	
}
