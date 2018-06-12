package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.OperatorsDao;
 
import com.yidu.t235.xm.dao.Impl.OperatorsDaoImpl;
 
import com.yidu.t235.xm.entity.Operators;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class OperatorsServlet
 */
@WebServlet("/OperatorsServlet")
public class OperatorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OperatorsDao operatorsDao=new OperatorsDaoImpl();
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperatorsServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应类型及编码集
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String method=request.getParameter("method");
		
		if("findAll".equals(method)){
			findAll(request,response);
		}else if("addOperators".equals(method)){
			addOperators(request,response);
			
		}else if("deleteOperators".equals(method)){
			String operatorsStr=request.getParameter("operatorsArray");
			deleteOperators(request,response,operatorsStr);
		}else if("updateOperators".equals(method)){
			updateOperators(request,response);
		}
		
	}

	private void updateOperators(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//接收网页数据
		int userId=Integer.parseInt(request.getParameter("userId"));
		 
		String name=request.getParameter("name");
	 
		String address=request.getParameter("address");
		String telephoneNumber=request.getParameter("telephoneNumber");
		String empType=request.getParameter("empType");
		double empMoney=Double.parseDouble(request.getParameter("empMoney") );
		
		Operators operators=new Operators(userId,  name,  address,
				telephoneNumber,empType,empMoney ); 
		//调用数据层
		int result=operatorsDao.update(operators);
		
	/*	String salesReport2=(String) request.getAttribute("salesReport");
		System.out.println(salesReport2);
	 	int result2=salesReportDao.update(salesReport);
		*/
		if(result>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
		}
		out.close();
		
	}

	private void deleteOperators(HttpServletRequest request, HttpServletResponse response, String operatorsStr) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		String [] operators=operatorsStr.split(",");
		try {
			for(String operatorsNo:operators){
				int operatorsId=Integer.parseInt( operatorsNo );
				operatorsDao.delete(operatorsId);
			}
			out.println("deletesuccess");
		} catch (Exception e) {
			 out.println("failure");
		}
		out.close();
	}

	private void addOperators(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//接收网页数据
		 
		 
		String name=request.getParameter("name");
	 
		String address=request.getParameter("address");
		String telephoneNumber=request.getParameter("telephoneNumber");
		String empType=request.getParameter("empType");
		double empMoney=Double.parseDouble(request.getParameter("empMoney") );
		
		Operators operators=new Operators(   name,  address,
				telephoneNumber,empType,empMoney ); 
		 
		try {
			operatorsDao.add(operators);
			out.println("addsuccess");
		} catch (Exception e) {
			 out.println("addfailure");
		}
		out.close();
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//接收网页数据
		int rows =Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//调用数据层
		List<Operators> operatorsList= operatorsDao.findPage(rows, page);
		
		//获取总行数
		int totalRows=operatorsDao.getCount();
		//使用json对数据进行转换
		String jsonStr=JSONArray.fromObject(operatorsList).toString();
		//组成符合datagrid的格式
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		
		out.println(json);
		
		out.close();
	}

}
