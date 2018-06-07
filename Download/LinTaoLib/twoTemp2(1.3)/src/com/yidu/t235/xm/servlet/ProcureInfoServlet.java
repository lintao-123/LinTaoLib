package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.ProcureInfoDao;
import com.yidu.t235.xm.dao.Impl.ProcureInfoDaoImpl;
import com.yidu.t235.xm.entity.ProcureInfo;

import net.sf.json.JSONArray;

/**
 * 采购订单表
 * servlet Servlet implementation class ProcureInfoServlet
 */
@WebServlet("/ProcureInfoServlet")
public class ProcureInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProcureInfoDao procureInfoDao = new ProcureInfoDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcureInfoServlet() {
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
		// 设置 响应类型及字符集
		response.setContentType("text/html;charset=utf-8");
		// 设置请求字符集
		request.setCharacterEncoding("utf-8");
		// 获取来自请求method的参数值
		String method = request.getParameter("method");
		// 根据参数值判断用户操作
		if ("findAll".equals(method)) {
			// 显示当前页所有数据
			findAll(request, response);
		} else if ("addProcureInfo".equals(method)) {
			// 调用添加方法
			addProcureInfo(request, response);
		} else if ("deleteProcureInfo".equals(method)) {
			// 获取来自请求中的编号字符参数
			String procureInfoStr = request.getParameter("procurementArray");
			// 调用删除方法
			deleteProcureInfo(request, response, procureInfoStr);
		} else if ("updateProcureInfo".equals(method)) {
			// 调用修改方法
			updateProcureInfo(request, response);
		}

	}

	/**
	 * 对采购订单进行修改
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @throws IOException
	 *             IO异常
	 */
	private void updateProcureInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置输出对象
		PrintWriter out = response.getWriter();

		/* 接收网页数据 */
		// 接收编号
		int tempId = Integer.parseInt(request.getParameter("tempId"));
		// 接收订单号
		String procurementNo = request.getParameter("procurementNo");
		// 接收供应商
		String supplier = request.getParameter("supplier");
		// 接收采购时间
		String libraryTime = request.getParameter("libraryTime");

		// 把采购订单对象封装
		ProcureInfo procureInfo = new ProcureInfo(tempId, procurementNo, supplier, libraryTime);
		// 调用数据层对象实现修改订单并把返回值赋给变量
		int rows = procureInfoDao.update(procureInfo);
		// 判断修改是否成功
		if (rows > 0) {
			// 成功（success）
			out.println("updatesuccess");
		} else {
			// 失败(failure)
			out.println("updatefailure");
		}
		// 关闭输出对象
		out.close();
	}

	/**
	 * 删除采购订单
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @param procureInfoStr
	 *            字符串
	 * @throws IOException
	 *             IO异常
	 */
	private void deleteProcureInfo(HttpServletRequest request, HttpServletResponse response, String procureInfoStr)
			throws IOException {
		// 设置输出对象
		PrintWriter out = response.getWriter();
		// 对编号字符串进行分割，[界面是用逗号进行连接，此处也用逗号进行分割]
		String[] procureInfos = procureInfoStr.split(",");
		// 由于可能是批量删除，使用异常和循环来判断是否成功
		try {
			for (String procureInfo : procureInfos) {
				// 将当前编号字符串转换成整形
				int tempId = Integer.parseInt(procureInfo);
				// 调用数据层代码实现删除
				procureInfoDao.delete(tempId);
			}
			// 成功（success）
			out.println("deletesuccess");
		} catch (Exception e) {
			// 失败（failure）
			out.println("deletefailure");
		}
		// 关闭输出对象
		out.close();

	}

	/**
	 * 添加采购订单
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @throws IOException
	 *             IO异常
	 */
	private void addProcureInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置输出对象
		PrintWriter out = response.getWriter();

		/* 接收网页数据 */
		// 接收采购订单编号
		String procurementNo = request.getParameter("procurementNo");
		// 接收供应商
		String supplier = request.getParameter("supplier");
		// 接收采购时间
		String libraryTime = request.getParameter("libraryTime");
		// 把采购订单对象进行封装
		ProcureInfo procureInfo = new ProcureInfo(procurementNo, supplier, libraryTime);
		// 通过异常判断是否成功
		try {
			// 调用数据层进行添加
			procureInfoDao.add(procureInfo);
			// 成功（success）
			out.println("addsuccess");
		} catch (Exception e) {
			// 失败（failure）
			out.println("addfailure");
		}
		// 关闭输出对象
		out.close();
	}

	/**
	 * 实现显示当前页面所有数据
	 * 
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @throws IOException
	 *             IO异常
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置输出对象
		PrintWriter out = response.getWriter();
		// 接收网页数据
		// 接收行数
		int rows = Integer.parseInt(request.getParameter("rows"));
		// 接收页数
		int page = Integer.parseInt(request.getParameter("page"));
		// 调用数据层执行分页查询
		List<ProcureInfo> procureInfosList = procureInfoDao.findPage(rows, page);
		// 调用数据层查询出总记录数
		int rowsCount = procureInfoDao.getCount();
		// 将数据集合转换成JSON格式
		String jsonStr = JSONArray.fromObject(procureInfosList).toString();
		// 组合成符合json格式数据
		String json = "{\"total\":" + rowsCount + ",\"rows\":" + jsonStr + "}";
		// 输出到客户端
		out.println(json);
		// 关闭输出对象
		out.close();

	}

}
