package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.ProcureDao;
import com.yidu.t235.dao.impl.ProcureDaoImpl;
import com.yidu.t235.entity.Procure;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class ProcureServlet
 */
@WebServlet("/ProcureServlet")
public class ProcureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 //����һ�����ݲ��������
	 ProcureDao procureDao=new ProcureDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcureServlet() {
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
				}else if("addProcure".equals(method)){
					addProcure(request,response);
					
				}else if("deleteProcure".equals(method)){
					//ɾ������ַ���  
					String procureNoStr=request.getParameter("procureArray");
					deleteProcure(request,response,procureNoStr);
				}else if("updateProcure".equals(method)){
				    updateProcure(request,response);
				}
			}

	private void updateProcure(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		int bookid=Integer.parseInt(request.getParameter("bookid"));
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		int salesvolume=Integer.parseInt(request.getParameter("salesvolume"));
		double bid=Double.parseDouble(request.getParameter("bid"));
		String salesperiods=request.getParameter("salesperiods");
		String operator=request.getParameter("operator");
		
		//��װ�ɶ���
		Procure procure=new Procure(bookid,ordernumber,bookname,salesvolume,bid,salesperiods,operator);
	
		//�������ݲ����ʵ�������Ա��
		int rows=procureDao.update(procure);
		
		//�ж����Ӳ����Ƿ�ɹ�
		if(rows>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
		}
		
		//�ر��������
		out.close();
	}

	private void deleteProcure(HttpServletRequest request, 
			HttpServletResponse response, String procureNoStr) throws IOException{
		        //�����������
				PrintWriter out=response.getWriter();
				
				//��Ա������ַ������зָ�[�������ö��Ž������ӣ��˴�Ҳ�ö��ŷָ�]
				String[] procures=procureNoStr.split(",");
				//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�ɹ�
				try{
					for(String procureStr:procures){
						//����ǰ����ַ�������ת��������
						int bookid=Integer.parseInt(procureStr);
						
						//�������ݲ�������ɾ��
						procureDao.delete(bookid);
						
					}
					out.println("deletesuccess");
				}catch(Exception e){
					out.println("deletefailure");
				}
				
				//�ر��������
				out.close();
			}

	private void addProcure(HttpServletRequest request, 
			HttpServletResponse response) throws IOException{
		//�����������
	    PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
	    
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		int salesvolume=Integer.parseInt(request.getParameter("salesvolume"));
		double bid=Double.parseDouble(request.getParameter("bid"));
		 
		String salesperiods=request.getParameter("salesperiods");
		String operator=request.getParameter("operator");
		
		//��װ�ɶ���
		Procure procure=new Procure(ordernumber,bookname, salesvolume,bid ,salesperiods,operator);
		
		//�������ݲ����ʵ�������Ա��,ͨ���쳣�жϳɹ���ʧ��
		try{
			procureDao.add(procure);
			
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

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws 
	IOException{
		        //����һ���������
				PrintWriter out=response.getWriter();
				
				//��ȡdatagrid�е������͵�ǰҳ��
				int rows=Integer.parseInt(request.getParameter("rows"));
				int page=Integer.parseInt(request.getParameter("page"));
				
				//�������ݲ��ҳ��ʽ����ȡ����
				List<Procure> procureList=procureDao.findByPage(rows, page);
				//���������
				int totalRows=procureDao.getProcureCount();
				
				//ʹ��JSON�����ݽ���ת��(����ת����JSON����)
				String jsonStr=JSONArray.fromObject(procureList).toString();
				
				//��Ϸ���datagridҪ���ʽ�������ַ���
				String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
				//�����ҳ��
				out.println(json);
				//�ر��������
				out.close();
			}

		}