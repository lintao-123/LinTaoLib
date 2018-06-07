package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.SupplierInformationDao;
import com.yidu.t235.xm.dao.Impl.SupplierInformationDaoImpl;
import com.yidu.t235.xm.entity.SupplierInformation;

import net.sf.json.JSONArray;

/**
 * 供货商表控制层类	  
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:34:46
 */
@WebServlet("/SupplierInformationServlet")
public class SupplierInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//定义一个数据层操作对象
	private SupplierInformationDao supplierInformationDao=new SupplierInformationDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierInformationServlet() {
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
		}else if("supplierInformationAdd".equals(method)){
			//供应商添加
			supplierInformationAdd(request,response);
		}else if("supplierInformationDelete".equals(method)){
			//获取来自请求的中供应商编号字符串参数
			String supplierNumberStr=request.getParameter("cArray");
			//调用删除方法 
			supplierInformationDelete(request,response,supplierNumberStr);
		}else if("supplierInformationUpdate".equals(method)){
			//调用修改方法 
			supplierInformationUpdate(request,response);
		}
	}

	/**
	 * 对供应商进行修改
	 * @param request
	 * @param response
	 * @throws IOException IO对象
	 */
	private void supplierInformationUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		int supplierNumber=Integer.parseInt(request.getParameter("supplierNumber"));
		String supplier=request.getParameter("supplier");
		String telephoneNumbers=request.getParameter("telephoneNumbers");
		String address=request.getParameter("address");
		String contacts=request.getParameter("contacts");
		
		//封装成对象
		SupplierInformation supplierInformation=new SupplierInformation(supplierNumber, supplier, telephoneNumbers, address, contacts);
		
		//调用数据层对象实现添加新图书
		int rows=supplierInformationDao.update(supplierInformation);
		
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
	 * 删除供应商
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param empNoStr 编号字符串
	 * @throws IOException IO异常
	 */
	private void supplierInformationDelete(HttpServletRequest request, HttpServletResponse response, String supplierNumberStr) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//对图书编号字符串进行分割[界面是用逗号进行连接，此处也用逗号分割]
		String[] supplierInformation=supplierNumberStr.split(",");
		
		System.out.println("删除的图书编号:"+supplierInformation);
		//由于可能是批量删除，使用异常及循环来进行判断删除是否成功
		try{
			for(String supplierInformationStr:supplierInformation){
				//将当前编号字符串进行转换成整型
				int supplierNumber=Integer.parseInt(supplierInformationStr);
				//调用数据层代码进行删除
				supplierInformationDao.delete(supplierNumber);
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//关闭输出对象
		out.close();
	}

	/**
	 * 实现新供应商添加
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException IO异常
	 */
	private void supplierInformationAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		String supplier=request.getParameter("supplier");
		String telephoneNumbers=request.getParameter("telephoneNumbers");
		String address=request.getParameter("address");
		String contacts=request.getParameter("contacts");
		
		//封装成对象
		SupplierInformation supplierInformation=new SupplierInformation(supplier, telephoneNumbers, address, contacts);
	
		
		//调用数据层对象实现添加新供应商,通过异常判断成功与失败
		try{
			supplierInformationDao.add(supplierInformation);
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
		List<SupplierInformation> enterInventoryList=supplierInformationDao.findByPage(rows, page);
		
		//查询总记录数
		int totalRows=supplierInformationDao.supplierInformationCount();
		
		//将数据集合转换成JSON格式
		String jsonStr=JSONArray.fromObject(enterInventoryList).toString();
		//生成格式数据
		String json = "{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		
		//输出到控制台，测试用
		System.out.println(json);
		//输出到客户端
		out.write(json);
		//关闭输出对象
		out.close();
	}

}
