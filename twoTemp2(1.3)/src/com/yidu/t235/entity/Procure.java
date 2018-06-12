package com.yidu.t235.entity;

public class Procure {
	  //���
	  private int bookid;
      //�ɹ�������
	  private String ordernumber;
	  //ͼ������
      private String bookname;
      //ͼ������
      private int salesvolume;
      //ͼ�����
      private double bid;
      //ͼ���ܼ�
      private double total;
      //��������
      private String salesperiods;
      //����Ա
      private String operator;
      
      /*
       * Ĭ�Ϲ��췽��
       */
      public Procure(){
    	  
      }
      /**
       *  
       * @param bookid ���
       * @param ordernumber �ɹ�������
       * @param bookname ͼ������
       * @param salesvolume ͼ������
       * @param bid ͼ�����
 
       * @param salesperiods ��������
       * @param operator ����Ա
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
	//�Զ�����setter��getter����
	
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