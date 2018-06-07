package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.MemberInfoDao;
import com.yidu.t235.xm.entity.MemberInfo;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * 数据层对数据库进行操作
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:48:25
 */
public class MemberInfoDaoImpl implements MemberInfoDao {
	/**
	 * 将会员信息对象添加到数据库memberInfo表中
	 * @param memberInfo 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	@Override
	public int add(MemberInfo memberInfo) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="insert into memberInfo values(?,?,?,?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1,memberInfo.getMemberName());
			pstmt.setString(2,memberInfo.getMemberSex());
			pstmt.setString(3, memberInfo.getMemberTelephone());
			pstmt.setString(4, memberInfo.getMemberLevel());
			pstmt.setDouble(5, memberInfo.getMemberIntegral());
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
	 * 根据会员编号从数据库表memberInfo中删除信息
	 * @param memberId 会员编号
	 * @return 成功删除的记录数(非0/大于0:表示删除成功，0：删除失败)
	 */
	@Override
	public int delete(int memberId) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="delete from memberInfo where memberId=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setInt(1, memberId);
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
	 * @param memberInfo 会员对象
	 * @return 修改成功的记录数(1:表示修改成功,0:修改失败)
	 */
	@Override
	public int update(MemberInfo memberInfo) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnectoin();
			//编写SQL语句
			String sql="update memberInfo set memberName=?,memberSex=?,"
					+ "memberTelephone=?,memberLevel=?,memberIntegral=? "
					+ "where memberId=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1,memberInfo.getMemberName());
			pstmt.setString(2,memberInfo.getMemberSex());
			pstmt.setString(3, memberInfo.getMemberTelephone());
			pstmt.setString(4, memberInfo.getMemberLevel());
			pstmt.setDouble(5, memberInfo.getMemberIntegral());
			pstmt.setInt(6, memberInfo.getMemberId());
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
	 * 根据会员编号从数据库表中查找会员
	 * @param memberId 会员编号
	 * @return 查找成功的会员对象(如果返回null对象，表示查找失败)
	 */
	@Override
	public MemberInfo findById(int memberId) {
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
			String sql="select * from memberInfo where memberId=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置sql语句中的参数
			pstmt.setInt(1, memberId);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			//声明一个会员对象
			MemberInfo memberInfo=null;
			if(rs.next()){
				//实例化会员对象,用于将结果集中的数据保存到会员对象中
				memberInfo=new MemberInfo();
				//将会员编号保存到会员对象中
				memberInfo.setMemberId(rs.getInt("memberId"));
				//将会员姓名保存到会员对象中
				memberInfo.setMemberName(rs.getString("memberName"));
				//将会员性别保存到会员对象
				memberInfo.setMemberSex(rs.getString("memberSex"));
				//将会员电话到会员对象
				memberInfo.setMemberTelephone(rs.getString("memberTelephone"));
				//将会员等级保存到会员对象中
				memberInfo.setMemberLevel(rs.getString("memberLevel"));
				//将会员积分保存到会员对象
				memberInfo.setMemberIntegral(rs.getDouble("memberIntegral"));
			}
			//返回方法值：会员对象
			return memberInfo;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

	/**
	 * 从数据库表中查找指定页的会员对象
	 * @param rows 每页的记录数
	 * @param pages 页数
	 * @return 当前页的会员对象集合
	 */
	@Override
	public List<MemberInfo> findByPage(int rows, int pages) {
		//定义一个会员集合，用来保存当前页的会员对象
		List<MemberInfo> memberInfoList=new ArrayList<MemberInfo>();
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
			String sql="select top (?) * from memberInfo"
					+ " where memberId not in("
					+ "select top (?) memberId from memberInfo)";
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
				//定义会员对象,用于将结果集中的一行数据保存到会员对象中
				MemberInfo memberInfo=new MemberInfo();
				//将会员编号保存到会员对象中
				memberInfo.setMemberId(rs.getInt("memberId"));
				//将会员姓名保存到会员对象中
				memberInfo.setMemberName(rs.getString("memberName"));
				//将会员性别保存到会员对象
				memberInfo.setMemberSex(rs.getString("memberSex"));
				//将会员电话到会员对象
				memberInfo.setMemberTelephone(rs.getString("memberTelephone"));
				//将会员等级保存到会员对象中
				memberInfo.setMemberLevel(rs.getString("memberLevel"));
				//将会员积分保存到会员对象
				memberInfo.setMemberIntegral(rs.getDouble("memberIntegral"));
				//将当前会员对象添加到集合中
				memberInfoList.add(memberInfo);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回分页结果
		return memberInfoList;
	}

	/**
	 * 统计记录数
	 */
	@Override
	public int memberInfoCount() {
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
			String sql="select count(*) as memberInfoCount from memberInfo";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			
			//定义一个变量，用于从结果集中获取行数
			int rowCount=0;
			if(rs.next()){
				//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
				rowCount=rs.getInt("memberInfoCount");
			}
			//返回方法值：会员对象
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}
}
