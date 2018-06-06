package com.yidu.t235.xm.entity;

public class ProcurementStatement {
	private int tempId;//���
	private String procurementNo	;//	--�ɹ�����
	private String bookName	;//	--	ͼ������
	private String bookType;//	--	 ͼ������
	private double bookBid	;//	--	ͼ�����
	private String supplier	;//	--	��Ӧ��
	private String libraryTime	;//	--	�ɹ�ʱ��
	private int purchaseQuantity;//	--	�ɹ�����
	private String playEmp	;//	--	����Ա
	private double total;//�ܼ�
	
	public ProcurementStatement() {
	}
	/**
	 *���� ���ι��캯��
	 * @param procurementNo �ɹ�����
	 * @param bookName ͼ������
	 * @param bookType ͼ������
	 * @param bookBid ͼ�����
	 * @param supplier ��Ӧ��
	 * @param libraryTime �ɹ�ʱ��
	 * @param purchaseQuantity �ɹ�����
	 * @param playEmp ����Ա
	 * @param total �ܼ�
	 */
	public ProcurementStatement(String procurementNo, String bookName, String bookType, double bookBid, String supplier,
			String libraryTime, int purchaseQuantity, String playEmp ) {
		this.procurementNo = procurementNo;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookBid = bookBid;
		this.supplier = supplier;
		this.libraryTime = libraryTime;
		this.purchaseQuantity = purchaseQuantity;
		this.playEmp = playEmp;
	}
	/**
	 * ���ι��캯����������
	 * @param tempId
	 * @param procurementNo �ɹ�����
	 * @param bookName ͼ������
	 * @param bookType ͼ������
	 * @param bookBid ͼ�����
	 * @param supplier ��Ӧ��
	 * @param libraryTime �ɹ�ʱ��
	 * @param purchaseQuantity �ɹ�����
	 * @param playEmp ����Ա
	 * @param total �ܼ�
	 */
	public ProcurementStatement(int tempId, String procurementNo, String bookName, String bookType, double bookBid,
			String supplier, String libraryTime, int purchaseQuantity, String playEmp ) {
	 
		this.tempId = tempId;
		this.procurementNo = procurementNo;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookBid = bookBid;
		this.supplier = supplier;
		this.libraryTime = libraryTime;
		this.purchaseQuantity = purchaseQuantity;
		this.playEmp = playEmp;
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
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public double getBookBid() {
		return bookBid;
	}
	public void setBookBid(double bookBid) {
		this.bookBid = bookBid;
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
	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public String getPlayEmp() {
		return playEmp;
	}
	public void setPlayEmp(String playEmp) {
		this.playEmp = playEmp;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = bookBid*purchaseQuantity;
	}
	 
}
