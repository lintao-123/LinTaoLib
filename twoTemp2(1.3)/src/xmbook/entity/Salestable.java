package xmbook.entity;

public class Salestable {
	//�������б��
	int bookno;
	//���۶����� 
	String ordernumber;
	//ͼ������
	String bookname;
	//��������
	int salesnumber;
	//Ӧ�ռ۸�
	double shouldprice;
	//ʵ�ռ۸�
	double realityprice;
	//��������
	String salestime;
	//����Ա
	String saleser;
	/**�������Ĺ��췽��
	 * @param bookno �������б�� 
	 * @param ordernumber ���۶����� 
	 * @param booknameͼ������
	 * @param salesnumber��������
	 * @param shouldpriceӦ�ռ۸�
	 * @param realityprice ʵ�ռ۸�
	 * @param salestime ��������
	 * @param saleser ����Ա
	 */
	public Salestable( int bookno,String ordernumber, String bookname, int salesnumber, double shouldprice,
			double realityprice, String salestime, String saleser) {
		this.bookno=bookno;
		this.ordernumber = ordernumber;
		this.bookname = bookname;
		this.salesnumber = salesnumber;
		this.shouldprice = shouldprice;
		this.realityprice = realityprice;
		this.salestime = salestime;
		this.saleser = saleser;
	}
	/**
	 *Ĭ�Ϲ��췽�� 
	 */
	public Salestable() {

	}
	//�Զ����ɵ�set��GET����
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
	public int getSalesnumber() {
		return salesnumber;
	}
	public void setSalesnumber(int salesnumber) {
		this.salesnumber = salesnumber;
	}
	public double getShouldprice() {
		return shouldprice;
	}
	public void setShouldprice(double shouldprice) {
		this.shouldprice = shouldprice;
	}
	public double getRealityprice() {
		return realityprice;
	}
	public void setRealityprice(double realityprice) {
		this.realityprice = realityprice;
	}
	public String getSalestime() {
		return salestime;
	}
	public void setSalestime(String salestime) {
		this.salestime = salestime;
	}
	public String getSaleser() {
		return saleser;
	}
	public void setSaleser(String saleser) {
		this.saleser = saleser;
	}
	
}
