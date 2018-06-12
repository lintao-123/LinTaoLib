package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.SalesinquiryDao;
import com.yidu.t235.dao.impl.SalesinquiryDaoImpl;
import com.yidu.t235.entity.Salesinquiry;

import net.sf.json.JSONArray;

/**
 * 销售订单详情表控制层类
 * @author 肖云飞
 * 日期： 2018-3-09
 */
/**
 * Servlet implementation class SalesinquiryServlet
 */
@WebServlet("/SalesinquiryServlet")
public class SalesinquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //定义一个数据层操作对象
	SalesinquiryDao salesinquiryDao=new SalesinquiryDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesinquiryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		 //设置
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//根据路径后操作方式调用操作方法
		String method=request.getParameter("method");
		
		//判断操作方式
		if("findAll".equals(method)){
			findAll(request,response);
		}else if("addSalesinquiry".equals(method)){
			addSalesinquiry(request,response);
			
		}else if("deleteSalesinquiry".equals(method)){
			//删除编号字符串
			String salesinquiryNoStr=request.getParameter("salesinquiryArray");
			deleteSalesinquiry(request,response,salesinquiryNoStr);
		}else if("updateSalesinquiry".equals(method)){
			updateSalesinquiry(request,response);
		}
	}
	 /**
     * 对图书进行修改
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
	private void updateSalesinquiry(HttpServletRequest request, HttpServletResponse response) throws 
	IOException{
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		int ordernumber=Integer.parseInt(request.getParameter("ordernumber"));
		String ordernumberid=request.getParameter("ordernumberid");
		String bookname=request.getParameter("bookname");
		String booktype=request.getParameter("booktype");
		double bookprice=Double.parseDouble(request.getParameter("bookprice"));
		int salesvolume=Integer.parseInt(request.getParameter("salesvolume"));
		//封装成对象
		Salesinquiry salesinquiry=new Salesinquiry(ordernumber, ordernumberid, bookname,booktype,bookprice,salesvolume );
		
		//调用数据层对象实现添加新员工
		int rows=salesinquiryDao.update(salesinquiry);
		
		//判断增加操作是否成功
		if(rows>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
		}
		
		//关闭输出对象
		out.close();
	}

	/**
     * 删除图书
     * @param request 请求对象
     * @param response 响应对象
     * @param salesinquiryNoStr 编号字符串
     * @throws IOException IO异常
     */
	private void deleteSalesinquiry(HttpServletRequest request, HttpServletResponse response,
			String salesinquiryNoStr) throws IOException{
		 //定义输出对象
		 PrintWriter out=response.getWriter();
		
		//对员工编号字符串进行分割[界面是用逗号进行连接，此处也用逗号分割]
		String[] salesinquirys=salesinquiryNoStr.split(",");
		
		//由于可能是批量删除，使用异常及循环来进行判断删除是否成功
		try{
			for(String salesinquiryStr:salesinquirys){
				//将当前编号字符串进行转换成整型
				int ordernumber=Integer.parseInt(salesinquiryStr);
				//调用数据层代码进行删除
				salesinquiryDao.delete(ordernumber);
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//关闭输出对象
		out.close();
	}
	 /**
     * 实现图书添加
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
	private void addSalesinquiry(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//定义输出对象
	    PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		String ordernumberid=request.getParameter("ordernumberid");
		String bookname=request.getParameter("bookname");
		String booktype=request.getParameter("booktype");
		double bookprice=Double.parseDouble(request.getParameter("bookprice"));
		int salesvolume=Integer.parseInt(request.getParameter("salesvolume"));
	
		//封装成对象
		Salesinquiry salesinquiry=new Salesinquiry( ordernumberid, bookname,booktype,bookprice,salesvolume );
		salesinquiryDao.add(salesinquiry);
		//调用数据层对象实现添加新员工,通过异常判断成功与失败
		try{
		
			
			out.println("addsuccess");
		}catch(Exception e){
			
			out.println("addfailure");
		}

		//关闭输出对象
		out.close();
	}

	/**
	 * 显示当前页所有数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException 异常 
	 */


	private void findAll(HttpServletRequest request, HttpServletResponse response) throws 
	IOException{
		 //定义一个输出对象
		PrintWriter out=response.getWriter();
		
		//获取datagrid中的行数和当前页码
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//调用数据层分页方式，获取数据
		List<Salesinquiry> salesinquiryList=salesinquiryDao.findByPage(rows, page);
		//获得总行数
		int totalRows=salesinquiryDao.getSalesinquiryCount();
		
		//使用JSON对数据进行转换(集合转换成JSON数组)
		String jsonStr=JSONArray.fromObject(salesinquiryList).toString();
		
		//组合符合datagrid要求格式的数据字符串
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//输出到页面
		out.println(json);
		//关闭输出对象
		out.close();
	}

}