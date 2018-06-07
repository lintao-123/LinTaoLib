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
 * �����̱���Ʋ���	  
 * @author �����
 * ���ڣ�2018��3��8�� ����4:34:46
 */
@WebServlet("/SupplierInformationServlet")
public class SupplierInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//����һ�����ݲ��������
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
		}else if("supplierInformationAdd".equals(method)){
			//��Ӧ�����
			supplierInformationAdd(request,response);
		}else if("supplierInformationDelete".equals(method)){
			//��ȡ����������й�Ӧ�̱���ַ�������
			String supplierNumberStr=request.getParameter("cArray");
			//����ɾ������ 
			supplierInformationDelete(request,response,supplierNumberStr);
		}else if("supplierInformationUpdate".equals(method)){
			//�����޸ķ��� 
			supplierInformationUpdate(request,response);
		}
	}

	/**
	 * �Թ�Ӧ�̽����޸�
	 * @param request
	 * @param response
	 * @throws IOException IO����
	 */
	private void supplierInformationUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		int supplierNumber=Integer.parseInt(request.getParameter("supplierNumber"));
		String supplier=request.getParameter("supplier");
		String telephoneNumbers=request.getParameter("telephoneNumbers");
		String address=request.getParameter("address");
		String contacts=request.getParameter("contacts");
		
		//��װ�ɶ���
		SupplierInformation supplierInformation=new SupplierInformation(supplierNumber, supplier, telephoneNumbers, address, contacts);
		
		//�������ݲ����ʵ�������ͼ��
		int rows=supplierInformationDao.update(supplierInformation);
		
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
	 * ɾ����Ӧ��
	 * @param request �������
	 * @param response ��Ӧ����
	 * @param empNoStr ����ַ���
	 * @throws IOException IO�쳣
	 */
	private void supplierInformationDelete(HttpServletRequest request, HttpServletResponse response, String supplierNumberStr) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ͼ�����ַ������зָ�[�������ö��Ž������ӣ��˴�Ҳ�ö��ŷָ�]
		String[] supplierInformation=supplierNumberStr.split(",");
		
		System.out.println("ɾ����ͼ����:"+supplierInformation);
		//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�ɹ�
		try{
			for(String supplierInformationStr:supplierInformation){
				//����ǰ����ַ�������ת��������
				int supplierNumber=Integer.parseInt(supplierInformationStr);
				//�������ݲ�������ɾ��
				supplierInformationDao.delete(supplierNumber);
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//�ر��������
		out.close();
	}

	/**
	 * ʵ���¹�Ӧ�����
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void supplierInformationAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		String supplier=request.getParameter("supplier");
		String telephoneNumbers=request.getParameter("telephoneNumbers");
		String address=request.getParameter("address");
		String contacts=request.getParameter("contacts");
		
		//��װ�ɶ���
		SupplierInformation supplierInformation=new SupplierInformation(supplier, telephoneNumbers, address, contacts);
	
		
		//�������ݲ����ʵ������¹�Ӧ��,ͨ���쳣�жϳɹ���ʧ��
		try{
			supplierInformationDao.add(supplierInformation);
			out.println("addsuccess");
		}catch(Exception e){
			out.println("addfailure");
		}

		//�ر��������
		out.close();
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
		List<SupplierInformation> enterInventoryList=supplierInformationDao.findByPage(rows, page);
		
		//��ѯ�ܼ�¼��
		int totalRows=supplierInformationDao.supplierInformationCount();
		
		//�����ݼ���ת����JSON��ʽ
		String jsonStr=JSONArray.fromObject(enterInventoryList).toString();
		//���ɸ�ʽ����
		String json = "{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		
		//���������̨��������
		System.out.println(json);
		//������ͻ���
		out.write(json);
		//�ر��������
		out.close();
	}

}
