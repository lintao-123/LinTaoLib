package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.ProcureDao;
import com.yidu.t235.dao.impl.ProcureDaoImpl;
import com.yidu.t235.entity.Procure;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class ProcureServlet
 */
@WebServlet("/ProcureServlet")
public class ProcureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 //定义一个数据层操作对象
	 ProcureDao procureDao=new ProcureDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcureServlet() {
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
				}else if("addProcure".equals(method)){
					addProcure(request,response);
					
				}else if("deleteProcure".equals(method)){
					//删除编号字符串  
					String procureNoStr=request.getParameter("procureArray");
					deleteProcure(request,response,procureNoStr);
				}else if("updateProcure".equals(method)){
				    updateProcure(request,response);
				}
			}

	private void updateProcure(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		int bookid=Integer.parseInt(request.getParameter("bookid"));
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		int salesvolume=Integer.parseInt(request.getParameter("salesvolume"));
		double bid=Double.parseDouble(request.getParameter("bid"));
		String salesperiods=request.getParameter("salesperiods");
		String operator=request.getParameter("operator");
		
		//封装成对象
		Procure procure=new Procure(bookid,ordernumber,bookname,salesvolume,bid,salesperiods,operator);
	
		//调用数据层对象实现添加新员工
		int rows=procureDao.update(procure);
		
		//判断增加操作是否成功
		if(rows>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
		}
		
		//关闭输出对象
		out.close();
	}

	private void deleteProcure(HttpServletRequest request, 
			HttpServletResponse response, String procureNoStr) throws IOException{
		        //定义输出对象
				PrintWriter out=response.getWriter();
				
				//对员工编号字符串进行分割[界面是用逗号进行连接，此处也用逗号分割]
				String[] procures=procureNoStr.split(",");
				//由于可能是批量删除，使用异常及循环来进行判断删除是否成功
				try{
					for(String procureStr:procures){
						//将当前编号字符串进行转换成整型
						int bookid=Integer.parseInt(procureStr);
						
						//调用数据层代码进行删除
						procureDao.delete(bookid);
						
					}
					out.println("deletesuccess");
				}catch(Exception e){
					out.println("deletefailure");
				}
				
				//关闭输出对象
				out.close();
			}

	private void addProcure(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//定义输出对象
	    PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
	    
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		int salesvolume=Integer.parseInt(request.getParameter("salesvolume"));
		double bid=Double.parseDouble(request.getParameter("bid"));
		 
		String salesperiods=request.getParameter("salesperiods");
		String operator=request.getParameter("operator");
		
		//封装成对象
		Procure procure=new Procure(ordernumber,bookname, salesvolume,bid ,salesperiods,operator);
		
		//调用数据层对象实现添加新员工,通过异常判断成功与失败
		try{
			procureDao.add(procure);
			
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

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws 
	IOException{
		        //定义一个输出对象
				PrintWriter out=response.getWriter();
				
				//获取datagrid中的行数和当前页码
				int rows=Integer.parseInt(request.getParameter("rows"));
				int page=Integer.parseInt(request.getParameter("page"));
				
				//调用数据层分页方式，获取数据
				List<Procure> procureList=procureDao.findByPage(rows, page);
				//获得总行数
				int totalRows=procureDao.getProcureCount();
				
				//使用JSON对数据进行转换(集合转换成JSON数组)
				String jsonStr=JSONArray.fromObject(procureList).toString();
				
				//组合符合datagrid要求格式的数据字符串
				String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
				//输出到页面
				out.println(json);
				//关闭输出对象
				out.close();
			}

		}