package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.dao.ProcureDao;
import com.yidu.t235.entity.Procure;
import com.yidu.t235.utils.JdbcUtils;

public class ProcureDaoImpl implements ProcureDao{

	@Override
	public int add(Procure procure) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写sql语句
			String sql="insert into procure values(?,?,?,?,?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置预编译语句参数
			pstmt.setString(1, procure.getOrdernumber());
			pstmt.setString(2, procure.getBookname());
			pstmt.setInt(3, procure.getSalesvolume());
			pstmt.setDouble(4, procure.getBid());
			pstmt.setString(5, procure.getSalesperiods());
			pstmt.setString(6, procure.getOperator());
			//执行SQL操作
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//关闭数据库连接
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	@Override
	public int delete(int bookid) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写sql语句
			String sql="delete from procure where bookid=?";
			//实例化预编译语句
			pstmt=conn.prepareStatement(sql);
			//设置预编译语句参数
			pstmt.setInt(1, bookid);
			//执行SQL操作
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			//关闭数据库连接
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	@Override
	public int update(Procure procure) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写sql语句
			String sql="update procure set ordernumber=?,bookname=?,salesvolume=?,"
					+ "bid=? ,salesperiods=?,operator=? where bookid=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置预编译语句参数
			pstmt.setString(1, procure.getOrdernumber());
			pstmt.setString(2, procure.getBookname());
			pstmt.setInt(3, procure.getSalesvolume());
			pstmt.setDouble(4, procure.getBid());
			pstmt.setString(5, procure.getSalesperiods());
			pstmt.setString(6, procure.getOperator());
			pstmt.setInt(7, procure.getBookid());
			//执行SQL操作
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			//关闭数据库连接
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	@Override
	public List<Procure> findByPage(int rows, int page) {
		//定义一个部门集合，用来保存当前页的部门信息
		List<Procure> procureList=new ArrayList<Procure>();
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
			String sql="select top (?) * from procure"
			+" where bookid not in("
			+"select top (?) bookid from procure)";
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
				Procure procure=new Procure();
				procure.setBookid(rs.getInt("bookid"));
				procure.setOrdernumber(rs.getString("ordernumber"));
				procure.setBookname(rs.getString("bookname"));
				procure.setSalesvolume(rs.getInt("salesvolume"));
				procure.setBid(rs.getDouble("bid"));
				procure.setTotal(procure.getTotal()); 
				procure.setSalesperiods(rs.getString("salesperiods"));
				procure.setOperator(rs.getString("operator"));
				
				procureList.add(procure);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			//关闭/释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回分页结果
		return procureList;
	}

	@Override
	public int getProcureCount() {
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
			String sql="select count(*) as procurecount from procure";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			
			//定义一个变量，用于从结果集中获取行数
			int rowCount=0;
			if(rs.next()){
			  //此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
				rowCount=rs.getInt("procurecount");
			}
			//返回方法值：员工对象
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

}
