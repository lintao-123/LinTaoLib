package com.yidu.t235.xm.entity;

public class ReturnInquiry {
	
	private int tempId;//���
	private String orderNumber;//orderNumberid�ֶΣ��������ǿ� �˻�����
	private String bookName ;//--ͼ������
	private double bookPrice ;//--ͼ��۸�
	private int salesVolume ;//--��������
	private String salesPeriods ;//--�˻�ʱ��
	private String consoleOperator ;//--����Ա
	private double total;//--�ܼ�

	public ReturnInquiry() {
	}
	/**
	 * 
	/**
	 * 
	 *���ִ��ι��캯��
	 * @param orderNumber	�˻�����
	 * @param bookName		ͼ������
	 * @param bookPrice		ͼ��۸�
	 * @param salesVolume	��������
	 * @param salesPeriods	�˻�ʱ��
	 * @param consoleOperator ����Ա
 
	 */
	public ReturnInquiry(String orderNumber, String bookName, double bookPrice, int salesVolume, String salesPeriods,
			String consoleOperator ) {
		this.orderNumber = orderNumber;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.salesVolume = salesVolume;
		this.salesPeriods = salesPeriods;
		this.consoleOperator = consoleOperator;
		 
	}
	/**
	 * 
	 * @param tempId ���
	 * @param orderNumber	�˻�����
	 * @param bookName		ͼ������
	 * @param bookPrice		ͼ��۸�
	 * @param salesVolume	��������
	 * @param salesPeriods	�˻�ʱ��
	 * @param consoleOperator ����Ա
	 
	 */
	public ReturnInquiry(int tempId, String orderNumber, String bookName, double bookPrice, int salesVolume,
			String salesPeriods, String consoleOperator ) {
		this.tempId = tempId;
		this.orderNumber = orderNumber;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.salesVolume = salesVolume;
		this.salesPeriods = salesPeriods;
		this.consoleOperator = consoleOperator;
	 
	}
	
	//�Զ�����setter��getter����
	public int getTempId() {
		return tempId;
	}
	public void setTempId(int tempId) {
		this.tempId = tempId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public int getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}
	public String getSalesPeriods() {
		return salesPeriods;
	}
	public void setSalesPeriods(String salesPeriods) {
		this.salesPeriods = salesPeriods;
	}
	public String getConsoleOperator() {
		return consoleOperator;
	}
	public void setConsoleOperator(String consoleOperator) {
		this.consoleOperator = consoleOperator;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total =salesVolume*bookPrice;
	}
	
}
