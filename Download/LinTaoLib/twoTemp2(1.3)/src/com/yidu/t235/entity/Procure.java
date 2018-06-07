package com.yidu.t235.entity;

public class Procure {
	  //编号
	  private int bookid;
      //采购订单号
	  private String ordernumber;
	  //图书名称
      private String bookname;
      //图书数量
      private int salesvolume;
      //图书进价
      private double bid;
      //图书总价
      private double total;
      //报损日期
      private String salesperiods;
      //操作员
      private String operator;
      
      /*
       * 默认构造方法
       */
      public Procure(){
    	  
      }
      /**
       *  
       * @param bookid 编号
       * @param ordernumber 采购订单号
       * @param bookname 图书名称
       * @param salesvolume 图书数量
       * @param bid 图书进价
 
       * @param salesperiods 报损日期
       * @param operator 操作员
       */
	public Procure(int bookid, String ordernumber, String bookname, int salesvolume, double bid,  
			 String salesperiods, String operator) {
		this.bookid = bookid;
		this.ordernumber = ordernumber;
		this.bookname = bookname;
		this.salesvolume = salesvolume;
		this.bid = bid;
		 
		this.salesperiods = salesperiods;
		this.operator = operator;
	}
	public Procure(String ordernumber, String bookname, int salesvolume , double bid,
			 String salesperiods, String operator) {
		this.ordernumber = ordernumber;
		this.bookname = bookname;
		this.salesvolume = salesvolume;
		this.bid = bid;
		 
		this.salesperiods = salesperiods;
		this.operator = operator;
	}
	//自动生成setter和getter方法
	
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public int getSalesvolume() {
		return salesvolume;
	}
	public void setSalesvolume(int salesvolume) {
		this.salesvolume = salesvolume;
	}
	public double getBid() {
		return bid;
	}
	public void setBid(double bid) {
		this.bid = bid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double booktotal) {
		this.total = bid*salesvolume;
	}
	public String getSalesperiods() {
		return salesperiods;
	}
	public void setSalesperiods(String salesperiods) {
		this.salesperiods = salesperiods;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
      
}