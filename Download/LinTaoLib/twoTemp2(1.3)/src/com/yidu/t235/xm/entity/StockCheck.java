package com.yidu.t235.xm.entity;
/**
 * StockCheckʵ����
 * @author �����
 * ���ڣ�2018��3��8�� ����4:33:42
 */
public class StockCheck {
	//ͼ����
	private int bookNo;
	//ͼ������
	private String bookName;
	//�������
	private int stockNamber;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public StockCheck(){
		
	}

	/**
	 * �������췽��
	 * @param bookName ͼ������
	 * @param stockNamber �������
	 */
	public StockCheck(String bookName, int stockNamber) {
		this.bookName = bookName;
		this.stockNamber = stockNamber;
	}

	/**
	 * �������췽��
	 * @param bookNo ͼ����
	 * @param bookName ͼ������
	 * @param stockNamber �������
	 */
	public StockCheck(int bookNo, String bookName, int stockNamber) {
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.stockNamber = stockNamber;
	}

	//�Զ�����
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

	public int getStockNamber() {
		return stockNamber;
	}

	public void setStockNamber(int stockNamber) {
		this.stockNamber = stockNamber;
	}
}
