package com.yidu.t235.xm.entity;
/**
 * StockCheck实体类
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:33:42
 */
public class StockCheck {
	//图书编号
	private int bookNo;
	//图书名称
	private String bookName;
	//库存数量
	private int stockNamber;
	
	/**
	 * 默认构造方法
	 */
	public StockCheck(){
		
	}

	/**
	 * 带叁构造方法
	 * @param bookName 图书名称
	 * @param stockNamber 库存数量
	 */
	public StockCheck(String bookName, int stockNamber) {
		this.bookName = bookName;
		this.stockNamber = stockNamber;
	}

	/**
	 * 带叁构造方法
	 * @param bookNo 图书编号
	 * @param bookName 图书名称
	 * @param stockNamber 库存数量
	 */
	public StockCheck(int bookNo, String bookName, int stockNamber) {
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.stockNamber = stockNamber;
	}

	//自动生成
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
