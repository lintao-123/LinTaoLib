package com.yidu.t235.entity;

public class Bookdetailed {
	   //ͼ��������
	   private int bookno; 
	   //���۶�����
       private String ordernumber;
       //�ͻ�����
       private String bookname;
       //��������
       private String bookprice;
       //����Ա
       private String salesman;
       
       /**
        * Ĭ�Ϲ��췽��
        */
       public Bookdetailed(){
    	   
       }
       
       /**
        * 
        * @param bookno ������
        * @param ordernumber ���۶�����
        * @param bookname �ͻ�����
        * @param bookprice ��������
        * @param salesman ����Ա
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
       //�Զ�����setter��getter����

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