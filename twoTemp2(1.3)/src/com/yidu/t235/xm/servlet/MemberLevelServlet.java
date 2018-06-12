package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.MemberLevelDao;
import com.yidu.t235.xm.dao.Impl.MemberLevelDaoImpl;
import com.yidu.t235.xm.entity.MemberLevel;

import net.sf.json.JSONArray;

/**
 * 会员等级表控制层类	  
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:34:46
 */		  
@WebServlet("/MemberLevelServlet")
public class MemberLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//定义一个数据层操作对象
	private MemberLevelDao memberLevelDao=new MemberLevelDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLevelServlet() {
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
		System.out.println(method);
		
		//根据参数值判断用户操作
		if("findAll".equals(method)){
			//如果显示当前页所有数据
			findAll(request,response);
		}else if("memberLevelAdd".equals(method)){
			//会员添加
			memberLevelAdd(request,response);
		}else if("memberLevelDelete".equals(method)){
			//获取来自请求的中等级编号字符串参数
			String levelNumberStr=request.getParameter("cArray");
			//调用删除方法 
			memberLevelDelete(request,response,levelNumberStr);
		}else if("memberLevelUpdate".equals(method)){
			
			//调用修改方法 
			memberLevelUpdate(request,response);
		}
	}

	/**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
	 * 对信息进行修改
	 * @param request 请求
	 * @param response 响应
	 * @throws IOException IO对象
	 */
	private void memberLevelUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		int levelNumber=Integer.parseInt(request.getParameter("levelNumber"));
		String levelName=request.getParameter("levelName");
		double memberDiscount=Double.parseDouble(request.getParameter("memberDiscount"));
		String upgradeIntegral=request.getParameter("upgradeIntegral");
		
		//封装成对象
		MemberLevel memberLevel=new MemberLevel(levelNumber, levelName, memberDiscount, upgradeIntegral);
		
		//调用数据层对象实现添加新等级
		int rows=memberLevelDao.update(memberLevel);
				
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
	 * 删除等级信息
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param empNoStr 编号字符串
	 * @throws IOException IO异常
	 */
	private void memberLevelDelete(HttpServletRequest request, HttpServletResponse response, String levelNumberStr) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//对会员编号字符串进行分割[界面是用逗号进行连接，此处也用逗号分割]
		String[] memberLevel=levelNumberStr.split(",");
		
		System.out.println("删除的等级编号:"+memberLevel);
		//由于可能是批量删除，使用异常及循环来进行判断删除是否成功
		try{
			for(String memberLevelStr:memberLevel){
				//将当前编号字符串进行转换成整型
				int levelNumber=Integer.parseInt(memberLevelStr);
				//调用数据层代码进行删除
				memberLevelDao.delete(levelNumber);
				
				
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//关闭输出对象
		out.close();
	}

	/**
	 * 实现新等级信息添加
	 * @param request 请求对象
	 * @param response 响应对象
	 * @throws IOException IO异常
	 */
	private void memberLevelAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//定义输出对象
		PrintWriter out=response.getWriter();
		
		//获取来自表单的数据
		String levelName=request.getParameter("levelName");
		double memberDiscount=Double.parseDouble(request.getParameter("memberDiscount"));
		String upgradeIntegral=request.getParameter("upgradeIntegral");
		
		//封装成对象
		MemberLevel memberLevel=new MemberLevel(levelName, memberDiscount, upgradeIntegral);
		
		//调用数据层对象实现添加新等级,通过异常判断成功与失败
		try{
			memberLevelDao.add(memberLevel);
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
		List<MemberLevel> memberLevelList=memberLevelDao.findByPage(rows, page);
		
		//查询总记录数
		int totalRows=memberLevelDao.memberLevelCount();
		
		//将数据集合转换成JSON格式
		String jsonStr=JSONArray.fromObject(memberLevelList).toString();
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
