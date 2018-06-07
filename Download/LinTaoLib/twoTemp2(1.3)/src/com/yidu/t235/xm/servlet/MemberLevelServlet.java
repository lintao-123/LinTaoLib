package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.MemberLevelDao;
import com.yidu.t235.xm.dao.Impl.MemberLevelDaoImpl;
import com.yidu.t235.xm.entity.MemberLevel;

import net.sf.json.JSONArray;

/**
 * ��Ա�ȼ�����Ʋ���	  
 * @author �����
 * ���ڣ�2018��3��8�� ����4:34:46
 */		  
@WebServlet("/MemberLevelServlet")
public class MemberLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//����һ�����ݲ��������
	private MemberLevelDao memberLevelDao=new MemberLevelDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLevelServlet() {
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
		System.out.println(method);
		
		//���ݲ���ֵ�ж��û�����
		if("findAll".equals(method)){
			//�����ʾ��ǰҳ��������
			findAll(request,response);
		}else if("memberLevelAdd".equals(method)){
			//��Ա���
			memberLevelAdd(request,response);
		}else if("memberLevelDelete".equals(method)){
			//��ȡ����������еȼ�����ַ�������
			String levelNumberStr=request.getParameter("cArray");
			//����ɾ������ 
			memberLevelDelete(request,response,levelNumberStr);
		}else if("memberLevelUpdate".equals(method)){
			
			//�����޸ķ��� 
			memberLevelUpdate(request,response);
		}
	}

	/**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
	 * ����Ϣ�����޸�
	 * @param request ����
	 * @param response ��Ӧ
	 * @throws IOException IO����
	 */
	private void memberLevelUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		int levelNumber=Integer.parseInt(request.getParameter("levelNumber"));
		String levelName=request.getParameter("levelName");
		double memberDiscount=Double.parseDouble(request.getParameter("memberDiscount"));
		String upgradeIntegral=request.getParameter("upgradeIntegral");
		
		//��װ�ɶ���
		MemberLevel memberLevel=new MemberLevel(levelNumber, levelName, memberDiscount, upgradeIntegral);
		
		//�������ݲ����ʵ������µȼ�
		int rows=memberLevelDao.update(memberLevel);
				
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
	 * ɾ���ȼ���Ϣ
	 * @param request �������
	 * @param response ��Ӧ����
	 * @param empNoStr ����ַ���
	 * @throws IOException IO�쳣
	 */
	private void memberLevelDelete(HttpServletRequest request, HttpServletResponse response, String levelNumberStr) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//�Ի�Ա����ַ������зָ�[�������ö��Ž������ӣ��˴�Ҳ�ö��ŷָ�]
		String[] memberLevel=levelNumberStr.split(",");
		
		System.out.println("ɾ���ĵȼ����:"+memberLevel);
		//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�ɹ�
		try{
			for(String memberLevelStr:memberLevel){
				//����ǰ����ַ�������ת��������
				int levelNumber=Integer.parseInt(memberLevelStr);
				//�������ݲ�������ɾ��
				memberLevelDao.delete(levelNumber);
				
				
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//�ر��������
		out.close();
	}

	/**
	 * ʵ���µȼ���Ϣ���
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void memberLevelAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		String levelName=request.getParameter("levelName");
		double memberDiscount=Double.parseDouble(request.getParameter("memberDiscount"));
		String upgradeIntegral=request.getParameter("upgradeIntegral");
		
		//��װ�ɶ���
		MemberLevel memberLevel=new MemberLevel(levelName, memberDiscount, upgradeIntegral);
		
		//�������ݲ����ʵ������µȼ�,ͨ���쳣�жϳɹ���ʧ��
		try{
			memberLevelDao.add(memberLevel);
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
		List<MemberLevel> memberLevelList=memberLevelDao.findByPage(rows, page);
		
		//��ѯ�ܼ�¼��
		int totalRows=memberLevelDao.memberLevelCount();
		
		//�����ݼ���ת����JSON��ʽ
		String jsonStr=JSONArray.fromObject(memberLevelList).toString();
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
