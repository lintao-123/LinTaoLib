package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.ProcurementStatementDao;
import com.yidu.t235.xm.dao.Impl.ProcurementStatementDaoImpl;
import com.yidu.t235.xm.entity.ProcurementStatement;

import net.sf.json.JSONArray;

/**
 * �ɹ����������servlet Servlet implementation class ProcurementStatementServlet
 */
@WebServlet("/ProcurementStatementServlet")
public class ProcurementStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProcurementStatementDao procurementStatementDao = new ProcurementStatementDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcurementStatementServlet() {
		super();
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
		// ������Ӧ���ͺͱ��뼯
		response.setContentType("text/html;charset=utf-8");
		//���������ַ���
		request.setCharacterEncoding("utf-8");
		//��ȡ��������method�Ĳ���ֵ
		String method = request.getParameter("method");
		//���ݲ���ֵ�ж��û�����
		if ("findAll".equals(method)) {
			//��ʾ��ǰҳ��������
			findAll(request, response);
		} else if ("addProcurement".equals(method)) {
			//������ӷ���
			addProcurement(request, response);
		} else if ("deleteProcurement".equals(method)) {
			//��ȡ���������еı���ַ�����
			String procurementStr = request.getParameter("procurementArray");
			//����ɾ������
			deleteProcurement(request, response, procurementStr);
		} else if ("updateProcurement".equals(method)) {
			//�����޸ķ���
			updateProcurement(request, response);
		}
	}

	/**
	 * �޸Ĳɹ���������
	 * 
	 * @param request
	 *            �������
	 * @param response
	 *            ��Ӧ����
	 * @throws IOException
	 *             IO�쳣
	 */
	private void updateProcurement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// �����������
		PrintWriter out = response.getWriter();

		/*������ҳ����*/
		//���ձ��
		int tempId=Integer.parseInt(request.getParameter("tempId"));
		//���ղɹ�������
		String procurementNo=request.getParameter("procurementNo");
		//����ͼ������
		String  bookName=request.getParameter("bookName");
		//����ͼ������
		String bookType=request.getParameter("bookType");
		//����ͼ�����
		double bookBid=Double.parseDouble(request.getParameter("bookBid")) ;
		//���չ�Ӧ��
		String supplier=request.getParameter("supplier");
		//���ղɹ�ʱ��
		String libraryTime=request.getParameter("libraryTime");
		//���ղɹ�����
		int purchaseQuantity=Integer.parseInt(request.getParameter("purchaseQuantity"));
		//���ղ���Ա
		String playEmp=request.getParameter("playEmp");
		//�Ѳɹ����鶩�������װ
		ProcurementStatement procurementStatemen=new ProcurementStatement(tempId, procurementNo, bookName, bookType, bookBid, supplier, libraryTime, purchaseQuantity, playEmp);

		//�������ݲ����ʵ���޸Ķ������鲢�ѷ���ֵ��������
		int rows = procurementStatementDao.update(procurementStatemen);
		//�ж��޸��Ƿ�ɹ�
		if (rows > 0) {
			//�ɹ���success��
			out.println("updatesuccess");
		} else {
			//ʧ��(failure)
			out.println("updatefailure");
		}
		//�ر��������	
		out.close();
	}

	/**
	 * ɾ���ɹ���������
	 * 
	 * @param request
	 *            �������
	 * @param response
	 *            ��Ӧ����
	 * @param procurementStr
	 *            �ַ���
	 * @throws IOException
	 *             IO�쳣
	 */
	private void deleteProcurement(HttpServletRequest request, HttpServletResponse response, String procurementStr)
			throws IOException {
		// �����������
		PrintWriter out = response.getWriter();
		//�Ա���ַ������зָ[�������ö��Ž������ӣ��˴�Ҳ�ö��Ž��зָ�]
		String[] procurements = procurementStr.split(",");
		//���ڿ���������ɾ����ʹ���쳣��ѭ�����ж��Ƿ�ɹ�
		try {
			for (String procurementNo : procurements) {
				//����ǰ����ַ���ת��������
				int procurement = Integer.parseInt(procurementNo);
				//�������ݲ����ʵ��ɾ��
				procurementStatementDao.delete(procurement);
			}
			//�ɹ���success��
			out.println("deletesuccess");
		} catch (Exception e) {
			//ʧ�ܣ�failure��
			out.println("deletefailure");
		}
		//�ر��������
		out.close();
	}

	/**
	 * ��Ӷ�������
	 * 
	 * @param request
	 *            �������
	 * @param response
	 *            ��Ӧ����
	 * @throws IOException
	 *             IO�쳣
	 */
	private void addProcurement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// �����������
		PrintWriter out = response.getWriter();

		/*������ҳ����*/
		//���ղɹ��������
		String procurementNo = request.getParameter("procurementNo");
		//����ͼ������
		String bookName = request.getParameter("bookName");
		//����ͼ������
		String bookType = request.getParameter("bookType");
		//����ͼ�����
		double bookBid = Double.parseDouble(request.getParameter("bookBid"));
		//���չ�Ӧ��
		String supplier = request.getParameter("supplier");
		//���ղɹ�ʱ��
		String libraryTime = request.getParameter("libraryTime");
		//���ղɹ�����
		int purchaseQuantity = Integer.parseInt(request.getParameter("purchaseQuantity"));
		//���ղ���Ա
		String playEmp = request.getParameter("playEmp");
		//�Ѳɹ����鶩��������з�װ
		ProcurementStatement procurementStatemen = new ProcurementStatement(procurementNo,
				bookName, bookType, bookBid, supplier, libraryTime, purchaseQuantity, playEmp);

		//ͨ���쳣�ж��Ƿ�ɹ�

		try {
			//�������ݲ�������
			procurementStatementDao.add(procurementStatemen);
			//�ɹ���success��
			out.println("addsuccess");
		} catch (Exception e) {
			//ʧ�ܣ�failure��
			out.println("addfailure");
		}
		//�ر��������
		out.close();
	}

	/**
	 * ʵ����ʾҳ����������
	 * 
	 * @param request
	 *            �������
	 * @param response
	 *            ��Ӧ����
	 * @throws IOException
	 *             IO�쳣
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// �����������
		 PrintWriter out=response.getWriter();

		/* ������ҳ����*/	
		//��������
		 int rows=Integer.parseInt(request.getParameter("rows"));
		//����ҳ��
		 int page=Integer.parseInt(request.getParameter("page"));

		//�������ݲ�ִ�з�ҳ��ѯ
		
		List<ProcurementStatement> procurementStatementsList=
				procurementStatementDao.findPage(rows, page) ;

		//�������ݲ��ѯ���ܼ�¼��
		int totalRows=procurementStatementDao.getCount();

		//�����ݼ���ת����JSON��ʽ
		String jsonStr=JSONArray.fromObject(procurementStatementsList).toString();
		// ��ϳɷ���datagrid��ʽ�������ַ���
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		// ���json����
		out.println(json);
		//�ر��������
		out.close();
	}

}
