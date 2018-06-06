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
 * ������Ʋ���	  
 * @author �����
 * ���ڣ�2018��3��8�� ����4:34:46
 */
@WebServlet("/StockCheckServlet")
public class StockCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//����һ�����ݲ��������
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
		//������Ӧ��Ϣ���ͺ��ַ���
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//���������ַ���
		request.setCharacterEncoding("utf-8");
		
		//��ȡ���������method����ֵ
		String method=request.getParameter("method");
		
		//���ݲ���ֵ�ж��û�����
		if("findAll".equals(method)){
			//�����ʾ��ǰҳ��������
			findAll(request,response);
		} 
	}
	/**
	 * ʵ����ʾ��ǰ����������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException  �쳣
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ��datagrid������ݹ�����ҳ��¼������ǰҳ��
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//ִ�з�ҳ��ѯ
		List<StockCheck> stockCheckList=stockCheckDao.findByPage(rows, page);
		
		//��ѯ�ܼ�¼��
		int totalRows=stockCheckDao.stockCheckCount();
		
		//�����ݼ���ת����JSON��ʽ
		String jsonStr=JSONArray.fromObject(stockCheckList).toString();
		//���ɸ�ʽ����
		String json = "{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		
		//������ͻ���
		out.write(json);
		//�ر��������
		out.close();
	}

}
