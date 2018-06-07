package com.yidu.t235.entity;

public class Salesinquiry {
	 //编号
	 private int ordernumber;
	 //销售订单号
     private String ordernumberid;
     //图书名称
     private String bookname;
     //图书类型
     private String booktype;
     //图书价格
     private Double bookprice;
     //销售数量
     private int salesvolume;
     //图书总额
     private double total;
      
     /**
      * 默认构造方法
      */
     public Salesinquiry(){
    	 
     }
     /**
      * 
      * @param ordernumber 编号
      * @param ordernumberid 销售订单号
      * @param bookname 图书名称
      * @param booktype 图书类型
      * @param bookprice 图书价格
      * @param salesvolume 销售数量
      */
	 public Salesinquiry(int ordernumber, String ordernumberid, String bookname, String booktype, Double bookprice,
			int salesvolume ) {
		this.ordernumber = ordernumber;
		this.ordernumberid = ordernumberid;
		this.bookname = bookname;
		this.booktype = booktype;
		this.bookprice = bookprice;
		this.salesvolume = salesvolume;
	}
    
	public Salesinquiry(String ordernumberid, String bookname, String booktype, Double bookprice,
			int salesvolume ) {
		this.ordernumberid = ordernumberid;
		this.bookname = bookname;
		this.booktype = booktype;
		this.bookprice = bookprice;
		this.salesvolume = salesvolume;
	}

	//自动生成setter/getter方法
	
	public int getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getOrdernumberid() {
		return ordernumberid;
	}

	public void setOrdernumberid(String ordernumberid) {
		this.ordernumberid = ordernumberid;
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

	public Double getBookprice() {
		return bookprice;
	}

	public void setBookprice(Double bookprice) {
		this.bookprice = bookprice;
	}

	public int getSalesvolume() {
		return salesvolume;
	}

	public void setSalesvolume(int salesvolume) {
		this.salesvolume = salesvolume;
	}

	public Double getTotal() {
		return  total;
	}

	public void setTotal(double Total) {
		this.total =salesvolume*bookprice;
	}
	
}