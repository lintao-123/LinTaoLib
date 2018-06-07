package xmbook.servlet;

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

import xmbook.dao.SalestableDao;
import xmbook.dao.impl.SalestableDaoImpl;
import xmbook.entity.Salestable;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class salestableServlet
 */
@WebServlet("/SalestableServlet")
public class SalestableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //定义一个数据层操作对象
	SalestableDao salestabledao=new SalestableDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalestableServlet() {
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
		}else if("salestableAdd".equals(method)){
			//销售单添加
			salestableAdd(request,response);
		}else if("salestableDelete".equals(method)){
			String booknoStr=request.getParameter("salestableArray");
			//调用删除方法
			salestableDelete(request,response,booknoStr);
		}else if("salestableUpdate".equals(method)){
			//调用更新方法
			salestableUpdate(request,response);
		}
	}
	private void salestableUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		//获取来自表单的数据
		int bookno=Integer.parseInt(request.getParameter("bookno"));
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		int salesnumber=Integer.parseInt(request.getParameter("salesnumber"));
		double shouldprice=Double.parseDouble(request.getParameter("shouldprice"));
		double realityprice=Double.parseDouble(request.getParameter("realityprice"));
		String salestime=request.getParameter("salestime");
		String saleser=request.getParameter("saleser");
		//将数据封装到对象中
		Salestable salestable=new Salestable();
		salestable.setBookno(bookno);
		salestable.setOrdernumber(ordernumber);
		salestable.setBookname(bookname);
		salestable.setSalesnumber(salesnumber);
		salestable.setShouldprice(shouldprice);
		salestable.setRealityprice(realityprice);
		salestable.setSalestime(salestime);
		salestable.setSaleser(saleser);
		
		//查询名称，获得销售表修改前的销售数量
		Salestable salestablenumber=salestabledao.findName(bookname);
		//通过计算获得修改修改后的差值
		//公式：值=修改后的销售数量-修改前的销售数量
		int number=salesnumber-salestablenumber.getSalesnumber();
		//调用数据层
		int result=salestabledao.update(salestable);
		 //创建库存表（StockCheck）的数据层操作对象
		StockCheckDao stockCheckDao=new StockCheckDaoImpl();
		//调用库存表数据层修改库存数量
		stockCheckDao.update(bookname, -number);
		if(result>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
		}
		out.close();		
	}

	/**
	 * 删除销售数据
	 * @param request 请求对象
	 * @param response相应对象
	 * @param booknoStr 编号字符串
	 * @throws IOException IO异常
	 */
	private void salestableDelete(HttpServletRequest request, HttpServletResponse response, String booknoStr) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		//对销售编号字符串进行分割
		String [] salestables=booknoStr.split(",");
		//创建库存表数据操作对象
		StockCheckDao stockCheckDao=new StockCheckDaoImpl();
		//由于可能是批量删除，使用异常及循环来进行判断删除是否
		try {
			for(String salestableStr:salestables){
				//将当前编号字符创进行转换成整形
				int bookno=Integer.parseInt(salestableStr);
				/*查询出删除前的图书数量和名称*/
				//创建一个对象来接收查编号方法返回的对象
				Salestable salestableNumber=salestabledao.findId(bookno);
				//调用get方法获得名称
				String name=salestableNumber.getBookname();
				//调用get方法获得数量
				int number=salestableNumber.getSalesnumber();
				//调用销售表数据层代码进行删除
				salestabledao.delete(bookno);
				//调用库存表数据层代码进行修改
				stockCheckDao.update(name, number);
			}
			out.println("deletesuccess");
		} catch (NumberFormatException e) {
			out.println("deletefailure");
		}
		//关闭输出对象
		out.close();
	}
	/**
	 * 实现销售表数据添加
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException IO异常
	 */
	private void salestableAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		int salesnumber=Integer.parseInt(request.getParameter("salesnumber"));
		double shouldprice=Double.parseDouble(request.getParameter("shouldprice"));
		double realityprice=Double.parseDouble(request.getParameter("realityprice"));
		String salestime=request.getParameter("salestime");
		String saleser=request.getParameter("saleser");
		//将数据封装到对象中
		Salestable salestable=new Salestable(salesnumber, ordernumber,bookname,salesnumber,shouldprice,realityprice,salestime,saleser);
		
		
		//调用数据层对象实现添加新员工，通过异常判断成功与失败
		try {
			salestabledao.add(salestable);
			
			//创建库存表（StockCheck）的数据层操作对象
			StockCheckDao stockCheckDao=new StockCheckDaoImpl();
			
			//根据图书名称修改库存数量
			stockCheckDao.update(bookname, -salesnumber);
			out.println("addsuccess");
		} catch (Exception e) {
			out.print("addfailure");
		}
		//关闭输出对象
		out.close();
	}
	/**
	 * 实现显示当前页面的所有数据
	 * @param request 请求对象
	 * @param response 相应对象
	 * @throws IOException IO异常对象
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建输出对象
		PrintWriter out=response.getWriter();
		//获取从datagrid组件传递过来的页记录书及当前页码
		int rows=Integer.parseInt(request.getParameter("rows"));
		int pages=Integer.parseInt(request.getParameter("page"));
		//执行分页查询
		List<Salestable> salestables=salestabledao.findByPage(rows, pages);
		//查询记录书
		int totalRows=salestabledao.salestableCount(); 
		//将数据集合转换成JSON格式
		String jsonStr=JSONArray.fromObject(salestables).toString();
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//输出到客户端
		out.write(json);
		//关闭输出对象
		out.close();		
	}
}
