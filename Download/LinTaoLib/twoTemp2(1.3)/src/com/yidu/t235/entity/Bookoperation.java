package com.yidu.t235.entity;

public class Bookoperation {
	 //ͼ����
	 private int bookno;
	 //ͼ������
     private String bookname;
     //ͼ������
     private String booktype;
     //ͼ������
     private String bookauthor;
     //��Ӧ��
     private String supplier;
     //ͼ��۸�
     private String bookprice;
     //ͼ�鵥λ
     private String bookunit ;
     
     /**
      * Ĭ�Ϲ��췽��
      */
     public Bookoperation(){
    	 
     }
      /**
       * ���������췽��
       * @param bookno ͼ����
       * @param bookname ͼ������
       * @param booktype ͼ������
       * @param bookauthor ͼ������
       * @param supplier ��Ӧ��
       * @param bookprice ͼ��۸�
       * @param bookunit ͼ�鵥λ
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
    //�Զ�����setter/getter����
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
