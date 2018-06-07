package com.yidu.t235.xm.entity;

public class ReturnInquiry {
	
	private int tempId;//编号
	private String orderNumber;//orderNumberid字段：主键，非空 退货单号
	private String bookName ;//--图书名称
	private double bookPrice ;//--图书价格
	private int salesVolume ;//--销售数量
	private String salesPeriods ;//--退货时间
	private String consoleOperator ;//--操作员
	private double total;//--总价

	public ReturnInquiry() {
	}
	/**
	 * 
	/**
	 * 
	 *部分带参构造函数
	 * @param orderNumber	退货单号
	 * @param bookName		图书名称
	 * @param bookPrice		图书价格
	 * @param salesVolume	销售数量
	 * @param salesPeriods	退货时间
	 * @param consoleOperator 操作员
 
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
	 * @param tempId 编号
	 * @param orderNumber	退货单号
	 * @param bookName		图书名称
	 * @param bookPrice		图书价格
	 * @param salesVolume	销售数量
	 * @param salesPeriods	退货时间
	 * @param consoleOperator 操作员
	 
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
	
	//自动生成setter和getter方法
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
