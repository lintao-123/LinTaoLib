package com.yidu.t235.xm.entity;

public class Operators {
	private int userId 	;//--	用户编号
	private String name; //--	操作员姓名
	private String address;//--	地址
	private String telephoneNumber	;//--	电话号码
	private String empType ;//  员工类型
	private double  empMoney ;//--员工工资
	
	public Operators() { 
	}
	/**
	 * 
	 * @param name 操作员姓名
	 * @param address 地址
	 * @param telephoneNumber 电话号码
	 * @param empType  员工类型
	 * @param empMoney 员工工资
	 */
	public Operators(String name, String address, String telephoneNumber, String empType, double empMoney) {
		this.name = name;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.empType = empType;
		this.empMoney = empMoney;
	}
	
	/**
	 * 
	 * @param userId 用户编号
	 * @param name 操作员姓名
	 * @param address 地址
	 * @param telephoneNumber 电话号码
	 * @param empType  员工类型
	 * @param empMoney 员工工资
	 */
	public Operators(int userId, String name, String address, String telephoneNumber, String empType, double empMoney) {
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.empType = empType;
		this.empMoney = empMoney;
	}
	
	//自动生成setter和getter
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public double getEmpMoney() {
		return empMoney;
	}
	public void setEmpMoney(double empMoney) {
		this.empMoney = empMoney;
	}
	 
	
	
	

}
