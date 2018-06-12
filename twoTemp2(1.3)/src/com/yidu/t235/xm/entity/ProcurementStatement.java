package com.yidu.t235.xm.entity;

public class ProcurementStatement {
	private int tempId;//编号
	private String procurementNo	;//	--采购单号
	private String bookName	;//	--	图书名称
	private String bookType;//	--	 图书类型
	private double bookBid	;//	--	图书进价
	private String supplier	;//	--	供应商
	private String libraryTime	;//	--	采购时间
	private int purchaseQuantity;//	--	采购数量
	private String playEmp	;//	--	操作员
	private double total;//总价
	
	public ProcurementStatement() {
	}
	/**
	 *部分 带参构造函数
	 * @param procurementNo 采购单号
	 * @param bookName 图书名称
	 * @param bookType 图书类型
	 * @param bookBid 图书进价
	 * @param supplier 供应商
	 * @param libraryTime 采购时间
	 * @param purchaseQuantity 采购数量
	 * @param playEmp 操作员
	 * @param total 总价
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
	 * 带参构造函数（完整）
	 * @param tempId
	 * @param procurementNo 采购单号
	 * @param bookName 图书名称
	 * @param bookType 图书类型
	 * @param bookBid 图书进价
	 * @param supplier 供应商
	 * @param libraryTime 采购时间
	 * @param purchaseQuantity 采购数量
	 * @param playEmp 操作员
	 * @param total 总价
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
	
	//自动生成setter和getter
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
