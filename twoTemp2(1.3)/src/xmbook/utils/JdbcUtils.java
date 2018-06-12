package xmbook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ܣ����ݿ����Ӻ͹رչ�����
 * @author books
 * @version 1.0
 * ���ڣ�2018-2-10
 */
public class JdbcUtils {
	//���ݿ���������
	private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//�����ַ���
	private static String url="jdbc:sqlserver://localhost:1433;databaseName=xmbook";
	//�û���
	private static String user="sa";
	//��������
	private static String password="987654";
	
	//��̬�飬ʵ�������ʱ���������ݿ����������
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���췽��
	 */
	private JdbcUtils(){
		
	}

	/**
	 * ���ݿ����ӷ���
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
	
	/**
	 * �ͷ����ݿ����ӡ������󡢽����������Դ
	 * @param rs ���������
	 * @param pstmt ������
	 * @param conn ���ݿ����Ӷ���
	 */
	public static void close(ResultSet rs,Statement pstmt,Connection conn){
		try {
			//������������Ϊ�գ���ر�
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//���������Ϊ�գ���ر�
			try {
				if(pstmt!=null){
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				//������Ӷ���Ϊ�գ���ر�
				try {
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
