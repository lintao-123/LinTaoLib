package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.BookoperationDao;
import com.yidu.t235.dao.impl.BookoperationDaoImpl;
import com.yidu.t235.entity.Bookoperation;
import com.yidu.t235.entity.Booktype;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class BookoperationServlet
 */
@WebServlet("/BookoperationServlet")
public class BookoperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //����һ�����ݲ��������
	   BookoperationDao bookoperationDao=new BookoperationDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookoperationServlet() {
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
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, 
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
		}else if("addbookoperation".equals(method)){
			addBookoperation(request,response);
			
		}else if("deleteBookoperation".equals(method)){
			//ɾ������ַ���
			String bookoperationNoStr=request.getParameter("bookoperationArray");
			deleteBookoperation(request,response,bookoperationNoStr);
		}else if("updateBookoperation".equals(method)){
			
			updateBookoperation(request,response);
			
		}
	}

	private void updateBookoperation(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		int bookno=Integer.parseInt(request.getParameter("bookno"));
		String bookname=request.getParameter("bookname");
		String booktype=request.getParameter("booktype");
		String bookauthor=request.getParameter("bookauthor");
		String supplier=request.getParameter("supplier");
		String bookprice=request.getParameter("bookprice");
		String bookunit=request.getParameter("bookunit");
		//��װ�ɶ���
		Bookoperation bookoperation=new Bookoperation(bookno, bookname, booktype,bookauthor,supplier,bookprice,bookunit);
		
		//�������ݲ����ʵ�������Ա��
		int rows=bookoperationDao.update(bookoperation);
		
		//�ж����Ӳ����Ƿ�ɹ�
		if(rows>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
		}
		
		//�ر��������
		out.close();
	}

	private void deleteBookoperation(HttpServletRequest request, 
			HttpServletResponse response,
			String bookoperationNoStr) throws IOException{
		//�����������
		PrintWriter out=response.getWriter();
		
		//��Ա������ַ������зָ�[�������ö��Ž������ӣ��˴�Ҳ�ö��ŷָ�]
		String[] bookoperations=bookoperationNoStr.split(",");
		
		//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�ɹ�
		try{
			    for(String bookoperationStr:bookoperations){
				//����ǰ����ַ�������ת��������
				int bookno=Integer.parseInt(bookoperationStr);
				//�������ݲ�������ɾ��
				bookoperationDao.delete(bookno);
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//�ر��������
		out.close();
	}

	private void addBookoperation(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		 //�����������
	    PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		String bookname=request.getParameter("bookname");
		String booktype=request.getParameter("booktype");
		String bookauthor=request.getParameter("bookauthor");
		String supplier=request.getParameter("supplier");
		String bookprice=request.getParameter("bookprice");
		String bookunit=request.getParameter("bookunit");
		//��װ�ɶ���
		Bookoperation bookoperation=new Bookoperation( bookname, booktype,bookauthor,supplier,bookprice,bookunit);
		
		//�������ݲ����ʵ�������Ա��,ͨ���쳣�жϳɹ���ʧ��
		try{
			bookoperationDao.add(bookoperation);
			
			out.println("addsuccess");
		}catch(Exception e){
			
			out.println("addfailure");
		}

		//�ر��������
		out.close();
	}

	/**
	 * ��ʾ��ǰҳ��������
	 * @param request
	 * @param response
	 * @throws IOException 
	 */

	private void findAll(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//����һ���������
		PrintWriter out=response.getWriter();
		
		//��ȡdatagrid�е������͵�ǰҳ��
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ��ҳ��ʽ����ȡ����
		List<Bookoperation> bookoperationList=bookoperationDao.findByPage(rows, page);
		//���������
		int totalRows=bookoperationDao.getBookoperationCount();
		
		//ʹ��JSON�����ݽ���ת��(����ת����JSON����)
		String jsonStr=JSONArray.fromObject(bookoperationList).toString();
		
		//��Ϸ���datagridҪ���ʽ�������ַ���
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//�����ҳ��
		out.println(json);
		//�ر��������
		out.close();
	}

}