package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.MemberLevelDao;
import com.yidu.t235.xm.entity.MemberLevel;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * 数据层对数据库进行操作
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:51:44
 */
public class MemberLevelDaoImpl implements MemberLevelDao {
	/**
	 * 将会员等级对象添加到数据库memberLevel表中
	 * @param memberLevel 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	@Override
	public int add(MemberLevel memberLevel) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="insert into memberLevel values(?,?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1,memberLevel.getLevelName());
			pstmt.setDouble(2,memberLevel.getMemberDiscount());
			pstmt.setString(3, memberLevel.getUpgradeIntegral());
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
	 * 根据等级编号从数据库表memberLevel中删除会员信息
	 * @param levelNumber 等级编号
	 * @return 成功删除的记录数(非0/大于0:表示删除成功，0：删除失败)
	 */
	@Override
	public int delete(int levelNumber) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="delete from memberLevel where levelNumber=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setInt(1, levelNumber);
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
	 * 将会员对象修改到数据库表
	 * @param memberLevel 会员对象
	 * @return 修改成功的记录数(1:表示修改成功,0:修改失败)
	 */
	@Override
	public int update(MemberLevel memberLevel) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="update memberLevel set levelName=?,memberDiscount=?,"
					+ "upgradeIntegral=? "
					+ "where levelNumber=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1,memberLevel.getLevelName());
			pstmt.setDouble(2,memberLevel.getMemberDiscount());
			pstmt.setString(3, memberLevel.getUpgradeIntegral());
			pstmt.setInt(4, memberLevel.getLevelNumber());
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
	 * 从数据库表中查找指定页的等级对象
	 * @param rows 每页的记录数
	 * @param pages 页数
	 * @return 当前页的等级对象集合
	 */
	@Override
	public List<MemberLevel> findByPage(int rows, int pages) {
		//定义一个等级集合，用来保存当前页的等级对象
		List<MemberLevel> memberLevelList=new ArrayList<MemberLevel>();
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
			String sql="select top (?) * from memberLevel"
					+ " where levelNumber not in("
					+ "select top (?) levelNumber from memberLevel)";
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
				//定义等级对象,用于将结果集中的一行数据保存到等级对象中
				MemberLevel memberLevel=new MemberLevel();
				//将等级编号保存到等级对象中
				memberLevel.setLevelNumber(rs.getInt("levelNumber"));
				//将等级名称保存到等级对象中
				memberLevel.setLevelName(rs.getString("levelName"));
				//将会员折扣保存到等级对象
				memberLevel.setMemberDiscount(rs.getDouble("memberDiscount"));
				//将升级积分保存到等级对象
				memberLevel.setUpgradeIntegral(rs.getString("upgradeIntegral"));
				//将当前等级对象添加到集合中
				memberLevelList.add(memberLevel);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回分页结果
		return memberLevelList;
	}

	/**
	 * 统计记录数
	 */
	@Override
	public int memberLevelCount() {
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
			String sql="select count(*) as memberLevelCount from memberLevel";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			
			//定义一个变量，用于从结果集中获取行数
			int rowCount=0;
			if(rs.next()){
				//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
				rowCount=rs.getInt("memberLevelCount");
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
