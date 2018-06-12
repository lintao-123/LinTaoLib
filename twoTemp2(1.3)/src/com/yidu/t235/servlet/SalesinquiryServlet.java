package com.yidu.t235.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.dao.SalesinquiryDao;
import com.yidu.t235.dao.impl.SalesinquiryDaoImpl;
import com.yidu.t235.entity.Salesinquiry;

import net.sf.json.JSONArray;

/**
 * ���۶����������Ʋ���
 * @author Ф�Ʒ�
 * ���ڣ� 2018-3-09
 */
/**
 * Servlet implementation class SalesinquiryServlet
 */
@WebServlet("/SalesinquiryServlet")
public class SalesinquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //����һ�����ݲ��������
	SalesinquiryDao salesinquiryDao=new SalesinquiryDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesinquiryServlet() {
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
		}else if("addSalesinquiry".equals(method)){
			addSalesinquiry(request,response);
			
		}else if("deleteSalesinquiry".equals(method)){
			//ɾ������ַ���
			String salesinquiryNoStr=request.getParameter("salesinquiryArray");
			deleteSalesinquiry(request,response,salesinquiryNoStr);
		}else if("updateSalesinquiry".equals(method)){
			updateSalesinquiry(request,response);
		}
	}
	 /**
     * ��ͼ������޸�
     * @param request �������
     * @param response ��Ӧ����
     * @throws IOException IO�쳣
     */
	private void updateSalesinquiry(HttpServletRequest request, HttpServletResponse response) throws 
	IOException{
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		int ordernumber=Integer.parseInt(request.getParameter("ordernumber"));
		String ordernumberid=request.getParameter("ordernumberid");
		String bookname=request.getParameter("bookname");
		String booktype=request.getParameter("booktype");
		double bookprice=Double.parseDouble(request.getParameter("bookprice"));
		int salesvolume=Integer.parseInt(request.getParameter("salesvolume"));
		//��װ�ɶ���
		Salesinquiry salesinquiry=new Salesinquiry(ordernumber, ordernumberid, bookname,booktype,bookprice,salesvolume );
		
		//�������ݲ����ʵ�������Ա��
		int rows=salesinquiryDao.update(salesinquiry);
		
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
     * @param salesinquiryNoStr ����ַ���
     * @throws IOException IO�쳣
     */
	private void deleteSalesinquiry(HttpServletRequest request, HttpServletResponse response,
			String salesinquiryNoStr) throws IOException{
		 //�����������
		 PrintWriter out=response.getWriter();
		
		//��Ա������ַ������зָ�[�������ö��Ž������ӣ��˴�Ҳ�ö��ŷָ�]
		String[] salesinquirys=salesinquiryNoStr.split(",");
		
		//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�ɹ�
		try{
			for(String salesinquiryStr:salesinquirys){
				//����ǰ����ַ�������ת��������
				int ordernumber=Integer.parseInt(salesinquiryStr);
				//�������ݲ�������ɾ��
				salesinquiryDao.delete(ordernumber);
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//�ر��������
		out.close();
	}
	 /**
     * ʵ��ͼ�����
     * @param request �������
     * @param response ��Ӧ����
     * @throws IOException IO�쳣
     */
	private void addSalesinquiry(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//�����������
	    PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		String ordernumberid=request.getParameter("ordernumberid");
		String bookname=request.getParameter("bookname");
		String booktype=request.getParameter("booktype");
		double bookprice=Double.parseDouble(request.getParameter("bookprice"));
		int salesvolume=Integer.parseInt(request.getParameter("salesvolume"));
	
		//��װ�ɶ���
		Salesinquiry salesinquiry=new Salesinquiry( ordernumberid, bookname,booktype,bookprice,salesvolume );
		salesinquiryDao.add(salesinquiry);
		//�������ݲ����ʵ�������Ա��,ͨ���쳣�жϳɹ���ʧ��
		try{
		
			
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


	private void findAll(HttpServletRequest request, HttpServletResponse response) throws 
	IOException{
		 //����һ���������
		PrintWriter out=response.getWriter();
		
		//��ȡdatagrid�е������͵�ǰҳ��
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		
		//�������ݲ��ҳ��ʽ����ȡ����
		List<Salesinquiry> salesinquiryList=salesinquiryDao.findByPage(rows, page);
		//���������
		int totalRows=salesinquiryDao.getSalesinquiryCount();
		
		//ʹ��JSON�����ݽ���ת��(����ת����JSON����)
		String jsonStr=JSONArray.fromObject(salesinquiryList).toString();
		
		//��Ϸ���datagridҪ���ʽ�������ַ���
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//�����ҳ��
		out.println(json);
		//�ر��������
		out.close();
	}

}