package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.BooktypeDao;
import com.yidu.t235.dao.impl.BooktypeDaoImpl;
import com.yidu.t235.entity.Booktype;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class BooktypeServlet
 */
@WebServlet("/BooktypeServlet")
public class BooktypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //����һ�����ݲ��������
	BooktypeDao booktypeDao=new BooktypeDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BooktypeServlet() {
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
				}else if("addBooktype".equals(method)){
					addBooktype(request,response);
					
				}else if("deleteBooktype".equals(method)){
					//ɾ������ַ���
					String booktypeNoStr=request.getParameter("booktypeArray");
					deleteBooktype(request,response,booktypeNoStr);
				}else if("updateBooktype".equals(method)){
					updateBooktype(request,response);
				}
			}

	private void updateBooktype(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		        //�����������
				PrintWriter out=response.getWriter();
				
				//��ȡ���Ա�������
				int typeid=Integer.parseInt(request.getParameter("typeid"));
				String typename=request.getParameter("typename");
				String typedetailed=request.getParameter("typedetailed");
				
				//��װ�ɶ���
				Booktype booktype=new Booktype(typeid, typename, typedetailed);
				
				//�������ݲ����ʵ�������Ա��
				int rows=booktypeDao.update(booktype);
				
				//�ж����Ӳ����Ƿ�ɹ�
				if(rows>0){
					out.println("updatesuccess");
				}else{
					out.println("updatefailure");
				}
				
				//�ر��������
				out.close();
			}

	private void deleteBooktype(HttpServletRequest request, 
			HttpServletResponse response, 
			String booktypeNoStr) throws IOException{
		        //�����������
				PrintWriter out=response.getWriter();
				
				//��Ա������ַ������зָ�[�������ö��Ž������ӣ��˴�Ҳ�ö��ŷָ�]
				String[] booktypes=booktypeNoStr.split(",");
				
				//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�ɹ�
				try{
					for(String booktypeStr:booktypes){
						//����ǰ����ַ�������ת��������
						int typeid=Integer.parseInt(booktypeStr);
						//�������ݲ�������ɾ��
						booktypeDao.delete(typeid);
					}
					out.println("deletesuccess");
				}catch(Exception e){
					out.println("deletefailure");
				}
				
				//�ر��������
				out.close();
			}


	private void addBooktype(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		        //�����������
			    PrintWriter out=response.getWriter();
				
				//��ȡ���Ա�������
				String typename=request.getParameter("typename");
				String typedetailed=request.getParameter("typedetailed");
				
				//��װ�ɶ���
				Booktype booktype=new Booktype( typename, typedetailed);
				
				//�������ݲ����ʵ�������Ա��,ͨ���쳣�жϳɹ���ʧ��
				try{
					booktypeDao.add(booktype);
					
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
				List<Booktype> booktypeList=booktypeDao.findByPage(rows, page);
				//���������
				int totalRows=booktypeDao.getBooktypeCount();
				
				//ʹ��JSON�����ݽ���ת��(����ת����JSON����)
				String jsonStr=JSONArray.fromObject(booktypeList).toString();
				
				//��Ϸ���datagridҪ���ʽ�������ַ���
				String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
				//�����ҳ��
				out.println(json);
				//�ر��������
				out.close();
			}

		}