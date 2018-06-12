package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.BooktypeDao;
import com.yidu.t235.dao.impl.BooktypeDaoImpl;
import com.yidu.t235.entity.Booktype;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class BooktypeServlet
 */
@WebServlet("/BooktypeServlet")
public class BooktypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //定义一个数据层操作对象
	BooktypeDao booktypeDao=new BooktypeDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooktypeServlet() {
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
				}else if("addBooktype".equals(method)){
					addBooktype(request,response);
					
				}else if("deleteBooktype".equals(method)){
					//删除编号字符串
					String booktypeNoStr=request.getParameter("booktypeArray");
					deleteBooktype(request,response,booktypeNoStr);
				}else if("updateBooktype".equals(method)){
					updateBooktype(request,response);
				}
			}

	private void updateBooktype(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		        //定义输出对象
				PrintWriter out=response.getWriter();
				
				//获取来自表单的数据
				int typeid=Integer.parseInt(request.getParameter("typeid"));
				String typename=request.getParameter("typename");
				String typedetailed=request.getParameter("typedetailed");
				
				//封装成对象
				Booktype booktype=new Booktype(typeid, typename, typedetailed);
				
				//调用数据层对象实现添加新员工
				int rows=booktypeDao.update(booktype);
				
				//判断增加操作是否成功
				if(rows>0){
					out.println("updatesuccess");
				}else{
					out.println("updatefailure");
				}
				
				//关闭输出对象
				out.close();
			}

	private void deleteBooktype(HttpServletRequest request, 
			HttpServletResponse response, 
			String booktypeNoStr) throws IOException{
		        //定义输出对象
				PrintWriter out=response.getWriter();
				
				//对员工编号字符串进行分割[界面是用逗号进行连接，此处也用逗号分割]
				String[] booktypes=booktypeNoStr.split(",");
				
				//由于可能是批量删除，使用异常及循环来进行判断删除是否成功
				try{
					for(String booktypeStr:booktypes){
						//将当前编号字符串进行转换成整型
						int typeid=Integer.parseInt(booktypeStr);
						//调用数据层代码进行删除
						booktypeDao.delete(typeid);
					}
					out.println("deletesuccess");
				}catch(Exception e){
					out.println("deletefailure");
				}
				
				//关闭输出对象
				out.close();
			}


	private void addBooktype(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		        //定义输出对象
			    PrintWriter out=response.getWriter();
				
				//获取来自表单的数据
				String typename=request.getParameter("typename");
				String typedetailed=request.getParameter("typedetailed");
				
				//封装成对象
				Booktype booktype=new Booktype( typename, typedetailed);
				
				//调用数据层对象实现添加新员工,通过异常判断成功与失败
				try{
					booktypeDao.add(booktype);
					
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
				List<Booktype> booktypeList=booktypeDao.findByPage(rows, page);
				//获得总行数
				int totalRows=booktypeDao.getBooktypeCount();
				
				//使用JSON对数据进行转换(集合转换成JSON数组)
				String jsonStr=JSONArray.fromObject(booktypeList).toString();
				
				//组合符合datagrid要求格式的数据字符串
				String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
				//输出到页面
				out.println(json);
				//关闭输出对象
				out.close();
			}

		}