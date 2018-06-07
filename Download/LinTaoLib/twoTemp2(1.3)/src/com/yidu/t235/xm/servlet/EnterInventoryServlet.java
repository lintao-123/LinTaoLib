package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.EnterInventoryDao;
import com.yidu.t235.xm.dao.StockCheckDao;
import com.yidu.t235.xm.dao.Impl.EnterInventoryDaoImpl;
import com.yidu.t235.xm.dao.Impl.StockCheckDaoImpl;
import com.yidu.t235.xm.entity.EnterInventory;
import com.yidu.t235.xm.entity.StockCheck;

import net.sf.json.JSONArray;

/**
 * ��������Ʋ���
 * @author �����
 * ���ڣ�2018��3��8�� ����4:19:57
 */
@WebServlet("/EnterInventoryServlet")
public class EnterInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//����һ�����ݲ��������
	private EnterInventoryDao enterInventoryDao=new EnterInventoryDaoImpl();
	//����һ���������ݲ��������
	private StockCheckDao stockCheckDao=new StockCheckDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EnterInventoryServlet() {
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
		}else if("enterInventoryAdd".equals(method)){
			//ͼ�����
			enterInventoryAdd(request,response);
		}else if("enterInventoryDelete".equals(method)){                                                                                                                                                                                                                               //��ȡ�����������ͼ�����ַ�������
			String bookNoStr=request.getParameter("cArray");
			//����ɾ������ 
			enterInventoryDelete(request,response,bookNoStr);
		}else if("enterInventoryUpdate".equals(method)){
			//�����޸ķ��� 
			enterInventoryUpdate(request,response);
		}
	}

	/**
	 * ��ͼ������޸�
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO����
	 */
	private void enterInventoryUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		int bookNo=Integer.parseInt(request.getParameter("bookNo"));
		String bookName=request.getParameter("bookName");
		int purchaseQuantity=Integer.parseInt(request.getParameter("purchaseQuantity"));
		double bookBid=Double.parseDouble(request.getParameter("bookBid"));
		String inventoryTime=request.getParameter("inventoryTime");
		String consoleOperator=request.getParameter("consoleOperator");
		
		//��װ�ɶ���
		EnterInventory enterInventory=new EnterInventory(bookNo, bookName,purchaseQuantity,bookBid
				, inventoryTime, consoleOperator);
		
		//ͨ��ͼ�����ƣ���ѯ��ý������и�ͼ���޸�ǰ������
		EnterInventory enterInventoryNumber=enterInventoryDao.findByName(bookName);
		//�����޸�ʱ��ͼ���������޸�ǰ��ͼ�������������㣬�õ��޸�ǰ���ͼ��������ֵ
		//ֵ=�޸ĺ������-ԭ������
		/*int number=enterInventory.getPurchaseQuantity()-
				enterInventoryNumber.getPurchaseQuantity();*/
		int number=purchaseQuantity-enterInventoryNumber.getPurchaseQuantity();
		//System.out.println(number);
		//�������ݲ����ʵ���޸���ͼ��
		int rows=enterInventoryDao.update(enterInventory);
		
		//���ݿ������ݲ�������󣬶Կ���и�ͼ�����������޸�
		stockCheckDao.update(bookName, number);
		
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
	 * @param empNoStr ����ַ���
	 * @throws IOException IO�쳣
	 */
	private void enterInventoryDelete(HttpServletRequest request, HttpServletResponse response, String bookNoStr) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ͼ�����ַ������зָ�[�������ö��Ž������ӣ��˴�Ҳ�ö��ŷָ�]
		String[] enterInventory=bookNoStr.split(",");
		
		//���������������
		StockCheckDao stockCheckDao=new StockCheckDaoImpl();
		
		//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�ɹ�
		try{
			for(String enterInventoryStr:enterInventory){
				//����ǰ����ַ�������ת��������
				int bookNo=Integer.parseInt(enterInventoryStr);
				// ���ݽ������ţ��ڽ���������в�ѯ��ɾ��ǰͼ������
				EnterInventory enterInventoryNumber=enterInventoryDao.findById(bookNo);
				//ͨ��get�������ɾ��ǰͼ������
				String name=enterInventoryNumber.getBookName();
				//ͨ��get�������ɾ��ǰͼ������
				int number=enterInventoryNumber.getPurchaseQuantity();
				//���ý��������ݲ�������ɾ��
				enterInventoryDao.delete(bookNo);
				//���ÿ������ݲ������п���޸�
				stockCheckDao.update(name, -number);
	
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
	private void enterInventoryAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		String bookName=request.getParameter("bookName");
		int purchaseQuantity=Integer.parseInt(request.getParameter("purchaseQuantity"));
		double bookBid=Double.parseDouble(request.getParameter("bookBid"));
		String inventoryTime=request.getParameter("inventoryTime");
		String consoleOperator=request.getParameter("consoleOperator");
		
		//��װ�ɽ�������
		EnterInventory enterInventory=new EnterInventory(bookName, purchaseQuantity, bookBid,
				inventoryTime, consoleOperator);
		
		
		//�������ݲ����ʵ�������ͼ��,ͨ���쳣�жϳɹ���ʧ��
		try{
			//��������ӵ�������
			enterInventoryDao.add(enterInventory);

			/*����ϲ�û�з����쳣���򽫵�ǰ������ӵ������*/
			
			//�����������ݲ��������
			StockCheckDao stockCheckDao=new StockCheckDaoImpl();
			//�ڿ�����ͨ��ͼ�����Ʋ�ѯ������ͼ�����
			StockCheck check=stockCheckDao.findByName(bookName);
			//����ͼ�����ƴӿ������жϣ��Ƿ���ڸ�ͼ�飺
			if(check!=null){
				//�������ͼ�飬��Ը�ͼ�������������޸�;
				stockCheckDao.update(bookName, purchaseQuantity);
			}else{
				//��������в����ڵ�ǰ�ɹ���ͼ�飬�򽫵�ǰͼ�����Ƽ��ɹ�������ӿ�����
				//A:��ͼ�����ƺ͵�ǰ�ɹ���������װ�ɴ�����
				StockCheck stockCheck=new StockCheck(bookName, purchaseQuantity);
				//B:���ɹ�ͼ����Ϊ�������
				stockCheckDao.add(stockCheck);
			}
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
		List<EnterInventory> enterInventoryList=enterInventoryDao.findByPage(rows, page);
		
		//��ѯ�ܼ�¼��
		int totalRows=enterInventoryDao.enterInventoryCount();
		
		//�����ݼ���ת����JSON��ʽ
		String jsonStr=JSONArray.fromObject(enterInventoryList).toString();
		//���ɸ�ʽ����
		String json = "{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//������ͻ���
		out.write(json);
		//�ر��������
		out.close();
	}

}
