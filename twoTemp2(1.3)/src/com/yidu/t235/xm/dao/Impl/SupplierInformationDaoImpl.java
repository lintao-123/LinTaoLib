package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.SupplierInformationDao;
import com.yidu.t235.xm.entity.SupplierInformation;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * 数据层对数据库进行操作
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:18:23
 */
public class SupplierInformationDaoImpl implements SupplierInformationDao{
	/**
	 * 将供应商编号对象添加到数据库supplierInformation表中
	 * @param supplierInformation 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	@Override
	public int add(SupplierInformation supplierInformation) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="insert into supplierInformation values(?,?,?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1, supplierInformation.getSupplier());
			pstmt.setString(2, supplierInformation.getTelephoneNumbers());
			pstmt.setString(3,supplierInformation.getAddress());
			pstmt.setString(4, supplierInformation.getContacts());
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
	 * 根据供应商编号从数据库表supplierInformation中删除供应商编号
	 * @param supplierNumber 供应商编号
	 * @return 成功删除的记录数(非0/大于0:表示删除成功，0：删除失败)
	 */
	@Override
	public int delete(int supplierNumber) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="delete from supplierInformation where supplierNumber=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setInt(1, supplierNumber);
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
	 * 将供应商对象修改到数据库表
	 * @param supplierInformation 供应商对象
	 * @return 修改成功的记录数(1:表示修改成功,0:修改失败)
	 */
	@Override
	public int update(SupplierInformation supplierInformation) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="update SupplierInformation set supplier=?,telephoneNumbers=?,address=?,contacts=? "
					+ "where supplierNumber=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1,supplierInformation.getSupplier());
			pstmt.setString(2,supplierInformation.getTelephoneNumbers());
			pstmt.setString(3, supplierInformation.getAddress());
			pstmt.setString(4, supplierInformation.getContacts());
			pstmt.setInt(5, supplierInformation.getSupplierNumber());
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
	 * 根据供应商编号从数据库表中查找编号
	 * @param supplierNumber 供应商编号
	 * @return 查找成功供应商的对象(如果返回null对象，表示查找失败)
	 */
	@Override
	public SupplierInformation findById(int supplierNumber) {
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
			String sql="select * from supplierInformation where supplierNumber=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置sql语句中的参数
			pstmt.setInt(1, supplierNumber);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			//声明一个供应商对象
			SupplierInformation supplierInformation=null;
			if(rs.next()){
				//实例化供应商对象,用于将结果集中的数据保存到供应商对象中
				supplierInformation=new SupplierInformation();
				//将供应商编号保存到供应商对象中
				supplierInformation.setSupplierNumber(rs.getInt("supplierNumber"));
				//将供应商保存到供应商对象中
				supplierInformation.setSupplier(rs.getString("Supplier"));
				//将联系电话保存到供应商对象中
				supplierInformation.setTelephoneNumbers(rs.getString("telephoneNumbers"));
				//将联系地址保存到供应商对象中
				supplierInformation.setAddress(rs.getString("address"));
				//将联系人保存到供应商对象
				supplierInformation.setContacts(rs.getString("contacts"));
			}
			//返回方法值：供应商对象
			return supplierInformation;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

	/**
	 * 从数据库表中查找指定页的供应商对象
	 * @param rows 每页的记录数
	 * @param pages 页数
	 * @return 当前页的供应商对象集合
	 */
	@Override
	public List<SupplierInformation> findByPage(int rows, int pages) {
		//定义一个供应商集合，用来保存当前页的供应商对象
		List<SupplierInformation> supplierInformationList=new ArrayList<SupplierInformation>();
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
			String sql="select top (?) * from supplierInformation"
					+ " where suppliernumber not in("
					+ "select top (?) suppliernumber from supplierInformation)";
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
				//定义供应商对象,用于将结果集中的一行数据保存到供应商对象中
				SupplierInformation supplierInformation=new SupplierInformation();
				//将供应商编号保存到供应商对象中
				supplierInformation.setSupplierNumber(rs.getInt("supplierNumber"));
				//将供应商保存到供应商对象中
				supplierInformation.setSupplier(rs.getString("Supplier"));
				//将联系电话保存到供应商对象中
				supplierInformation.setTelephoneNumbers(rs.getString("telephoneNumbers"));
				//将联系地址保存到供应商对象中
				supplierInformation.setAddress(rs.getString("address"));
				//将联系人保存到供应商对象
				supplierInformation.setContacts(rs.getString("contacts"));
				//将当前供应商对象添加到集合中
				supplierInformationList.add(supplierInformation);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回分页结果
		return supplierInformationList;
	}

	/**
	 * 统计记录数
	 */
	@Override
	public int supplierInformationCount() {
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
			String sql="select count(*) as supplierInformationCount from supplierInformation";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			
			//定义一个变量，用于从结果集中获取行数
			int rowCount=0;
			if(rs.next()){
				//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
				rowCount=rs.getInt("supplierInformationCount");
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
