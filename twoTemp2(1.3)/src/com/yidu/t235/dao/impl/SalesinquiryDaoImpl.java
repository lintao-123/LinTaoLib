package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.dao.SalesinquiryDao;
import com.yidu.t235.entity.Salesinquiry;
import com.yidu.t235.utils.JdbcUtils;
/**
 * 数据层对数据库进行操作
 * @author 肖云飞
 * 日期：2018-3-09
 */
public class SalesinquiryDaoImpl implements SalesinquiryDao{
    /**
     * 将销售订单详情实体对象添加到数据库salesinquiry表中
     * @param salesinquiry实体对象
     * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
     */
	@Override
	public int add(Salesinquiry salesinquiry) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="insert into salesinquiry values(?,?,?,?,? )";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setString(1, salesinquiry.getOrdernumberid());
					pstmt.setString(2, salesinquiry.getBookname());
					pstmt.setString(3, salesinquiry.getBooktype());
					pstmt.setDouble(4, salesinquiry.getBookprice());
					pstmt.setInt(5, salesinquiry.getSalesvolume());
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

	/**
     * 根据销售编号从数据库表salesinquiry中删除图书
     * @param ordernumber 销售订单详情编号
     * @return 成功删除的记录数(非0/大于0:表示删除成功，0：删除失败)
     */
	@Override
	public int delete(int ordernumber) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="delete from salesinquiry where ordernumber=?";
					//实例化预编译语句
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setInt(1, ordernumber);
					//执行SQL操作
					//将执行后，在数据库表中影响的行数作为方法值返回
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					JdbcUtils.close(null, pstmt, conn);
				}
			}

	/**
     * 将销售订单详情编号对象修改到数据库表中
     * @param salesinquiry 编号对象
     *  @return 修改成功的记录数(1:表示修改成功,0:修改失败)
     */
	@Override
	public int update(Salesinquiry salesinquiry) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="update salesinquiry set ordernumberid=?,bookname=?,booktype=?,"
							+ "bookprice=?,salesvolume=?  where ordernumber=?";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setString(1, salesinquiry.getOrdernumberid());
					pstmt.setString(2, salesinquiry.getBookname());
					pstmt.setString(3, salesinquiry.getBooktype());
					pstmt.setDouble(4, salesinquiry.getBookprice());
					pstmt.setInt(5, salesinquiry.getSalesvolume());
					pstmt.setInt(6, salesinquiry.getOrdernumber());
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
	/**
     * 从数据库表中查找指定页的图书对象
     * @param rows 每页的记录数
     * @param pages页数
     * @return 当前页的图书对象集合
     */
	@Override
	public List<Salesinquiry> findByPage(int rows, int page) {
		        //定义一个部门集合，用来保存当前页的部门信息
				List<Salesinquiry> salesinquiryList=new ArrayList<Salesinquiry>();
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
					String sql="select top (?) * from salesinquiry "
							+ "where ordernumber not in("
							+ "select top (?) ordernumber from salesinquiry)";
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
						Salesinquiry salesinquiry=new Salesinquiry();
						salesinquiry.setOrdernumber(rs.getInt("ordernumber"));
						salesinquiry.setOrdernumberid(rs.getString("ordernumberid"));
						salesinquiry.setBookname(rs.getString("bookname"));
						salesinquiry.setBooktype(rs.getString("booktype"));
						salesinquiry.setBookprice(rs.getDouble("bookprice"));
						salesinquiry.setSalesvolume(rs.getInt("salesvolume"));
						salesinquiry.setTotal(salesinquiry.getTotal());
	
						salesinquiryList.add(salesinquiry);
					}
					
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//关闭/释放资源
					JdbcUtils.close(rs, pstmt, conn);
				}
				//返回分页结果
				return salesinquiryList;

			}
	/**
	 * 统计记录数
	 * @return 记录数
	 */
	@Override
	public int getSalesinquiryCount() {
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
					String sql="select count(*) as salesinquirycount from salesinquiry";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//执行SQL语句，返回结果集对象
					rs=pstmt.executeQuery();
					//处理结果集对象:将结果集中的内容保存到对象中 
					
					//定义一个变量，用于从结果集中获取行数
					int rowCount=0;
					if(rs.next()){
						//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
						rowCount=rs.getInt("salesinquirycount");
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