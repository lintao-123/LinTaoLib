package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.dao.BookoperationDao;
import com.yidu.t235.entity.Bookoperation;
import com.yidu.t235.utils.JdbcUtils;

public class BookoperationDaoImpl implements BookoperationDao{

	@Override
	public int add(Bookoperation bookoperation) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="insert into bookoperation values(?,?,?,?,?,?)";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setString(1, bookoperation.getBookname());
					pstmt.setString(2, bookoperation.getBooktype());
					pstmt.setString(3, bookoperation.getBookauthor());
					pstmt.setString(4, bookoperation.getSupplier());
					pstmt.setString(5, bookoperation.getBookprice());
					pstmt.setString(6, bookoperation.getBookunit());
					//执行SQL操作
					//将执行后，在数据库表中影响的行数作为方法值返回
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//关闭数据库连接
					JdbcUtils.close(null, pstmt, conn);
				}
			}


	@Override
	public int delete(int bookno) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="delete from bookoperation where bookno=?";
					//实例化预编译语句
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setInt(1, bookno);
					//执行SQL操作
					//将执行后，在数据库表中影响的行数作为方法值返回
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//关闭数据库连接
					JdbcUtils.close(null, pstmt, conn);
				}
			}


	@Override
	public int update(Bookoperation bookoperation) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="update bookoperation set bookname=?,booktype=?,bookauthor=?,"
							+ "supplier=?,bookprice=?,bookunit=? where bookno=?";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setString(1, bookoperation.getBookname());
					pstmt.setString(2, bookoperation.getBooktype());
					pstmt.setString(3, bookoperation.getBookauthor() );
					pstmt.setString(4, bookoperation.getSupplier());
					pstmt.setString(5, bookoperation.getBookprice());
					pstmt.setString(6, bookoperation.getBookunit());
					pstmt.setInt(7, bookoperation.getBookno());
					//执行SQL操作
					//将执行后，在数据库表中影响的行数作为方法值返回
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//关闭数据库连接
					JdbcUtils.close(null, pstmt, conn);
				}
			}


	@Override
	public List<Bookoperation> findByPage(int rows, int page) {
		        //定义一个部门集合，用来保存当前页的部门信息
				List<Bookoperation> bookoperationList=new ArrayList<Bookoperation>();
				//声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				//声明结果集对象
				ResultSet rs=null;
				
				try {
					//实例化连接对象
					conn=JdbcUtils.getConnectoin();
					//编写执行的SQL语句
					String sql="select top (?) * from bookoperation "
							+ "where bookno not in("
							+ "select top (?) bookno from bookoperation)";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//给参数赋值
					pstmt.setInt(1, rows);
					pstmt.setInt(2, (page-1)*rows);
					
					//System.out.println("SQL:"+sql);
					//执行分页查询的sql语句，将结果保存到结果集对象中
					rs=pstmt.executeQuery();
					//使用循环，对结果集中数据进行处理
					while(rs.next()){
						//定义部门对象,用于将结果集中的一行数据保存到部门对象中
						Bookoperation bookoperation=new Bookoperation();
						bookoperation.setBookno(rs.getInt("bookno"));
						bookoperation.setBookname(rs.getString("bookname"));
						bookoperation.setBooktype(rs.getString("booktype"));
						bookoperation.setBookauthor(rs.getString("bookauthor"));
						bookoperation.setSupplier(rs.getString("supplier"));
						bookoperation.setBookprice(rs.getString("bookprice"));
						bookoperation.setBookunit(rs.getString("bookunit"));
						
						bookoperationList.add(bookoperation);
					}
					
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//关闭/释放资源
					JdbcUtils.close(rs, pstmt, conn);
				}
				//返回分页结果
				return bookoperationList;

			}
	@Override
	public int getBookoperationCount() {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				//声明结果集对象
				ResultSet rs=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写SQL语句
					String sql="select count(*) as bookoperationcount from bookoperation";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//执行SQL语句，返回结果集对象
					rs=pstmt.executeQuery();
					//处理结果集对象:将结果集中的内容保存到对象中 
					
					//定义一个变量，用于从结果集中获取行数
					int rowCount=0;
					if(rs.next()){
						//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
						rowCount=rs.getInt("bookoperationcount");
					}
					//返回方法值：员工对象
					return rowCount;
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//关闭/释放资源(结果集对象，语句对象，连接对象)
					JdbcUtils.close(rs, pstmt, conn);
				}
			}

		}