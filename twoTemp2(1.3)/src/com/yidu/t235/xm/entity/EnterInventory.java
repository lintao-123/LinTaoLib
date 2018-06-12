package com.yidu.t235.xm.entity;

/**
 * EnterInventory实体类
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:26:30
 */
public class EnterInventory {
	//图书编号
	private int bookNo;
	//图书名称
	private String bookName;
	//采购数量
	private int purchaseQuantity;
	//采购进价
	private double bookBid;
	//入库时间
	private String inventoryTime;
	//操作员
	private String consoleOperator;
	
	/**
	 * 默认构造方法
	 */
	public EnterInventory(){
		
	}

	/**
	 * 带叁构造方法
	 * @param bookName 图书名称
	 * @param purchaseQuantity 采购数量
	 * @param bookBid 图书进价
	 * @param inventoryTime 入库时间
	 * @param consoleOperator 操作员
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
	 * 带叁构造方法
	 * @param bookNo 图书编号
	 * @param bookName 图书名称
	 * @param purchaseQuantity 采购数量
	 * @param bookBid 图书进价
	 * @param inventoryTime 入库时间
	 * @param consoleOperator 操作员
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
	//自动生成setter和getter方法
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
