package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.ProcureInfoDao;
import com.yidu.t235.xm.dao.Impl.ProcureInfoDaoImpl;
import com.yidu.t235.xm.entity.ProcureInfo;

import net.sf.json.JSONArray;

/**
 * �ɹ�������
 * servlet Servlet implementation class ProcureInfoServlet
 */
@WebServlet("/ProcureInfoServlet")
public class ProcureInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProcureInfoDao procureInfoDao = new ProcureInfoDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcureInfoServlet() {
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
		// ���� ��Ӧ���ͼ��ַ���
		response.setContentType("text/html;charset=utf-8");
		// ���������ַ���
		request.setCharacterEncoding("utf-8");
		// ��ȡ��������method�Ĳ���ֵ
		String method = request.getParameter("method");
		// ���ݲ���ֵ�ж��û�����
		if ("findAll".equals(method)) {
			// ��ʾ��ǰҳ��������
			findAll(request, response);
		} else if ("addProcureInfo".equals(method)) {
			// ������ӷ���
			addProcureInfo(request, response);
		} else if ("deleteProcureInfo".equals(method)) {
			// ��ȡ���������еı���ַ�����
			String procureInfoStr = request.getParameter("procurementArray");
			// ����ɾ������
			deleteProcureInfo(request, response, procureInfoStr);
		} else if ("updateProcureInfo".equals(method)) {
			// �����޸ķ���
			updateProcureInfo(request, response);
		}

	}

	/**
	 * �Բɹ����������޸�
	 * 
	 * @param request
	 *            �������
	 * @param response
	 *            ��Ӧ����
	 * @throws IOException
	 *             IO�쳣
	 */
	private void updateProcureInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// �����������
		PrintWriter out = response.getWriter();

		/* ������ҳ���� */
		// ���ձ��
		int tempId = Integer.parseInt(request.getParameter("tempId"));
		// ���ն�����
		String procurementNo = request.getParameter("procurementNo");
		// ���չ�Ӧ��
		String supplier = request.getParameter("supplier");
		// ���ղɹ�ʱ��
		String libraryTime = request.getParameter("libraryTime");

		// �Ѳɹ����������װ
		ProcureInfo procureInfo = new ProcureInfo(tempId, procurementNo, supplier, libraryTime);
		// �������ݲ����ʵ���޸Ķ������ѷ���ֵ��������
		int rows = procureInfoDao.update(procureInfo);
		// �ж��޸��Ƿ�ɹ�
		if (rows > 0) {
			// �ɹ���success��
			out.println("updatesuccess");
		} else {
			// ʧ��(failure)
			out.println("updatefailure");
		}
		// �ر��������
		out.close();
	}

	/**
	 * ɾ���ɹ�����
	 * 
	 * @param request
	 *            �������
	 * @param response
	 *            ��Ӧ����
	 * @param procureInfoStr
	 *            �ַ���
	 * @throws IOException
	 *             IO�쳣
	 */
	private void deleteProcureInfo(HttpServletRequest request, HttpServletResponse response, String procureInfoStr)
			throws IOException {
		// �����������
		PrintWriter out = response.getWriter();
		// �Ա���ַ������зָ[�������ö��Ž������ӣ��˴�Ҳ�ö��Ž��зָ�]
		String[] procureInfos = procureInfoStr.split(",");
		// ���ڿ���������ɾ����ʹ���쳣��ѭ�����ж��Ƿ�ɹ�
		try {
			for (String procureInfo : procureInfos) {
				// ����ǰ����ַ���ת��������
				int tempId = Integer.parseInt(procureInfo);
				// �������ݲ����ʵ��ɾ��
				procureInfoDao.delete(tempId);
			}
			// �ɹ���success��
			out.println("deletesuccess");
		} catch (Exception e) {
			// ʧ�ܣ�failure��
			out.println("deletefailure");
		}
		// �ر��������
		out.close();

	}

	/**
	 * ��Ӳɹ�����
	 * 
	 * @param request
	 *            �������
	 * @param response
	 *            ��Ӧ����
	 * @throws IOException
	 *             IO�쳣
	 */
	private void addProcureInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// �����������
		PrintWriter out = response.getWriter();

		/* ������ҳ���� */
		// ���ղɹ��������
		String procurementNo = request.getParameter("procurementNo");
		// ���չ�Ӧ��
		String supplier = request.getParameter("supplier");
		// ���ղɹ�ʱ��
		String libraryTime = request.getParameter("libraryTime");
		// �Ѳɹ�����������з�װ
		ProcureInfo procureInfo = new ProcureInfo(procurementNo, supplier, libraryTime);
		// ͨ���쳣�ж��Ƿ�ɹ�
		try {
			// �������ݲ�������
			procureInfoDao.add(procureInfo);
			// �ɹ���success��
			out.println("addsuccess");
		} catch (Exception e) {
			// ʧ�ܣ�failure��
			out.println("addfailure");
		}
		// �ر��������
		out.close();
	}

	/**
	 * ʵ����ʾ��ǰҳ����������
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
		PrintWriter out = response.getWriter();
		// ������ҳ����
		// ��������
		int rows = Integer.parseInt(request.getParameter("rows"));
		// ����ҳ��
		int page = Integer.parseInt(request.getParameter("page"));
		// �������ݲ�ִ�з�ҳ��ѯ
		List<ProcureInfo> procureInfosList = procureInfoDao.findPage(rows, page);
		// �������ݲ��ѯ���ܼ�¼��
		int rowsCount = procureInfoDao.getCount();
		// �����ݼ���ת����JSON��ʽ
		String jsonStr = JSONArray.fromObject(procureInfosList).toString();
		// ��ϳɷ���json��ʽ����
		String json = "{\"total\":" + rowsCount + ",\"rows\":" + jsonStr + "}";
		// ������ͻ���
		out.println(json);
		// �ر��������
		out.close();

	}

}
