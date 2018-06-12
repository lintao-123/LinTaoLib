package xmbook.entity;

public class Salestable {
	//自增长列编号
	int bookno;
	//销售订单号 
	String ordernumber;
	//图书名称
	String bookname;
	//销售数量
	int salesnumber;
	//应收价格
	double shouldprice;
	//实收价格
	double realityprice;
	//销售日期
	String salestime;
	//销售员
	String saleser;
	/**待参数的构造方法
	 * @param bookno 自增长列编号 
	 * @param ordernumber 销售订单号 
	 * @param bookname图书名称
	 * @param salesnumber销售数量
	 * @param shouldprice应收价格
	 * @param realityprice 实收价格
	 * @param salestime 销售日期
	 * @param saleser 销售员
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
	 *默认构造方法 
	 */
	public Salestable() {

	}
	//自动生成的set和GET方法
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
