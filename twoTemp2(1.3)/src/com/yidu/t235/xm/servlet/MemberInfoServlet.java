package com.yidu.t235.xm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.MemberInfoDao;
import com.yidu.t235.xm.dao.Impl.MemberInfoDaoImpl;
import com.yidu.t235.xm.entity.MemberInfo;

import net.sf.json.JSONArray;

/**
 * ��Ա��Ϣ����Ʋ���	  
 * @author �����
 * ���ڣ�2018��3��8�� ����4:34:46
 */
@WebServlet("/MemberInfoServlet")
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//����һ�����ݲ��������
	private MemberInfoDao memberInfoDao=new MemberInfoDaoImpl();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfoServlet() {
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
		}else if("memberInfoAdd".equals(method)){
			//��Ա���
			memberInfoAdd(request,response);
		}else if("memberInfoDelete".equals(method)){
			//��ȡ����������л�Ա����ַ�������
			String memberIdStr=request.getParameter("cArray");
			//����ɾ������ 
			memberInfoDelete(request,response,memberIdStr);
		}else if("memberInfoUpdate".equals(method)){
			
			//�����޸ķ��� 
			memberInfoUpdate(request,response);
		}
	}

	/**
	 * ����Ϣ�����޸�
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO����
	 */
	private void memberInfoUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		int memberId=Integer.parseInt(request.getParameter("memberId"));
		String memberName=request.getParameter("memberName");
		String memberSex=request.getParameter("memberSex");
		String memberTelephone=request.getParameter("memberTelephone");
		String memberLevel=request.getParameter("memberLevel");
		double memberIntegral=Double.parseDouble(request.getParameter("memberIntegral"));
		
		//��װ�ɶ���
		MemberInfo memberInfo=new MemberInfo(memberId,memberName, memberSex, memberTelephone, memberLevel, memberIntegral);
		
		//�������ݲ����ʵ������»�Ա
		int rows=memberInfoDao.update(memberInfo);
				
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
	 * ɾ����Ա��Ϣ
	 * @param request �������
	 * @param response ��Ӧ����
	 * @param empNoStr ����ַ���
	 * @throws IOException IO�쳣
	 */
	private void memberInfoDelete(HttpServletRequest request, HttpServletResponse response, String memberIdStr) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//�Ի�Ա����ַ������зָ�[�������ö��Ž������ӣ��˴�Ҳ�ö��ŷָ�]
		String[] memberInfo=memberIdStr.split(",");
		
		System.out.println("ɾ���Ļ�Ա���:"+memberInfo);
		//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�ɹ�
		try{
			for(String memberInfoStr:memberInfo){
				//����ǰ����ַ�������ת��������
				int memberId=Integer.parseInt(memberInfoStr);
				//�������ݲ�������ɾ��
				memberInfoDao.delete(memberId);
			}
			out.println("deletesuccess");
		}catch(Exception e){
			out.println("deletefailure");
		}
		
		//�ر��������
		out.close();
	}

	/**
	 * ʵ���»�Ա��Ϣ���
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void memberInfoAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		String memberName=request.getParameter("memberName");
		String memberSex=request.getParameter("memberSex");
		String memberTelephone=request.getParameter("memberTelephone");
		String memberLevel=request.getParameter("memberLevel");
		double memberIntegral=Double.parseDouble(request.getParameter("memberIntegral"));
		
		//��װ�ɶ���
		MemberInfo memberInfo=new MemberInfo(memberName, memberSex, memberTelephone, memberLevel, memberIntegral);
		
		
		//�������ݲ����ʵ�������ͼ��,ͨ���쳣�жϳɹ���ʧ��
		try{
			memberInfoDao.add(memberInfo);
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
		List<MemberInfo> memberInfoList=memberInfoDao.findByPage(rows, page);
		
		//��ѯ�ܼ�¼��
		int totalRows=memberInfoDao.memberInfoCount();
		
		//�����ݼ���ת����JSON��ʽ
		String jsonStr=JSONArray.fromObject(memberInfoList).toString();
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
