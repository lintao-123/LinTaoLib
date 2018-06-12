package com.yidu.t235.xm.entity;

public class Operators {
	private int userId 	;//--	�û����
	private String name; //--	����Ա����
	private String address;//--	��ַ
	private String telephoneNumber	;//--	�绰����
	private String empType ;//  Ա������
	private double  empMoney ;//--Ա������
	
	public Operators() { 
	}
	/**
	 * 
	 * @param name ����Ա����
	 * @param address ��ַ
	 * @param telephoneNumber �绰����
	 * @param empType  Ա������
	 * @param empMoney Ա������
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
	 * @param userId �û����
	 * @param name ����Ա����
	 * @param address ��ַ
	 * @param telephoneNumber �绰����
	 * @param empType  Ա������
	 * @param empMoney Ա������
	 */
	public Operators(int userId, String name, String address, String telephoneNumber, String empType, double empMoney) {
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.empType = empType;
		this.empMoney = empMoney;
	}
	
	//�Զ�����setter��getter
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
