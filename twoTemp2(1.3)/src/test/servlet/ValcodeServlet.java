package test.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class test
 */
@WebServlet("/ValcodeServlet")
public class ValcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValcodeServlet() {
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
		// ��֪�������ͼƬ����
       response.setContentType("image/jpeg");
       // ���������������
       response.setHeader("pragma", "no-cache");
       response.setHeader("cache-control", "no-cache");
       response.setHeader("expires", "0");
       // ������4λ���ֹ��ɵ���֤��
       int length = 4;
       String valcode  = "";
       Random rd =  new Random();
       for(int i=0; i<length; i++)
           valcode+=rd.nextInt(10);
       // �Ѳ�������֤����뵽Session��
       HttpSession  session = request.getSession();
       session.setAttribute("valcode", valcode);
       // ����ͼƬ
       int width = 80;
       int height = 25;
       BufferedImage img= new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
       // ��ȡһ��Graphics
       Graphics g = img.getGraphics();
       // ��䱳��ɫ
       g.setColor(Color.WHITE);
       g.fillRect(0, 0, width, height);
       // ��������50
       for(int i=0; i<50; i++){
           g.setColor(new Color(rd.nextInt(100)+155,rd.nextInt(100)+155,rd.nextInt(100)+155));
           g.drawLine(rd.nextInt(width), rd.nextInt(height),rd.nextInt(width), rd.nextInt(height));
       }
       // ���Ʊ߿�
       g.setColor(Color.GRAY);
       g.drawRect(0, 0, width-1, height-1);
       // ������֤��
       Font[] fonts = {new Font("����",Font.BOLD,18),new Font("����",Font.BOLD,18),new Font("����",Font.BOLD,18),new Font("��Բ",Font.BOLD,18)};
       for(int i=0; i<length; i++){
           g.setColor(new Color(rd.nextInt(150),rd.nextInt(150),rd.nextInt(150)));
           g.setFont(fonts[rd.nextInt(fonts.length)]);
           g.drawString(valcode.charAt(i)+"", width/valcode.length()*i+2, 18);
       }
       // ���ͼ��
       g.dispose();
       ImageIO.write(img, "jpeg", response.getOutputStream()); 
		
	}

}
