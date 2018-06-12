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
		// 获取验证码
       String valcode = request.getSession().getAttribute("valcode").toString();
       // 获取用户填写的验证码
       String vcode = request.getParameter("vcode");
       //获取用户输入的用户名
       String name =  request.getParameter("name");
       //获取用户输入用户密码
       String pw = request.getParameter("pw");
       // 进行验证
       if(name.equals("XM")&&pw.equals("123456")){
	       if(!valcode.equals(vcode)){
	           System.out.println(">>>验证码错误！");
	          
	          // request.getRequestDispatcher("login.html").forward(request, response);
	           //重定向
	           response.sendRedirect("login.html");
	       }else{
	           System.out.println(">>>验证码正确！");
	           //request.getRequestDispatcher("index.html").forward(request, response);
	           response.sendRedirect("index.html");
	       	}
		}else{
			 //response.sendRedirect("error.jsp");
			//密码错误
			//请求转发
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
