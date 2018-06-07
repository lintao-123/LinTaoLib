package com.yidu.t235.xm.entity;
/**
 * SupplierInformationʵ����
 * @author �����
 * ���ڣ�2018��3��8�� ����4:33:42
 */
public class SupplierInformation {
	//��Ӧ�̱��
	private int supplierNumber;
	//��Ӧ������
	private String supplier;
	//��ϵ�绰
	private String telephoneNumbers;
	//��ϵ��ַ
	private String address;
	//��ϵ��
	private String contacts;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public SupplierInformation(){
		
	}
	
	/**
	 * �������췽��
	 * @param supplier ��Ӧ��
	 * @param telephoneNumbers ��ϵ�绰
	 * @param address ��ϵ��ַ
	 * @param contacts ��ϵ��
	 */
	public SupplierInformation(String supplier, String telephoneNumbers, String address, String contacts) {
		this.supplier = supplier;
		this.telephoneNumbers = telephoneNumbers;
		this.address = address;
		this.contacts = contacts;
	}

	/**
	 * �������췽��
	 * @param supplierNumber ��Ӧ�̱��
	 * @param supplier ��Ӧ��
	 * @param telephoneNumbers ��ϵ�绰
	 * @param address ��ϵ��ַ
	 * @param contacts ��ϵ��
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

	//�Զ�����
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
