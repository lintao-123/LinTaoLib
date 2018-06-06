package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.StockCheckDao;
import com.yidu.t235.xm.dao.Impl.StockCheckDaoImpl;
import com.yidu.t235.xm.entity.StockCheck;

import net.sf.json.JSONArray;

/**
 * 库存表控制层类	  
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:34:46
 */
@WebServlet("/StockCheckServlet")
public class StockCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//定义一个数据层操作对象
	private StockCheckDao stockCheckDao=new StockCheckDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public StockCheckServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应信息类型和字符集
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//设置请求字符集
		request.setCharacterEncoding("utf-8");
		
		//获取来自请求的method参数值
		String method=request.getParameter("method");
		
		//根据参数值判断用户操作
		if("findAll".equals(method)){
			//如果显示当前页所有数据
			findAll(request,response);
		} 
	}
	/**
	 * 实现显示当前面所有数据
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException  异常
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出对象
		PrintWriter out=response.getWriter();
		
		//获取从datagrid组件传递过来的页记录数及当前页码
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//执行分页查询
		List<StockCheck> stockCheckList=stockCheckDao.findByPage(rows, page);
		
		//查询总记录数
		int totalRows=stockCheckDao.stockCheckCount();
		
		//将数据集合转换成JSON格式
		String jsonStr=JSONArray.fromObject(stockCheckList).toString();
		//生成格式数据
		String json = "{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		
		//输出到客户端
		out.write(json);
		//关闭输出对象
		out.close();
	}

}
