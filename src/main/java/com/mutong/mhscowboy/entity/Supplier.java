package com.mutong.mhscowboy.entity;

public class Supplier extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8862587621732729590L;
	private Integer sid;
	private String supnum;
	private String name;
	private String contacts;
	private String address;
	private String phone;
	private String fax;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSupnum() {
		return supnum;
	}
	public void setSupnum(String supnum) {
		this.supnum = supnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Supplier [sid=" + sid + ", supnum=" + supnum + ", name=" + name + ", contacts=" + contacts
				+ ", address=" + address + ", phone=" + phone + ", fax=" + fax + "]";
	}
	
}
