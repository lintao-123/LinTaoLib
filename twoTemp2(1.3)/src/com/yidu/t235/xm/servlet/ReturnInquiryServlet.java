package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.ReturnInquiryDao;
import com.yidu.t235.xm.dao.StockCheckDao;
import com.yidu.t235.xm.dao.Impl.ReturnInquiryDaoImpl;
import com.yidu.t235.xm.dao.Impl.StockCheckDaoImpl;
import com.yidu.t235.xm.entity.ReturnInquiry;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ReturnInquiryServlet
 */
@WebServlet("/ReturnInquiryServlet")
public class ReturnInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		//销售退货表操作对象
       private ReturnInquiryDao returnInquiryDao=new ReturnInquiryDaoImpl();
       //库存表操作对象
       private StockCheckDao stockCheckDao=new StockCheckDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnInquiryServlet() {
  
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应类型和字符集编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		//接收网页method传递的数据
		String method=request.getParameter("method");
		
		//判断调用的方法
		if("findAll".equals(method)){
			findAll(request,response);
		}else if("addReturnInquiry".equals(method)){
			addReturnInquiry(request,response);
		}else if("deleteReturnInquiry".equals(method)){
			String orderNumberStr =request.getParameter("returnInquiryArray");
			deleteReturnInquiry(request,response,orderNumberStr);
		}else if("updateReturnInquiry".equals(method)){
			updateReturnInquiry(request,response);
		}
	}

	private void updateReturnInquiry(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//接收网页数据
		int tempId=Integer.parseInt(request.getParameter("tempId"));
		String orderNumber= request.getParameter("orderNumber") ;
		String bookName=request.getParameter("bookName");
		 
		double bookPrice=Double.parseDouble(request.getParameter("bookPrice"));
		int salesVolume=Integer.parseInt(request.getParameter("salesVolume"));
		String salesPeriods=request.getParameter("salesPeriods");
		String consoleOperator=request.getParameter("consoleOperator");
		
		ReturnInquiry returnInquiry=new ReturnInquiry(tempId,orderNumber,bookName ,bookPrice,salesVolume,
				salesPeriods,consoleOperator);
		/*通过查找Id方法得到销售退货对象里的修改前数量,并计算出修改前和修改后的差值*/
		//调用销售退货数据层进行查Id
		ReturnInquiry returnInquiryNumber=returnInquiryDao.findById(tempId);
		//差值=修改后-修改前
		int number=salesVolume-returnInquiryNumber.getSalesVolume();
		//调用销售退货数据层进行修改
		int rows=returnInquiryDao.update(returnInquiry);
		//调用库存表数据层进行修改
		stockCheckDao.update(bookName, number);
		if(rows>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
			
		}
		out.close();
		
	}

	private void deleteReturnInquiry(HttpServletRequest request, HttpServletResponse response, String orderNumberStr) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		//对字符串进行分割
		String [] orderNumbers=orderNumberStr.split(",");
		 try {
			for(String orderNumber:orderNumbers){
				int orderNumberNO=Integer.parseInt(orderNumber );
				//根据Id查询出数量和名称
				ReturnInquiry returnInquiryNumber=returnInquiryDao.findById(orderNumberNO);
				//使用实体类get方法获得名称
				String bookName=returnInquiryNumber.getBookName();
				//使用实体类get方法获得数量
				int number=returnInquiryNumber.getSalesVolume();
				//调用销售退货表数据层进行删除
				returnInquiryDao.delete(orderNumberNO);
				//调用库存表数据层进行修改
				stockCheckDao.update(bookName, -number);
			}
			out.println("deletesuccess");
		} catch (Exception e) {
			out.println("deletefailure");
		}
		
		out.close();
	}

	private void addReturnInquiry(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		//接收网页数据
		String orderNumber= request.getParameter("orderNumber") ;
		String bookName=request.getParameter("bookName");
		 
		double bookPrice=Double.parseDouble(request.getParameter("bookPrice"));
		int salesVolume=Integer.parseInt(request.getParameter("salesVolume"));
		String salesPeriods=request.getParameter("salesPeriods");
		String consoleOperator=request.getParameter("consoleOperator");
		
		ReturnInquiry returnInquiry=new ReturnInquiry(orderNumber,bookName ,bookPrice,salesVolume,
				salesPeriods,consoleOperator);
		try {
			//调用销售退货数据层进行添加
			returnInquiryDao.add(returnInquiry);
			//调用销售退货数据层进行修改
			stockCheckDao.update(bookName,salesVolume);
			out.println("addsuccess");
		} catch (Exception e) {
			out.println("addfailure");
		}
	}
	
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//接收网页数据
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//调用数据层
		List<ReturnInquiry> returnInquiriesList=returnInquiryDao.findPage(rows, page);
		
		//获取总行数
		int rowsCount=returnInquiryDao.getCount();
		//使用json对数据进行转换
		String jsonStr=JSONArray.fromObject(returnInquiriesList).toString();
		//组合成符合json格式字符串
		String json="{\"total\":"+rowsCount+",\"rows\":"+jsonStr+"}";		
		out.println(json);
		out.close();
			
	}

}
