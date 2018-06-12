package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.BookoperationDao;
import com.yidu.t235.dao.impl.BookoperationDaoImpl;
import com.yidu.t235.entity.Bookoperation;
import com.yidu.t235.entity.Booktype;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class BookoperationServlet
 */
@WebServlet("/BookoperationServlet")
public class BookoperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //定义一个数据层操作对象
	   BookoperationDao bookoperationDao=new BookoperationDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookoperationServlet() {
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
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, 
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
		}else if("addbookoperation".equals(method)){
			addBookoperation(request,response);
			
		}else if("deleteBookoperation".equals(method)){
			//删除编号字符串
			String bookoperationNoStr=request.getParameter("bookoperationArray");
			deleteBookoperation(request,response,bookoperationNoStr);
		}else if("updateBookoperation".equals(method)){
			
			updateBookoperation(request,response);
			
		}
	}

	private void updateBookoperation(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		int bookno=Integer.parseInt(request.getParameter("bookno"));
		String bookname=request.getParameter("bookname");
		String booktype=request.getParameter("booktype");
		String bookauthor=request.getParameter("bookauthor");
		String supplier=request.getParameter("supplier");
		String bookprice=request.getParameter("bookprice");
		String bookunit=request.getParameter("bookunit");
		//封装成对象
		Bookoperation bookoperation=new Bookoperation(bookno, bookname, booktype,bookauthor,supplier,bookprice,bookunit);
		
		//调用数据层对象实现添加新员工
		int rows=bookoperationDao.update(bookoperation);
		
		//判断增加操作是否成功
		if(rows>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
		}
		
		//关闭输出对象
		out.close();
	}

	private void deleteBookoperation(HttpServletRequest request, 
			HttpServletResponse response,
			String bookoperationNoStr) throws IOException{
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//对员工编号字符串进行分割[界面是用逗号进行连接，此处也用逗号分割]
		String[] bookoperations=bookoperationNoStr.split(",");
		
		//由于可能是批量删除，使用异常及循环来进行判断删除是否成功
		try{
			    for(String bookoperationStr:bookoperations){
				//将当前编号字符串进行转换成整型
				int bookno=Integer.parseInt(bookoperationStr);
				//调用数据层代码进行删除
				bookoperationDao.delete(bookno);
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//关闭输出对象
		out.close();
	}

	private void addBookoperation(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		 //定义输出对象
	    PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		String bookname=request.getParameter("bookname");
		String booktype=request.getParameter("booktype");
		String bookauthor=request.getParameter("bookauthor");
		String supplier=request.getParameter("supplier");
		String bookprice=request.getParameter("bookprice");
		String bookunit=request.getParameter("bookunit");
		//封装成对象
		Bookoperation bookoperation=new Bookoperation( bookname, booktype,bookauthor,supplier,bookprice,bookunit);
		
		//调用数据层对象实现添加新员工,通过异常判断成功与失败
		try{
			bookoperationDao.add(bookoperation);
			
			out.println("addsuccess");
		}catch(Exception e){
			
			out.println("addfailure");
		}

		//关闭输出对象
		out.close();
	}

	/**
	 * 显示当前页所有数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 */

	private void findAll(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//定义一个输出对象
		PrintWriter out=response.getWriter();
		
		//获取datagrid中的行数和当前页码
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//调用数据层分页方式，获取数据
		List<Bookoperation> bookoperationList=bookoperationDao.findByPage(rows, page);
		//获得总行数
		int totalRows=bookoperationDao.getBookoperationCount();
		
		//使用JSON对数据进行转换(集合转换成JSON数组)
		String jsonStr=JSONArray.fromObject(bookoperationList).toString();
		
		//组合符合datagrid要求格式的数据字符串
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//输出到页面
		out.println(json);
		//关闭输出对象
		out.close();
	}

}