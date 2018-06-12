package test.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ��֤��
       String valcode = request.getSession().getAttribute("valcode").toString();
       // ��ȡ�û���д����֤��
       String vcode = request.getParameter("vcode");
       //��ȡ�û�������û���
       String name =  request.getParameter("name");
       //��ȡ�û������û�����
       String pw = request.getParameter("pw");
       // ������֤
       if(name.equals("XM")&&pw.equals("123456")){
	       if(!valcode.equals(vcode)){
	           System.out.println(">>>��֤�����");
	          
	          // request.getRequestDispatcher("login.html").forward(request, response);
	           //�ض���
	           response.sendRedirect("login.html");
	       }else{
	           System.out.println(">>>��֤����ȷ��");
	           //request.getRequestDispatcher("index.html").forward(request, response);
	           response.sendRedirect("index.html");
	       	}
		}else{
			 //response.sendRedirect("error.jsp");
			//�������
			//����ת��
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
