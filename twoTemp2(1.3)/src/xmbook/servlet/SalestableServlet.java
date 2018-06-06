package xmbook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yidu.t235.xm.dao.StockCheckDao;
import com.yidu.t235.xm.dao.Impl.StockCheckDaoImpl;

import xmbook.dao.SalestableDao;
import xmbook.dao.impl.SalestableDaoImpl;
import xmbook.entity.Salestable;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class salestableServlet
 */
@WebServlet("/SalestableServlet")
public class SalestableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       //����һ�����ݲ��������
	SalestableDao salestabledao=new SalestableDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalestableServlet() {
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
		}else if("salestableAdd".equals(method)){
			//���۵����
			salestableAdd(request,response);
		}else if("salestableDelete".equals(method)){
			String booknoStr=request.getParameter("salestableArray");
			//����ɾ������
			salestableDelete(request,response,booknoStr);
		}else if("salestableUpdate".equals(method)){
			//���ø��·���
			salestableUpdate(request,response);
		}
	}
	private void salestableUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		//��ȡ���Ա�������
		int bookno=Integer.parseInt(request.getParameter("bookno"));
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		int salesnumber=Integer.parseInt(request.getParameter("salesnumber"));
		double shouldprice=Double.parseDouble(request.getParameter("shouldprice"));
		double realityprice=Double.parseDouble(request.getParameter("realityprice"));
		String salestime=request.getParameter("salestime");
		String saleser=request.getParameter("saleser");
		//�����ݷ�װ��������
		Salestable salestable=new Salestable();
		salestable.setBookno(bookno);
		salestable.setOrdernumber(ordernumber);
		salestable.setBookname(bookname);
		salestable.setSalesnumber(salesnumber);
		salestable.setShouldprice(shouldprice);
		salestable.setRealityprice(realityprice);
		salestable.setSalestime(salestime);
		salestable.setSaleser(saleser);
		
		//��ѯ���ƣ�������۱��޸�ǰ����������
		Salestable salestablenumber=salestabledao.findName(bookname);
		//ͨ���������޸��޸ĺ�Ĳ�ֵ
		//��ʽ��ֵ=�޸ĺ����������-�޸�ǰ����������
		int number=salesnumber-salestablenumber.getSalesnumber();
		//�������ݲ�
		int result=salestabledao.update(salestable);
		 //��������StockCheck�������ݲ��������
		StockCheckDao stockCheckDao=new StockCheckDaoImpl();
		//���ÿ������ݲ��޸Ŀ������
		stockCheckDao.update(bookname, -number);
		if(result>0){
			out.println("updatesuccess");
		}else{
			out.println("updatefailure");
		}
		out.close();		
	}

	/**
	 * ɾ����������
	 * @param request �������
	 * @param response��Ӧ����
	 * @param booknoStr ����ַ���
	 * @throws IOException IO�쳣
	 */
	private void salestableDelete(HttpServletRequest request, HttpServletResponse response, String booknoStr) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		//�����۱���ַ������зָ�
		String [] salestables=booknoStr.split(",");
		//�����������ݲ�������
		StockCheckDao stockCheckDao=new StockCheckDaoImpl();
		//���ڿ���������ɾ����ʹ���쳣��ѭ���������ж�ɾ���Ƿ�
		try {
			for(String salestableStr:salestables){
				//����ǰ����ַ�������ת��������
				int bookno=Integer.parseInt(salestableStr);
				/*��ѯ��ɾ��ǰ��ͼ������������*/
				//����һ�����������ղ��ŷ������صĶ���
				Salestable salestableNumber=salestabledao.findId(bookno);
				//����get�����������
				String name=salestableNumber.getBookname();
				//����get�����������
				int number=salestableNumber.getSalesnumber();
				//�������۱����ݲ�������ɾ��
				salestabledao.delete(bookno);
				//���ÿ������ݲ��������޸�
				stockCheckDao.update(name, number);
			}
			out.println("deletesuccess");
		} catch (NumberFormatException e) {
			out.println("deletefailure");
		}
		//�ر��������
		out.close();
	}
	/**
	 * ʵ�����۱��������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣
	 */
	private void salestableAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		
		//��ȡ���Ա�������
		String ordernumber=request.getParameter("ordernumber");
		String bookname=request.getParameter("bookname");
		int salesnumber=Integer.parseInt(request.getParameter("salesnumber"));
		double shouldprice=Double.parseDouble(request.getParameter("shouldprice"));
		double realityprice=Double.parseDouble(request.getParameter("realityprice"));
		String salestime=request.getParameter("salestime");
		String saleser=request.getParameter("saleser");
		//�����ݷ�װ��������
		Salestable salestable=new Salestable(salesnumber, ordernumber,bookname,salesnumber,shouldprice,realityprice,salestime,saleser);
		
		
		//�������ݲ����ʵ�������Ա����ͨ���쳣�жϳɹ���ʧ��
		try {
			salestabledao.add(salestable);
			
			//��������StockCheck�������ݲ��������
			StockCheckDao stockCheckDao=new StockCheckDaoImpl();
			
			//����ͼ�������޸Ŀ������
			stockCheckDao.update(bookname, -salesnumber);
			out.println("addsuccess");
		} catch (Exception e) {
			out.print("addfailure");
		}
		//�ر��������
		out.close();
	}
	/**
	 * ʵ����ʾ��ǰҳ�����������
	 * @param request �������
	 * @param response ��Ӧ����
	 * @throws IOException IO�쳣����
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//�����������
		PrintWriter out=response.getWriter();
		//��ȡ��datagrid������ݹ�����ҳ��¼�鼰��ǰҳ��
		int rows=Integer.parseInt(request.getParameter("rows"));
		int pages=Integer.parseInt(request.getParameter("page"));
		//ִ�з�ҳ��ѯ
		List<Salestable> salestables=salestabledao.findByPage(rows, pages);
		//��ѯ��¼��
		int totalRows=salestabledao.salestableCount(); 
		//�����ݼ���ת����JSON��ʽ
		String jsonStr=JSONArray.fromObject(salestables).toString();
		String json="{\"total\":"+totalRows+",\"rows\":"+jsonStr+"}";
		//������ͻ���
		out.write(json);
		//�ر��������
		out.close();		
	}
}
