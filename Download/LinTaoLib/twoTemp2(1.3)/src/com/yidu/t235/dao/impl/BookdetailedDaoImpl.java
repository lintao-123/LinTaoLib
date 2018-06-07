package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.dao.BookdetailedDao;
import com.yidu.t235.entity.Bookdetailed;
import com.yidu.t235.utils.JdbcUtils;

/**
 * 数据层对数据库进行操作
 * @author 肖云飞
 * 日期：2018-3-09 
 */

public class BookdetailedDaoImpl implements  BookdetailedDao{

	/**
     * 将销售订单实体对象添加到数据库bookdetailed表中
     * @param bookdetailed实体对象
     * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
     */
	
	@Override
	public int add(Bookdetailed bookdetailed) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="insert into bookdetailed values(?,?,?,?)";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setString(1, bookdetailed.getOrdernumber());
					pstmt.setString(2, bookdetailed.getBookname());
					pstmt.setString(3, bookdetailed.getBookprice());
					pstmt.setString(4, bookdetailed.getSalesman());
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
     * 将销售订单实体对象删除到数据库bookdetailed表中
     * @param bookdetailed实体对象
     * @return 成功删除的行数(非0/大于0:表示删除成功，0：删除失败)
     */
	@Override
	public int delete(int bookno) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="delete from bookdetailed where bookno=?";
					//实例化预编译语句
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setInt(1, bookno);
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
     * 将销售对象修改到数据库表
     * @param bookdetailed 销售对象
     * @return 修改成功的记录数(1:表示修改成功,0:修改失败)
     */
	@Override
	public int update(Bookdetailed bookdetailed) {
		        //声明数据库连接对象
				Connection conn=null;
				//声明预编译语句对象
				PreparedStatement pstmt=null;
				
				try {
					//实例化数据库连接对象
					conn=JdbcUtils.getConnectoin();
					//编写sql语句
					String sql="update bookdetailed set ordernumber=?,bookname=?,bookprice=?,salesman=? where bookno=?";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//设置预编译语句参数
					pstmt.setString(1, bookdetailed.getOrdernumber());
					pstmt.setString(2, bookdetailed.getBookname());
					pstmt.setString(3, bookdetailed.getBookprice());
					pstmt.setString(4, bookdetailed.getSalesman());
					pstmt.setInt(5, bookdetailed.getBookno());
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
     * 从数据库表中查找指定页的销售对象
     * @param rows 每页的记录数
     * @param pages 页数
     * @return 当前页的图书对象集合
     */
	@Override
	public List<Bookdetailed> findByPage(int rows, int page) {
		        //定义一个部门集合，用来保存当前页的部门信息
				List<Bookdetailed> bookdetailedList=new ArrayList<Bookdetailed>();
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
					String sql="select top (?) * from bookdetailed "
							+ " where bookno not in( "
							+ " select top (?) bookno from bookdetailed)";
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
						Bookdetailed bookdetailed=new Bookdetailed();
						bookdetailed.setBookno(rs.getInt("bookno"));
						bookdetailed.setOrdernumber(rs.getString("ordernumber"));
						bookdetailed.setBookname(rs.getString("bookname"));
						bookdetailed.setBookprice(rs.getString("bookprice"));
						bookdetailed.setSalesman (rs.getString("salesman"));
						
						bookdetailedList.add(bookdetailed);
					}
					
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//关闭/释放资源
					JdbcUtils.close(rs, pstmt, conn);
				}
				//返回分页结果
				return bookdetailedList;

			}
	/**
	 * 统计记录数
	 * @return 记录数
	 */
	@Override
	public int getBookdetailedCount() {
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
					String sql="select count(*) as bookdetailedcount from bookdetailed";
					//实例化预编译语句对象
					pstmt=conn.prepareStatement(sql);
					//执行SQL语句，返回结果集对象
					rs=pstmt.executeQuery();
					//处理结果集对象:将结果集中的内容保存到对象中 
					
					//定义一个变量，用于从结果集中获取行数
					int rowCount=0;
					if(rs.next()){
						//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
						rowCount=rs.getInt("bookdetailedcount");
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