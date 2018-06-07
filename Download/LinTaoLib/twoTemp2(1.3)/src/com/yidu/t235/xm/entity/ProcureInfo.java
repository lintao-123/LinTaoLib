package com.yidu.t235.xm.entity;

public class ProcureInfo {
	private int tempId ;//--���
 
	private String procurementNo ;//--�ɹ�������
 
	private String supplier ; 	//��Ӧ��
	private String libraryTime	;//�ɹ�����

	 
	
	public ProcureInfo() {
	}


	/**
	 * ���ִ��κ���
	 * @param procurementNo --�ɹ�������
	 * @param supplier ��Ӧ��
	 * @param libraryTime �ɹ�����
	 */
	public ProcureInfo(String procurementNo, String supplier, String libraryTime) {
		this.procurementNo = procurementNo;
		this.supplier = supplier;
		this.libraryTime = libraryTime;
	}

	/**
	 * ���κ���
	 * @param tempId--���
	 * @param procurementNo --�ɹ�������
	 * @param supplier ��Ӧ��
	 * @param libraryTime �ɹ�����
	 */
	
	public ProcureInfo(int tempId, String procurementNo, String supplier, String libraryTime) {
		this.tempId = tempId;
		this.procurementNo = procurementNo;
		this.supplier = supplier;
		this.libraryTime = libraryTime;
	}

	//�Զ�����setter��getter
	public int getTempId() {
		return tempId;
	}


	public void setTempId(int tempId) {
		this.tempId = tempId;
	}


	public String getProcurementNo() {
		return procurementNo;
	}


	public void setProcurementNo(String procurementNo) {
		this.procurementNo = procurementNo;
	}


	public String getSupplier() {
		return supplier;
	}


	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}


	public String getLibraryTime() {
		return libraryTime;
	}


	public void setLibraryTime(String libraryTime) {
		this.libraryTime = libraryTime;
	}
		 	
}
