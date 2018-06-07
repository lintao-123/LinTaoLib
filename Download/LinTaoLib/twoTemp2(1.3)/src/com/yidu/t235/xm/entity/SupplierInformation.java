package com.yidu.t235.xm.entity;
/**
 * SupplierInformation实体类
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:33:42
 */
public class SupplierInformation {
	//供应商编号
	private int supplierNumber;
	//供应商名称
	private String supplier;
	//联系电话
	private String telephoneNumbers;
	//联系地址
	private String address;
	//联系人
	private String contacts;
	
	/**
	 * 默认构造方法
	 */
	public SupplierInformation(){
		
	}
	
	/**
	 * 带叁构造方法
	 * @param supplier 供应商
	 * @param telephoneNumbers 联系电话
	 * @param address 联系地址
	 * @param contacts 联系人
	 */
	public SupplierInformation(String supplier, String telephoneNumbers, String address, String contacts) {
		this.supplier = supplier;
		this.telephoneNumbers = telephoneNumbers;
		this.address = address;
		this.contacts = contacts;
	}

	/**
	 * 带叁构造方法
	 * @param supplierNumber 供应商编号
	 * @param supplier 供应商
	 * @param telephoneNumbers 联系电话
	 * @param address 联系地址
	 * @param contacts 联系人
	 */
	public SupplierInformation(int supplierNumber, String supplier, String telephoneNumbers, String address,
			String contacts) {
		this.supplierNumber = supplierNumber;
		this.supplier = supplier;
		this.telephoneNumbers = telephoneNumbers;
		this.address = address;
		this.contacts = contacts;
	}

	public int getSupplierNumber() {
		return supplierNumber;
	}

	//自动生成
	public void setSupplierNumber(int supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getTelephoneNumbers() {
		return telephoneNumbers;
	}

	public void setTelephoneNumbers(String telephoneNumbers) {
		this.telephoneNumbers = telephoneNumbers;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
}
