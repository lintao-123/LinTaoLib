package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.EnterInventoryDao;
import com.yidu.t235.xm.dao.StockCheckDao;
import com.yidu.t235.xm.dao.Impl.EnterInventoryDaoImpl;
import com.yidu.t235.xm.dao.Impl.StockCheckDaoImpl;
import com.yidu.t235.xm.entity.EnterInventory;
import com.yidu.t235.xm.entity.StockCheck;

import net.sf.json.JSONArray;

/**
 * 进货表控制层类
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:19:57
 */
@WebServlet("/EnterInventoryServlet")
public class EnterInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//定义一个数据层操作对象
	private EnterInventoryDao enterInventoryDao=new EnterInventoryDaoImpl();
	//定义一个库存表数据层操作操作
	private StockCheckDao stockCheckDao=new StockCheckDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EnterInventoryServlet() {
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
		}else if("enterInventoryAdd".equals(method)){
			//图书添加
			enterInventoryAdd(request,response);
		}else if("enterInventoryDelete".equals(method)){                                                                                                                                                                                                                               //获取来自请求的中图书编号字符串参数
			String bookNoStr=request.getParameter("cArray");
			//调用删除方法 
			enterInventoryDelete(request,response,bookNoStr);
		}else if("enterInventoryUpdate".equals(method)){
			//调用修改方法 
			enterInventoryUpdate(request,response);
		}
	}

	/**
	 * 对图书进行修改
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException IO对象
	 */
	private void enterInventoryUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		int bookNo=Integer.parseInt(request.getParameter("bookNo"));
		String bookName=request.getParameter("bookName");
		int purchaseQuantity=Integer.parseInt(request.getParameter("purchaseQuantity"));
		double bookBid=Double.parseDouble(request.getParameter("bookBid"));
		String inventoryTime=request.getParameter("inventoryTime");
		String consoleOperator=request.getParameter("consoleOperator");
		
		//封装成对象
		EnterInventory enterInventory=new EnterInventory(bookNo, bookName,purchaseQuantity,bookBid
				, inventoryTime, consoleOperator);
		
		//通过图书名称，查询获得进货表中该图书修改前的数量
		EnterInventory enterInventoryNumber=enterInventoryDao.findByName(bookName);
		//根据修改时的图书数量和修改前的图书数量进行运算，得到修改前后的图书数量差值
		//值=修改后的数量-原有数量
		/*int number=enterInventory.getPurchaseQuantity()-
				enterInventoryNumber.getPurchaseQuantity();*/
		int number=purchaseQuantity-enterInventoryNumber.getPurchaseQuantity();
		//System.out.println(number);
		//调用数据层对象实现修改新图书
		int rows=enterInventoryDao.update(enterInventory);
		
		//根据库存表数据层操作对象，对库存中该图书数量进行修改
		stockCheckDao.update(bookName, number);
		
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
	 * @param empNoStr 编号字符串
	 * @throws IOException IO异常
	 */
	private void enterInventoryDelete(HttpServletRequest request, HttpServletResponse response, String bookNoStr) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//对图书编号字符串进行分割[界面是用逗号进行连接，此处也用逗号分割]
		String[] enterInventory=bookNoStr.split(",");
		
		//创建库存表操作对象
		StockCheckDao stockCheckDao=new StockCheckDaoImpl();
		
		//由于可能是批量删除，使用异常及循环来进行判断删除是否成功
		try{
			for(String enterInventoryStr:enterInventory){
				//将当前编号字符串进行转换成整型
				int bookNo=Integer.parseInt(enterInventoryStr);
				// 根据进货表编号，在进货表对象中查询出删除前图书数量
				EnterInventory enterInventoryNumber=enterInventoryDao.findById(bookNo);
				//通过get方法获得删除前图书名称
				String name=enterInventoryNumber.getBookName();
				//通过get方法获得删除前图书数量
				int number=enterInventoryNumber.getPurchaseQuantity();
				//调用进货表数据层代码进行删除
				enterInventoryDao.delete(bookNo);
				//调用库存表数据层代码进行库存修改
				stockCheckDao.update(name, -number);
	
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
	private void enterInventoryAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		String bookName=request.getParameter("bookName");
		int purchaseQuantity=Integer.parseInt(request.getParameter("purchaseQuantity"));
		double bookBid=Double.parseDouble(request.getParameter("bookBid"));
		String inventoryTime=request.getParameter("inventoryTime");
		String consoleOperator=request.getParameter("consoleOperator");
		
		//封装成进货对象
		EnterInventory enterInventory=new EnterInventory(bookName, purchaseQuantity, bookBid,
				inventoryTime, consoleOperator);
		
		
		//调用数据层对象实现添加新图书,通过异常判断成功与失败
		try{
			//将数据添加到进货表
			enterInventoryDao.add(enterInventory);

			/*如果上步没有发生异常，则将当前数据添加到库存中*/
			
			//创建库存表数据层操作对象
			StockCheckDao stockCheckDao=new StockCheckDaoImpl();
			//在库存表中通过图书名称查询，返回图书对象
			StockCheck check=stockCheckDao.findByName(bookName);
			//根据图书名称从库存表中判断，是否存在该图书：
			if(check!=null){
				//如果存在图书，则对该图书库存数量进行修改;
				stockCheckDao.update(bookName, purchaseQuantity);
			}else{
				//如果库存表中不存在当前采购的图书，则将当前图书名称及采购数量添加库存表中
				//A:将图书名称和当前采购数量，封装成存库对象
				StockCheck stockCheck=new StockCheck(bookName, purchaseQuantity);
				//B:将采购图书作为新书入库
				stockCheckDao.add(stockCheck);
			}
			out.println("addsuccess");
		}catch(Exception e){
			out.println("addfailure");
		}

		//关闭输出对象
		out.close();
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
		List<EnterInventory> enterInventoryList=enterInventoryDao.findByPage(rows, page);
		
		//查询总记录数
		int totalRows=enterInventoryDao.enterInventoryCount();
		
		//将数据集合转换成JSON格式
		String jsonStr=JSONArray.fromObject(enterInventoryList).toString();
		//生成格式数据
		String json = "{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//输出到客户端
		out.write(json);
		//关闭输出对象
		out.close();
	}

}
