package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.StockCheckDao;
import com.yidu.t235.xm.entity.StockCheck;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * 数据层对数据库进行操作
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:18:23
 */
public class StockCheckDaoImpl implements StockCheckDao {
	/**
	 * 将库存实体对象添加到数据库stockCheck表中
	 * @param stockCheck 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	@Override
	public int add(StockCheck stockCheck) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="insert into StockCheck values(?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1,stockCheck.getBookName());
			pstmt.setInt(2, stockCheck.getStockNamber());
			//执行SQL操作
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	/**
	 * 将库存对象修改到数据库表
	 * @param stockCheck 库存对象
	 * @return 修改成功的记录数(1:表示修改成功,0:修改失败)
	 */
	@Override
	public int update(String bookName,int number) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="update stockCheck set stockNamber=stockNamber+(?) where bookName=(?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setInt(1, number);
			pstmt.setString(2, bookName);
			//执行SQL操作
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	/**
	 * 根据图书名称从数据库表中查找图书
	 * @param bookName 图书名称
	 * @return 查找成功的图书对象(如果返回null对象，表示查找失败)
	 */
	@Override
	public StockCheck findByName(String bookName) {
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
			String sql="select * from stockCheck where bookName=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置sql语句中的参数
			pstmt.setString(1, bookName);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			//声明一个图书对象
			StockCheck stockCheck=null;
			if(rs.next()){
				//实例化图书对象,用于将结果集中的数据保存到图书对象中
				stockCheck=new StockCheck();
				//将图书编号保存到图书对象中
				stockCheck.setBookNo(rs.getInt("bookNo"));
				//将图书名称保存到图书对象中
				stockCheck.setBookName(rs.getString("bookName"));
				//将采购数量保存到图书对象中
				stockCheck.setStockNamber(rs.getInt("stockNamber"));
			}
			//返回方法值：图书对象
			return stockCheck;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

	 
 
	/**
	 * 从数据库表中查找指定页的图书对象
	 * @param rows 每页的记录数
	 * @param pages 页数
	 * @return 当前页的图书对象集合
	 */
	@Override
	public List<StockCheck> findByPage(int rows, int pages) {
		//定义一个图书集合，用来保存当前页的图书对象
		List<StockCheck> stockCheckList=new ArrayList<StockCheck>();
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
			String sql="select top (?) * from stockCheck"
					+ " where bookno not in("
					+ "select top (?) bookno from StockCheck)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给参数赋值
			pstmt.setInt(1, rows);
			pstmt.setInt(2, (pages-1)*rows);
			
			//System.out.println("SQL:"+sql);
			//执行分页查询的sql语句，将结果保存到结果集对象中
			rs=pstmt.executeQuery();
			//使用循环，对结果集中数据进行处理
			while(rs.next()){
				//定义图书对象,用于将结果集中的一行数据保存到图书对象中
				StockCheck stockCheck=new StockCheck();
				//将图书编号保存到图书对象中
				stockCheck.setBookNo(rs.getInt("bookNo"));
				//将图书名称保存到图书对象中
				stockCheck.setBookName(rs.getString("bookName"));
				//将采购数量保存到图书对象中
				stockCheck.setStockNamber(rs.getInt("stockNamber"));
				//将当前图书对象添加到集合中
				stockCheckList.add(stockCheck);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回分页结果
		return stockCheckList;
	}

	/**
	 * 统计记录数
	 */
	@Override
	public int stockCheckCount() {
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
			String sql="select count(*) as StockCheckCount from StockCheck";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			
			//定义一个变量，用于从结果集中获取行数
			int rowCount=0;
			if(rs.next()){
				//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
				rowCount=rs.getInt("StockCheckCount");
			}
			//返回方法值：图书对象
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}
}
