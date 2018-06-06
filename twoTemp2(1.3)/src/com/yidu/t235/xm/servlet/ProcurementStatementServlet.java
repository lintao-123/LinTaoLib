package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.ProcurementStatementDao;
import com.yidu.t235.xm.dao.Impl.ProcurementStatementDaoImpl;
import com.yidu.t235.xm.entity.ProcurementStatement;

import net.sf.json.JSONArray;

/**
 * 采购订单详情表servlet Servlet implementation class ProcurementStatementServlet
 */
@WebServlet("/ProcurementStatementServlet")
public class ProcurementStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProcurementStatementDao procurementStatementDao = new ProcurementStatementDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcurementStatementServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应类型和编码集
		response.setContentType("text/html;charset=utf-8");
		//设置请求字符集
		request.setCharacterEncoding("utf-8");
		//获取来自请求method的参数值
		String method = request.getParameter("method");
		//根据参数值判断用户操作
		if ("findAll".equals(method)) {
			//显示当前页所有数据
			findAll(request, response);
		} else if ("addProcurement".equals(method)) {
			//调用添加方法
			addProcurement(request, response);
		} else if ("deleteProcurement".equals(method)) {
			//获取来自请求中的编号字符参数
			String procurementStr = request.getParameter("procurementArray");
			//调用删除方法
			deleteProcurement(request, response, procurementStr);
		} else if ("updateProcurement".equals(method)) {
			//调用修改方法
			updateProcurement(request, response);
		}
	}

	/**
	 * 修改采购订单详情
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @throws IOException
	 *             IO异常
	 */
	private void updateProcurement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 定义输出对象
		PrintWriter out = response.getWriter();

		/*接收网页数据*/
		//接收编号
		int tempId=Integer.parseInt(request.getParameter("tempId"));
		//接收采购订单号
		String procurementNo=request.getParameter("procurementNo");
		//接收图书名称
		String  bookName=request.getParameter("bookName");
		//接收图书类型
		String bookType=request.getParameter("bookType");
		//接收图书进价
		double bookBid=Double.parseDouble(request.getParameter("bookBid")) ;
		//接收供应商
		String supplier=request.getParameter("supplier");
		//接收采购时间
		String libraryTime=request.getParameter("libraryTime");
		//接收采购数量
		int purchaseQuantity=Integer.parseInt(request.getParameter("purchaseQuantity"));
		//接收操作员
		String playEmp=request.getParameter("playEmp");
		//把采购详情订单对象封装
		ProcurementStatement procurementStatemen=new ProcurementStatement(tempId, procurementNo, bookName, bookType, bookBid, supplier, libraryTime, purchaseQuantity, playEmp);

		//调用数据层对象实现修改订单详情并把返回值赋给变量
		int rows = procurementStatementDao.update(procurementStatemen);
		//判断修改是否成功
		if (rows > 0) {
			//成功（success）
			out.println("updatesuccess");
		} else {
			//失败(failure)
			out.println("updatefailure");
		}
		//关闭输出对象	
		out.close();
	}

	/**
	 * 删除采购订单详情
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @param procurementStr
	 *            字符串
	 * @throws IOException
	 *             IO异常
	 */
	private void deleteProcurement(HttpServletRequest request, HttpServletResponse response, String procurementStr)
			throws IOException {
		// 定义输出对象
		PrintWriter out = response.getWriter();
		//对编号字符串进行分割，[界面是用逗号进行连接，此处也用逗号进行分割]
		String[] procurements = procurementStr.split(",");
		//由于可能是批量删除，使用异常和循环来判断是否成功
		try {
			for (String procurementNo : procurements) {
				//将当前编号字符串转换成整形
				int procurement = Integer.parseInt(procurementNo);
				//调用数据层代码实现删除
				procurementStatementDao.delete(procurement);
			}
			//成功（success）
			out.println("deletesuccess");
		} catch (Exception e) {
			//失败（failure）
			out.println("deletefailure");
		}
		//关闭输出对象
		out.close();
	}

	/**
	 * 添加订单详情
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @throws IOException
	 *             IO异常
	 */
	private void addProcurement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 定义输出对象
		PrintWriter out = response.getWriter();

		/*接收网页数据*/
		//接收采购订单编号
		String procurementNo = request.getParameter("procurementNo");
		//接收图书名称
		String bookName = request.getParameter("bookName");
		//接收图书类型
		String bookType = request.getParameter("bookType");
		//接收图书进价
		double bookBid = Double.parseDouble(request.getParameter("bookBid"));
		//接收供应商
		String supplier = request.getParameter("supplier");
		//接收采购时间
		String libraryTime = request.getParameter("libraryTime");
		//接收采购数量
		int purchaseQuantity = Integer.parseInt(request.getParameter("purchaseQuantity"));
		//接收操作员
		String playEmp = request.getParameter("playEmp");
		//把采购详情订单对象进行封装
		ProcurementStatement procurementStatemen = new ProcurementStatement(procurementNo,
				bookName, bookType, bookBid, supplier, libraryTime, purchaseQuantity, playEmp);

		//通过异常判断是否成功

		try {
			//调用数据层进行添加
			procurementStatementDao.add(procurementStatemen);
			//成功（success）
			out.println("addsuccess");
		} catch (Exception e) {
			//失败（failure）
			out.println("addfailure");
		}
		//关闭输出对象
		out.close();
	}

	/**
	 * 实现显示页面所有数据
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @throws IOException
	 *             IO异常
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 定义输出对象
		 PrintWriter out=response.getWriter();

		/* 接收网页数据*/	
		//接收行数
		 int rows=Integer.parseInt(request.getParameter("rows"));
		//接收页数
		 int page=Integer.parseInt(request.getParameter("page"));

		//调用数据层执行分页查询
		
		List<ProcurementStatement> procurementStatementsList=
				procurementStatementDao.findPage(rows, page) ;

		//调用数据层查询出总记录数
		int totalRows=procurementStatementDao.getCount();

		//将数据集合转换成JSON格式
		String jsonStr=JSONArray.fromObject(procurementStatementsList).toString();
		// 组合成符合datagrid格式的数据字符串
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		// 输出json数据
		out.println(json);
		//关闭输出对象
		out.close();
	}

}
