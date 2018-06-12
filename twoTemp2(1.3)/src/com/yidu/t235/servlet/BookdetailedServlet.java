package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.BookdetailedDao;
import com.yidu.t235.dao.impl.BookdetailedDaoImpl;
import com.yidu.t235.entity.Bookdetailed;

import net.sf.json.JSONArray;

/**
 * 销售订单表控制层类
 * @author 肖云飞
 * 日期： 2018-3-09
 */
/**
 * Servlet implementation class BookdetailedServlet
 */
@WebServlet("/BookdetailedServlet")
public class BookdetailedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //定义一个数据层操作对象
	   BookdetailedDao bookdetailedDao=new BookdetailedDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookdetailedServlet() {
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
		}else if("addBookdetailed".equals(method)){
			addBookdetailed(request,response);
			
		}else if("deleteBookdetailed".equals(method)){
			//删除编号字符串  
			String bookdetailedNoStr=request.getParameter("bookdetailedArray");
			deleteBookdetailed(request,response,bookdetailedNoStr);
		}else if("updateBookdetailed".equals(method)){
			updateBookdetailed(request,response);
		}
	}
	/**
     * 对图书进行修改
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO对象
     */
	private void updateBookdetailed(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		int bookno=Integer.parseInt(request.getParameter("bookno"));
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		String bookprice=request.getParameter("bookprice");
		String salesman=request.getParameter("salesman");
		
		//封装成对象
		Bookdetailed bookdetailed=new Bookdetailed(bookno, ordernumber, bookname,bookprice,salesman);
		
		//调用数据层对象实现添加新员工
		int rows=bookdetailedDao.update(bookdetailed);
		
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
     * @param bookdetailedNoStr 编号字符串
     * @throws IOException IO异常
     */
	private void deleteBookdetailed(HttpServletRequest request, 
			HttpServletResponse response,
			String bookdetailedNoStr) throws IOException{
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//对员工编号字符串进行分割[界面是用逗号进行连接，此处也用逗号分割]
		String[] bookdetaileds=bookdetailedNoStr.split(",");
		//由于可能是批量删除，使用异常及循环来进行判断删除是否成功
		try{
			for(String bookdetailedStr:bookdetaileds){
				//将当前编号字符串进行转换成整型
				int bookno=Integer.parseInt(bookdetailedStr);
				
				//调用数据层代码进行删除
				bookdetailedDao.delete(bookno);
				
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//关闭输出对象
		out.close();
	}

	/**
     * 实现新图书添加
     * @param request 请求对象
     * @param response 响应对象
     * @throws IOException IO异常
     */
	private void addBookdetailed(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//定义输出对象
	    PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
	    
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		String bookprice=request.getParameter("bookprice");
		String salesman=request.getParameter("salesman");
		
		//封装成对象
		Bookdetailed bookdetailed=new Bookdetailed(ordernumber,bookname, bookprice,salesman);
		
		//调用数据层对象实现添加新员工,通过异常判断成功与失败
		try{
			bookdetailedDao.add(bookdetailed);
			
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
	private void findAll(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		//定义一个输出对象
		PrintWriter out=response.getWriter();
		
		//获取datagrid中的行数和当前页码
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//调用数据层分页方式，获取数据
		List<Bookdetailed> bookdetailedList=bookdetailedDao.findByPage(rows, page);
		//获得总行数
		int totalRows=bookdetailedDao.getBookdetailedCount();
		
		//使用JSON对数据进行转换(集合转换成JSON数组)
		String jsonStr=JSONArray.fromObject(bookdetailedList).toString();
		
		//组合符合datagrid要求格式的数据字符串
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//输出到页面
		out.println(json);
		//关闭输出对象
		out.close();
	}

}