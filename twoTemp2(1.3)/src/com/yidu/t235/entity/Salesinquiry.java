package com.yidu.t235.entity;

public class Salesinquiry {
	 //���
	 private int ordernumber;
	 //���۶�����
     private String ordernumberid;
     //ͼ������
     private String bookname;
     //ͼ������
     private String booktype;
     //ͼ��۸�
     private Double bookprice;
     //��������
     private int salesvolume;
     //ͼ���ܶ�
     private double total;
      
     /**
      * Ĭ�Ϲ��췽��
      */
     public Salesinquiry(){
    	 
     }
     /**
      * 
      * @param ordernumber ���
      * @param ordernumberid ���۶�����
      * @param bookname ͼ������
      * @param booktype ͼ������
      * @param bookprice ͼ��۸�
      * @param salesvolume ��������
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

	//�Զ�����setter/getter����
	
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