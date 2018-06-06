package com.yidu.t235.entity;

public class Bookoperation {
	 //图书编号
	 private int bookno;
	 //图书名称
     private String bookname;
     //图书种类
     private String booktype;
     //图书作者
     private String bookauthor;
     //供应商
     private String supplier;
     //图书价格
     private String bookprice;
     //图书单位
     private String bookunit ;
     
     /**
      * 默认构造方法
      */
     public Bookoperation(){
    	 
     }
      /**
       * 带参数构造方法
       * @param bookno 图书编号
       * @param bookname 图书名称
       * @param booktype 图书种类
       * @param bookauthor 图书作者
       * @param supplier 供应商
       * @param bookprice 图书价格
       * @param bookunit 图书单位
       */
	    public Bookoperation(int bookno, String bookname, String booktype, String bookauthor, String supplier,
			String bookprice, String bookunit) {
		this.bookno = bookno;
		this.bookname = bookname;
		this.booktype = booktype;
		this.bookauthor = bookauthor;
		this.supplier = supplier;
		this.bookprice = bookprice;
		this.bookunit = bookunit;
	}
	    
	    public Bookoperation(String bookname, String booktype, String bookauthor, String supplier,
				String bookprice, String bookunit) {
			this.bookname = bookname;
			this.booktype = booktype;
			this.bookauthor = bookauthor;
			this.supplier = supplier;
			this.bookprice = bookprice;
			this.bookunit = bookunit;
		}
    //自动生成setter/getter方法
	public int getBookno() {
		return bookno;
	}
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBooktype() {
		return booktype;
	}
	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}
	public String getBookauthor() {
		return bookauthor;
	}
	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getBookprice() {
		return bookprice;
	}
	public void setBookprice(String bookprice) {
		this.bookprice = bookprice;
	}
	public String getBookunit() {
		return bookunit;
	}
	public void setBookunit(String bookunit) {
		this.bookunit = bookunit;
	}
	    
}
