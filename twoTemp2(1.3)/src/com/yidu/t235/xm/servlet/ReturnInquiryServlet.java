package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.ReturnInquiryDao;
import com.yidu.t235.xm.dao.StockCheckDao;
import com.yidu.t235.xm.dao.Impl.ReturnInquiryDaoImpl;
import com.yidu.t235.xm.dao.Impl.StockCheckDaoImpl;
import com.yidu.t235.xm.entity.ReturnInquiry;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ReturnInquiryServlet
 */
@WebServlet("/ReturnInquiryServlet")
public class ReturnInquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		//�����˻����������
       private ReturnInquiryDao returnInquiryDao=new ReturnInquiryDaoImpl();
       //�����������
       private StockCheckDao stockCheckDao=new StockCheckDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnInquiryServlet() {
  
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������Ӧ���ͺ��ַ�������
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		//������ҳmethod���ݵ�����
		String method=request.getParameter("method");
		
		//�жϵ��õķ���
		if("findAll".equals(method)){
			findAll(request,response);
		}else if("addReturnInquiry".equals(method)){
			addReturnInquiry(request,response);
		}else if("deleteReturnInquiry".equals(method)){
			String orderNumberStr =request.getParameter("returnInquiryArray");
			deleteReturnInquiry(request,response,orderNumberStr);
		}else if("updateReturnInquiry".equals(method)){
			updateReturnInquiry(request,response);
		}
	}

	private void updateReturnInquiry(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//������ҳ����
		int tempId=Integer.parseInt(request.getParameter("tempId"));
		String orderNumber= request.getParameter("orderNumber") ;
		String bookName=request.getParameter("bookName");
		 
		double bookPrice=Double.parseDouble(request.getParameter("bookPrice"));
		int salesVolume=Integer.parseInt(request.getParameter("salesVolume"));
		String salesPeriods=request.getParameter("salesPeriods");
		String consoleOperator=request.getParameter("consoleOperator");
		
		ReturnInquiry returnInquiry=new ReturnInquiry(tempId,orderNumber,bookName ,bookPrice,salesVolume,
				salesPeriods,consoleOperator);
		/*ͨ������Id�����õ������˻���������޸�ǰ����,��������޸�ǰ���޸ĺ�Ĳ�ֵ*/
		//���������˻����ݲ���в�Id
		ReturnInquiry returnInquiryNumber=returnInquiryDao.findById(tempId);
		//��ֵ=�޸ĺ�-�޸�ǰ
		int number=salesVolume-returnInquiryNumber.getSalesVolume();
		//���������˻����ݲ�����޸�
		int rows=returnInquiryDao.update(returnInquiry);
		//���ÿ������ݲ�����޸�
		stockCheckDao.update(bookName, number);
		if(rows>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
			
		}
		out.close();
		
	}

	private void deleteReturnInquiry(HttpServletRequest request, HttpServletResponse response, String orderNumberStr) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		//���ַ������зָ�
		String [] orderNumbers=orderNumberStr.split(",");
		 try {
			for(String orderNumber:orderNumbers){
				int orderNumberNO=Integer.parseInt(orderNumber );
				//����Id��ѯ������������
				ReturnInquiry returnInquiryNumber=returnInquiryDao.findById(orderNumberNO);
				//ʹ��ʵ����get�����������
				String bookName=returnInquiryNumber.getBookName();
				//ʹ��ʵ����get�����������
				int number=returnInquiryNumber.getSalesVolume();
				//���������˻������ݲ����ɾ��
				returnInquiryDao.delete(orderNumberNO);
				//���ÿ������ݲ�����޸�
				stockCheckDao.update(bookName, -number);
			}
			out.println("deletesuccess");
		} catch (Exception e) {
			out.println("deletefailure");
		}
		
		out.close();
	}

	private void addReturnInquiry(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		//������ҳ����
		String orderNumber= request.getParameter("orderNumber") ;
		String bookName=request.getParameter("bookName");
		 
		double bookPrice=Double.parseDouble(request.getParameter("bookPrice"));
		int salesVolume=Integer.parseInt(request.getParameter("salesVolume"));
		String salesPeriods=request.getParameter("salesPeriods");
		String consoleOperator=request.getParameter("consoleOperator");
		
		ReturnInquiry returnInquiry=new ReturnInquiry(orderNumber,bookName ,bookPrice,salesVolume,
				salesPeriods,consoleOperator);
		try {
			//���������˻����ݲ�������
			returnInquiryDao.add(returnInquiry);
			//���������˻����ݲ�����޸�
			stockCheckDao.update(bookName,salesVolume);
			out.println("addsuccess");
		} catch (Exception e) {
			out.println("addfailure");
		}
	}
	
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//������ҳ����
		int rows=Integer.parseInt(request.getParameter("rows"));
		int page=Integer.parseInt(request.getParameter("page"));
		//�������ݲ�
		List<ReturnInquiry> returnInquiriesList=returnInquiryDao.findPage(rows, page);
		
		//��ȡ������
		int rowsCount=returnInquiryDao.getCount();
		//ʹ��json�����ݽ���ת��
		String jsonStr=JSONArray.fromObject(returnInquiriesList).toString();
		//��ϳɷ���json��ʽ�ַ���
		String json="{\"total\":"+rowsCount+",\"rows\":"+jsonStr+"}";		
		out.println(json);
		out.close();
			
	}

}
