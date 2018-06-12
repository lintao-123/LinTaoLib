package com.yidu.t235.xm.entity;

/**
 * EnterInventoryʵ����
 * @author �����
 * ���ڣ�2018��3��8�� ����4:26:30
 */
public class EnterInventory {
	//ͼ����
	private int bookNo;
	//ͼ������
	private String bookName;
	//�ɹ�����
	private int purchaseQuantity;
	//�ɹ�����
	private double bookBid;
	//���ʱ��
	private String inventoryTime;
	//����Ա
	private String consoleOperator;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public EnterInventory(){
		
	}

	/**
	 * �������췽��
	 * @param bookName ͼ������
	 * @param purchaseQuantity �ɹ�����
	 * @param bookBid ͼ�����
	 * @param inventoryTime ���ʱ��
	 * @param consoleOperator ����Ա
	 */
	public EnterInventory(String bookName, int purchaseQuantity, double bookBid, String inventoryTime,
			String consoleOperator) {
		this.bookName = bookName;
		this.purchaseQuantity = purchaseQuantity;
		this.bookBid = bookBid;
		this.inventoryTime = inventoryTime;
		this.consoleOperator = consoleOperator;
	}
	
	/**
	 * �������췽��
	 * @param bookNo ͼ����
	 * @param bookName ͼ������
	 * @param purchaseQuantity �ɹ�����
	 * @param bookBid ͼ�����
	 * @param inventoryTime ���ʱ��
	 * @param consoleOperator ����Ա
	 */
	public EnterInventory(int bookNo, String bookName, int purchaseQuantity, double bookBid, String inventoryTime,
			String consoleOperator) {
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.purchaseQuantity = purchaseQuantity;
		this.bookBid = bookBid;
		this.inventoryTime = inventoryTime;
		this.consoleOperator = consoleOperator;
	}
	//�Զ�����setter��getter����
	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public double getBookBid() {
		return bookBid;
	}

	public void setBookBid(double bookBid) {
		this.bookBid = bookBid;
	}

	public String getInventoryTime() {
		return inventoryTime;
	}

	public void setInventoryTime(String inventoryTime) {
		this.inventoryTime = inventoryTime;
	}

	public String getConsoleOperator() {
		return consoleOperator;
	}

	public void setConsoleOperator(String consoleOperator) {
		this.consoleOperator = consoleOperator;
	}
}
