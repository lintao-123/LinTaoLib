package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.ProcureInfoDao;
import com.yidu.t235.xm.entity.ProcureInfo;
import com.yidu.t235.xm.utils.JdbcUtils;

/**
 * 采购订单表实项类
 * 
 * @author Administrator
 *
 */
public class ProcureInfoDaoImpl implements ProcureInfoDao {
	/**
	 * 将采购订单对象添加到数据库表中
	 * 
	 * @param procureInfo
	 *            采购订单对象
	 * @return返回大于1，表示添加成功，返回0则添加失败
	 */
	@Override
	public int add(ProcureInfo procureInfo) {
		//声明数据库连接对象
		Connection conn = null;
		//声明预编译语句对象
		PreparedStatement pstmt = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql = "insert into  procureInfo values(?,?,?) ";
			//实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			/*设置SQL语句参数*/
			//设置采购订单号
			pstmt.setString(1, procureInfo.getProcurementNo());
			//设置供应商
			pstmt.setString(2, procureInfo.getSupplier());
			//设置采购日期
			pstmt.setString(3, procureInfo.getLibraryTime());
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//关闭数据库连接和释放资源
			JdbcUtils.close(null, pstmt, conn);
		}

	}

	/**
	 * 按编号从数据库表中删除
	 * 
	 * @param tempId
	 *            编号
	 * @return 返回大于1，表示删除成功，返回0则删除失败
	 */
	@Override
	public int delete(int tempId) {
		//声明数据库连接对象
		Connection conn = null;
		//声明预编译语句对象
		PreparedStatement pstmt = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql = "delete from  procureInfo where tempId=? ";
			//实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			/*设置SQL语句中的参数*/
			//设置编号
			pstmt.setInt(1, tempId);
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//关闭数据库连接和释放资源
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	/**
	 * 将采购订单对象修改到数据库
	 * 
	 * @param procureInfo
	 *            采购订单对象
	 * @return 返回大于1，表示修改成功，返回0则修改失败
	 */
	@Override
	public int update(ProcureInfo procureInfo) {
		//声明数据库连接对象
		Connection conn = null;
		//声明预编译语句对象
		PreparedStatement pstmt = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql = "update   procureInfo set procurementNo=?, supplier=?, " 
						+ " libraryTime=? where tempId=? ";
			//实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			/*设置SQL语句参数*/
			//设置采购订单号
			pstmt.setString(1, procureInfo.getProcurementNo());
			//设置供应商
			pstmt.setString(2, procureInfo.getSupplier());
			//设置采购时间
			pstmt.setString(3, procureInfo.getLibraryTime());
			//设置编号
			pstmt.setInt(4, procureInfo.getTempId());
			//将执行后，在数据库表中影响的行数作为方法值返回
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//关闭数据库连接和释放资源
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	/**
	 * 从数据库表中查找指定页
	 * 
	 * @param rows
	 *            行数
	 * @param page
	 *            页数
	 * @return 返回集合对象
	 */
	@Override
	public List<ProcureInfo> findPage(int rows, int page) {
		//定义一个采购订单对象集合,用来保存当前页对象
		List<ProcureInfo> procureInfosList = new ArrayList<ProcureInfo>();
		//声明数据库连接对象
		Connection conn = null;
		//声明预编译语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs = null;
		try {
			//实例化数据库连接对象
			conn = JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql = "select top(?) * from procureInfo  "  
						+" where tempId not in( "
						+ " select top(?) tempId from procureInfo) ";
			//实例化预编译语句对象
			pstmt = conn.prepareStatement(sql);
			/*设置SQL语句参数*/
			//设置行数
			pstmt.setInt(1, rows);
			//设置行数*页数-1
			pstmt.setInt(2, rows * (page - 1));
			//执行SQL语句，返回结果集对象
			rs = pstmt.executeQuery();
			//使用循环,对结果集中的数据进行处理
			while (rs.next()) {
				//定义采购订单对象，用于对结果集中的一行数据保存到订单对象中
				ProcureInfo procureInfo = new ProcureInfo();
				//将编号保存到对象中
				procureInfo.setTempId(rs.getInt("tempId"));
				//将采购订单号保存到对象中
				procureInfo.setProcurementNo(rs.getString("procurementNo"));
				//将供应商保存到对象中
				procureInfo.setSupplier(rs.getString("supplier"));
				//将采购时间保存到对象中
				procureInfo.setLibraryTime(rs.getString("libraryTime"));
				//将当前采购订单对象添加到集合中
				procureInfosList.add(procureInfo);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//关闭数据库连接和释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回分页结果
		return procureInfosList;
	}

	/**
	 * 统计数据库表记录数
	 * 
	 * @return 返回记录数
	 */

	@Override
	public int getCount() {
		//声明数据库连接对象
		Connection conn = null;
		//声明预编译语句对象
		PreparedStatement pstmt = null;
		//声明结果集对象
		ResultSet rs=null;
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="select count(*)as procureInfos from procureInfo";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//定义一个变量，用于从结果集中获取行数
			int rowCount=0;
			if (rs.next()) {
				//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
				rowCount=rs.getInt("procureInfos");
			}
			//返回方法值:统计记录数
			return rowCount;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//关闭数据库连接和释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

}
