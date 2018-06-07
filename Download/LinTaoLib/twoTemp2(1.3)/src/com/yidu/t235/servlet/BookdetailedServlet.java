package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.BookdetailedDao;
import com.yidu.t235.dao.impl.BookdetailedDaoImpl;
import com.yidu.t235.entity.Bookdetailed;

import net.sf.json.JSONArray;

/**
 * ���۶�������Ʋ���
 * @author Ф�Ʒ�
 * ���ڣ� 2018-3-09
 */
/**
 * Servlet implementation class BookdetailedServlet
 */
@WebServlet("/BookdetailedServlet")
public class BookdetailedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //����һ�����ݲ��������
	   BookdetailedDao bookdetailedDao=new BookdetailedDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookdetailedServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		//����
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		//����·���������ʽ���ò�������
		String method=request.getParameter("method");
		//�жϲ�����ʽ
		if("findAll".equals(method)){
			findAll(request,response);
		}else if("addBookdetailed".equals(method)){
			addBookdetailed(request,response);
			
		}else if("deleteBookdetailed".equals(method)){
			//ɾ������ַ���  
			String bookdetailedNoStr=request.getParameter("bookdetailedArray");
			deleteBookdetailed(request,response,bookdetailedNoStr);
		}else if("updateBookdetailed".equals(method)){
			updateBookdetailed(request,response);
		}
	}
	/**
     * ��ͼ������޸�
     * @param request �������
     * @param response ��Ӧ����
     * @throws IOException IO����
     */
	private void updateBookdetailed(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		int bookno=Integer.parseInt(request.getParameter("bookno"));
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		String bookprice=request.getParameter("bookprice");
		String salesman=request.getParameter("salesman");
		
		//��װ�ɶ���
		Bookdetailed bookdetailed=new Bookdetailed(bookno, ordernumber, bookname,bookprice,salesman);
		
		//�������ݲ����ʵ�������Ա��
		int rows=bookdetailedDao.update(bookdetailed);
		
		//�ж����Ӳ����Ƿ�ɹ�
		if(rows>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
		}
		
		//�ر��������
		out.close();
	}
	/**
     * ɾ��ͼ��
     * @param request �������
     * @param response ��Ӧ����
     * @param bookdetailedNoStr ����ַ���
     * @throws IOException IO�쳣
     */
	private void deleteBookdetailed(HttpServletRequest request, 
			HttpServletResponse response,
			String bookdetailedNoStr) throws IOException{
		//�����������
		PrintWriter out=response.getWriter();
		
		//��Ա������ַ������зָ�[�������ö��Ž������ӣ��˴�Ҳ�ö��ŷָ�]
		String[] bookdetaileds=bookdetailedNoStr.split(",");
		//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�ɹ�
		try{
			for(String bookdetailedStr:bookdetaileds){
				//����ǰ����ַ�������ת��������
				int bookno=Integer.parseInt(bookdetailedStr);
				
				//�������ݲ�������ɾ��
				bookdetailedDao.delete(bookno);
				
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//�ر��������
		out.close();
	}

	/**
     * ʵ����ͼ�����
     * @param request �������
     * @param response ��Ӧ����
     * @throws IOException IO�쳣
     */
	private void addBookdetailed(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//�����������
	    PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
	    
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		String bookprice=request.getParameter("bookprice");
		String salesman=request.getParameter("salesman");
		
		//��װ�ɶ���
		Bookdetailed bookdetailed=new Bookdetailed(ordernumber,bookname, bookprice,salesman);
		
		//�������ݲ����ʵ�������Ա��,ͨ���쳣�жϳɹ���ʧ��
		try{
			bookdetailedDao.add(bookdetailed);
			
			out.println("addsuccess");
		}catch(Exception e){
			
			out.println("addfailure");
		}

		//�ر��������
		out.close();
	}
	/**
	 * ��ʾ��ǰҳ��������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException �쳣
	 */
	private void findAll(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		//����һ���������
		PrintWriter out=response.getWriter();
		
		//��ȡdatagrid�е������͵�ǰҳ��
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ��ҳ��ʽ����ȡ����
		List<Bookdetailed> bookdetailedList=bookdetailedDao.findByPage(rows, page);
		//���������
		int totalRows=bookdetailedDao.getBookdetailedCount();
		
		//ʹ��JSON�����ݽ���ת��(����ת����JSON����)
		String jsonStr=JSONArray.fromObject(bookdetailedList).toString();
		
		//��Ϸ���datagridҪ���ʽ�������ַ���
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//�����ҳ��
		out.println(json);
		//�ر��������
		out.close();
	}

}