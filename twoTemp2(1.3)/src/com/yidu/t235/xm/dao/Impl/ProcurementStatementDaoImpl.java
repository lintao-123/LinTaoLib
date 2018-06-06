package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.ProcurementStatementDao;
import com.yidu.t235.xm.entity.ProcurementStatement;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * 采购订单详情实现类
 * @author Administrator
 *
 */
public class ProcurementStatementDaoImpl implements ProcurementStatementDao{
	/**
	 * 添加采购订单详情到数据库表中
	 * @param procurement 采购订单详情对象
	 * @return 返回大于1,表示添加成功 返回0,则添加表示失败
	 */
	@Override
	public int add(ProcurementStatement procurement) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
	    PreparedStatement pstmt=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//预编译SQL语句
			String sql="insert into procurementStatement values(?,?,?,?,?,?,?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			/*设置SQL语句参数*/
			//设置采购订单号
			pstmt.setString(1, procurement.getProcurementNo());
			//设置图书名称
			pstmt.setString(2, procurement.getBookName());
			//设置图书类型
			pstmt.setString(3, procurement.getBookType());
			//设置图书进价
			pstmt.setDouble(4, procurement.getBookBid());
			//设置供应商
			pstmt.setString(5, procurement.getSupplier());
			//设置采购时间
			pstmt.setString(6,procurement.getLibraryTime());
			//设置采购数量
			pstmt.setInt(7, procurement.getPurchaseQuantity());
			//设置操作员
			pstmt.setString(8,procurement.getPlayEmp());
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//关闭数据库连接和释放资源
			JdbcUtils.close(null, pstmt, conn);
		}
		
	}
	/**
	 * 根据编号删除订单详情到数据库中
	 * @param tempId 编号
	 * @return 返回大于1,表示删除成功 返回0,则删除表示失败
	 */
	@Override
	public int delete(int tempId) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="delete from procurementStatement where tempId=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			/*设置SQL语句中的参数*/
			//设置编号
			pstmt.setInt(1, tempId);
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//关闭数据库连接和释放资源
			JdbcUtils.close(null, pstmt, conn);
		}
		
	}
	/**
	 * 将采购订单详情修改到数据库表中
	 * @param procurement 采购订单详情
	 * @return 返回大于1,表示修改成功 返回0,则修改表示失败
	 */
	@Override
	public int update(ProcurementStatement procurement) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="update procurementStatement set procurementNo=?, bookName=?, bookType=?,"
					+ "  bookBid=?,supplier=?,libraryTime=?,purchaseQuantity=?, "
					+ "   playEmp=? where tempId=?  ";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			/*设置SQL语句参数*/
			//设置采购订单号
			pstmt.setString(1, procurement.getProcurementNo());
			//设置图书名称
			pstmt.setString(2, procurement.getBookName());
			//设置图书类型
			pstmt.setString(3, procurement.getBookType());
			//设置图书进价
			pstmt.setDouble(4, procurement.getBookBid());
			//设置供应商
			pstmt.setString(5, procurement.getSupplier());
			//设置采购时间
			pstmt.setString(6,procurement.getLibraryTime());
			//设置采购数量
			pstmt.setInt(7, procurement.getPurchaseQuantity());
			//设置操作员
			pstmt.setString(8,procurement.getPlayEmp());
			//设置编号
			pstmt.setInt(9, procurement.getTempId());
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//关闭数据库连接和释放资源
			JdbcUtils.close(null,pstmt,conn);
		}
	}
	/**
	 * 从数据库表中查找出指定页
	 * @param rows 每页记录数
	 * @param page 页数
	 * @return 返回集合对象
	 */
	@Override
	public List<ProcurementStatement> findPage(int rows, int page) {
		//定义一个采购订单对象集合,用来保存当前页对象
		List<ProcurementStatement> procurementStatementsList=new ArrayList<ProcurementStatement>();
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
			String sql=" select top(?) * from procurementStatement "
					+ " where procurementNo not in( "
					+ " select top(?) procurementNo from procurementStatement) ";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			/*设置SQL语句参数*/
			//设置行数
			pstmt.setInt(1, rows);
			//设置行数*页数-1
			pstmt.setInt(2, rows*(page-1));
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//使用循环,对结果集中的数据进行处理
			while(rs.next()){
				//定义采购订单对象，用于对结果集中的一行数据保存到订单对象中
				ProcurementStatement procurementStatement=new ProcurementStatement();
				//将员工编号保存到对象中
				procurementStatement.setTempId(rs.getInt("tempId"));
				//将采购订单号保存到对象中
				procurementStatement.setProcurementNo(rs.getString("procurementNo"));
				//将图书名称保存到对象中
				procurementStatement.setBookName(rs.getString("bookName"));
				//将图书类型保存到对象中
				procurementStatement.setBookType(rs.getString("bookType"));
				//将图书进价保存到对象中
				procurementStatement.setBookBid(rs.getDouble("bookBid"));
				//将供应商保存到对象中
				procurementStatement.setSupplier(rs.getString("supplier"));
				//将采购时间保存到对象中
				procurementStatement.setLibraryTime(rs.getString("libraryTime"));
				//将采购数量保存到对象中
				procurementStatement.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				//将操作员保存到对象中
				procurementStatement.setPlayEmp(rs.getString("playEmp"));
				//将对象的总价保存到对象中
				procurementStatement.setTotal(procurementStatement.getTotal());
				//将当前采购订单详情对象添加到集合中
				procurementStatementsList.add(procurementStatement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//关闭数据库连接和释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回分页结果
		return procurementStatementsList;
	}
	/**
	 * 统计采购订单详情表的记录数
	 * @return
	 */
	@Override
	public int getCount() {
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
			String sql="select count(*) as procurementStatementInfo from procurementStatement ";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行SQL语句，返回结果集对象
			rs= pstmt.executeQuery();
			//定义一个变量，用于从结果集中获取行数
			int rowCount=0;
			if(rs.next()){
				//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
				rowCount=rs.getInt("procurementStatementInfo");
			}
			//返回方法值：采购订单详情对象
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//关闭数据库连接和释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
				
	}

}
