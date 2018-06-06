package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.yidu.t235.dao.BooktypeDao;
import com.yidu.t235.entity.Booktype;
import com.yidu.t235.utils.JdbcUtils;

public class BooktypeDaoImpl implements BooktypeDao{

	@Override
	public int add(Booktype booktype) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			
		
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写sql语句
			String sql="insert into booktype values(?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			pstmt .setString(1, booktype.getTypename());
			pstmt.setString(2,booktype.getTypedetailed() );
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			JdbcUtils.close(null, pstmt, conn);
		}
			
	}
	@Override
	public int delete(int typeid) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="delete from booktype where typeid=?";
					//实例化预编译语句
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setInt(1, typeid);
					//执行SQL操作
					//将执行后，在数据库表中影响的行数作为方法值返回
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					JdbcUtils.close(null, pstmt, conn);
				}
			}

	@Override
	public int update(Booktype booktype) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="update booktype set typename=?,typedetailed=? where typeid=?";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setString(1, booktype.getTypename());
					pstmt.setString(2, booktype.getTypedetailed());
					pstmt.setInt(3, booktype.getTypeid());
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
	public List<Booktype> findByPage(int rows, int page) {
		        //定义一个部门集合，用来保存当前页的部门信息
				List<Booktype> booktypeList=new ArrayList<Booktype>();
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
					String sql="select top (?) * from booktype "
							+ "where typeid not in("
							+ "select top (?) typeid from booktype)";
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
						Booktype booktype=new Booktype();
						booktype.setTypeid(rs.getInt("typeid"));
						booktype.setTypename(rs.getString("typename"));
						booktype.setTypedetailed(rs.getString("typedetailed"));
						
						booktypeList.add(booktype);
					}
					
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//关闭/释放资源
					JdbcUtils.close(rs, pstmt, conn);
				}
				//返回分页结果
				return booktypeList;
				
			}

	@Override
	public int getBooktypeCount() {
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
					String sql="select count(*) as booktypecount from booktype";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//执行SQL语句，返回结果集对象
					rs=pstmt.executeQuery();
					//处理结果集对象:将结果集中的内容保存到对象中 
					
					//定义一个变量，用于从结果集中获取行数
					int rowCount=0;
					if(rs.next()){
						//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
						rowCount=rs.getInt("booktypecount");
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