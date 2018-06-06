package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.EnterInventoryDao;
import com.yidu.t235.xm.entity.EnterInventory;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * 数据层对数据库进行操作
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:18:23
 */
public class EnterInventoryDaoImpl implements EnterInventoryDao {
	/**
	 * 将图书实体对象添加到数据库enterInventory表中
	 * @param enterInventory 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	@Override
	public int add(EnterInventory enterInventory) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="insert into enterInventory values(?,?,?,?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1,enterInventory.getBookName());
			pstmt.setInt(2, enterInventory.getPurchaseQuantity());
			pstmt.setDouble( 3,enterInventory.getBookBid());
			pstmt.setString(4, enterInventory.getInventoryTime());
			pstmt.setString(5, enterInventory.getConsoleOperator());
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
	 * 根据图书编号从数据库表enterInventory中删除图书
	 * @param bookNo 图书编号
	 * @return 成功删除的记录数(非0/大于0:表示删除成功，0：删除失败)
	 */
	@Override
	public int delete(int bookNo) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="delete from enterInventory where bookNo=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setInt(1, bookNo);
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
	 * 将图书对象修改到数据库表
	 * @param enterInventory 图书对象
	 * @return 修改成功的记录数(1:表示修改成功,0:修改失败)
	 */
	@Override
	public int update(EnterInventory enterInventory) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="update enterInventory set bookName=?,purchaseQuantity=?,"
					+ " bookBid=?, inventoryTime=?,consoleOperator=? "
					+ "where bookNo=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1,enterInventory.getBookName());
			pstmt.setInt(2, enterInventory.getPurchaseQuantity());
			pstmt.setDouble( 3,enterInventory.getBookBid());
			pstmt.setString(4, enterInventory.getInventoryTime());
			pstmt.setString(5, enterInventory.getConsoleOperator());
			pstmt.setInt(6, enterInventory.getBookNo());
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
	public EnterInventory findByName(String bookName) {
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
			String sql="select * from enterInventory where bookname=? ";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置sql语句中的参数
			pstmt.setString(1, bookName);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			//声明一个图书对象
			EnterInventory enterInventory=null;
			if(rs.next()){
				//实例化图书对象,用于将结果集中的数据保存到图书对象中
				enterInventory=new EnterInventory();
				//将图书编号保存到图书对象中
				enterInventory.setBookNo(rs.getInt("bookNo"));
				//将图书名称保存到图书对象中
				enterInventory.setBookName(rs.getString("bookName"));
				//将采购数量保存到图书对象中
				enterInventory.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				//将图书进价保存到图书对象中
				enterInventory.setBookBid(rs.getDouble("bookBid"));
				//将入库时间保存到图书对象
				enterInventory.setInventoryTime(rs.getString("inventoryTime"));
				//将操作员保存到图书对象中
				enterInventory.setConsoleOperator(rs.getString("consoleOperator"));
			}
			//返回方法值：图书对象
			return enterInventory;
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
	public List<EnterInventory> findByPage(int rows, int pages) {
		//定义一个图书集合，用来保存当前页的图书对象
		List<EnterInventory> enterInventoryList=new ArrayList<EnterInventory>();
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
			String sql="select top (?) * from enterInventory"
					+ " where bookno not in("
					+ "select top (?) bookno from enterInventory)";
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
				EnterInventory enterInventory=new EnterInventory();
				//将图书编号保存到图书对象中
				enterInventory.setBookNo(rs.getInt("bookNo"));
				//将图书名称保存到图书对象中
				enterInventory.setBookName(rs.getString("bookName"));
				//将采购数量保存到图书对象中
				enterInventory.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				//将图书进价保存到图书对象中
				enterInventory.setBookBid(rs.getDouble("bookBid"));
				//将入库时间保存到图书对象
				enterInventory.setInventoryTime(rs.getString("inventoryTime"));
				//将操作员保存到图书对象中
				enterInventory.setConsoleOperator(rs.getString("consoleOperator"));
				//将当前图书对象添加到集合中
				enterInventoryList.add(enterInventory);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回分页结果
		return enterInventoryList;
	}

	
	/**
	 * 统计记录数
	 */
	@Override
	public int enterInventoryCount() {
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
			String sql="select count(*) as enterInventoryCount from enterInventory";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			
			//定义一个变量，用于从结果集中获取行数
			int rowCount=0;
			if(rs.next()){
				//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
				rowCount=rs.getInt("enterInventoryCount");
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
	
	/**
	 * 根据图书编号查询图书
	 */
	@Override
	public EnterInventory findById(int bookNo) {
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
			String sql="select * from enterInventory where bookno=? ";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置sql语句中的参数
			pstmt.setInt(1, bookNo);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			//声明一个图书对象
			EnterInventory enterInventory=null;
			if(rs.next()){
				//实例化图书对象,用于将结果集中的数据保存到图书对象中
				enterInventory=new EnterInventory();
				//将图书编号保存到图书对象中
				enterInventory.setBookNo(rs.getInt("bookNo"));
				//将图书名称保存到图书对象中
				enterInventory.setBookName(rs.getString("bookName"));
				//将采购数量保存到图书对象中
				enterInventory.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				//将采购价格保存到图书对象中
				enterInventory.setBookBid(rs.getDouble("bookBid"));
				//将入库时间保存到图书对象
				enterInventory.setInventoryTime(rs.getString("inventoryTime"));
				//将操作员保存到图书对象中
				enterInventory.setConsoleOperator(rs.getString("consoleOperator"));
			}
			//返回方法值：图书对象
			return enterInventory;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}	
}
