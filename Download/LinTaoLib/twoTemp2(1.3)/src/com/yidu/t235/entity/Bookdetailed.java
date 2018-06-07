package com.yidu.t235.entity;

public class Bookdetailed {
	   //图书自增长
	   private int bookno; 
	   //销售订单号
       private String ordernumber;
       //客户名称
       private String bookname;
       //销售日期
       private String bookprice;
       //销售员
       private String salesman;
       
       /**
        * 默认构造方法
        */
       public Bookdetailed(){
    	   
       }
       
       /**
        * 
        * @param bookno 自增长
        * @param ordernumber 销售订单号
        * @param bookname 客户名称
        * @param bookprice 销售日期
        * @param salesman 销售员
        */
       
	    public Bookdetailed(int bookno, String ordernumber, String bookname, String bookprice, String salesman) {
		this.bookno = bookno;
		this.ordernumber = ordernumber;
		this.bookname = bookname;
		this.bookprice = bookprice;
		this.salesman = salesman;
	}
	    public Bookdetailed(String ordernumber, String bookname, String bookprice, String salesman) {
			this.ordernumber = ordernumber;
			this.bookname = bookname;
			this.bookprice = bookprice;
			this.salesman = salesman;
		}
       //自动生成setter和getter方法

		public int getBookno() {
			return bookno;
		}

		public void setBookno(int bookno) {
			this.bookno = bookno;
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

		public String getBookprice() {
			return bookprice;
		}

		public void setBookprice(String bookprice) {
			this.bookprice = bookprice;
		}

		public String getSalesman() {
			return salesman;
		}

		public void setSalesman(String salesman) {
			this.salesman = salesman;
		}
}