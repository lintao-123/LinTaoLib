package xmbook.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 功能：数据库连接和关闭工具类
 * @author books
 * @version 1.0
 * 日期：2018-2-10
 */
public class JdbcUtils {
	//数据库驱动程序
	private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//连接字符串
	private static String url="jdbc:sqlserver://localhost:1433;databaseName=xmbook";
	//用户名
	private static String user="sa";
	//连接密码
	private static String password="987654";
	
	//静态块，实现类加载时，加载数据库的驱动程序
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 构造方法
	 */
	private JdbcUtils(){
		
	}

	/**
	 * 数据库连接方法
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}
	
	/**
	 * 释放数据库连接、语句对象、结果集对象资源
	 * @param rs 结果集对象
	 * @param pstmt 语句对象
	 * @param conn 数据库连接对象
	 */
	public static void close(ResultSet rs,Statement pstmt,Connection conn){
		try {
			//如果结果集对象不为空，则关闭
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//如果语句对象不为空，则关闭
			try {
				if(pstmt!=null){
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				//如果连接对象不为空，则关闭
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
