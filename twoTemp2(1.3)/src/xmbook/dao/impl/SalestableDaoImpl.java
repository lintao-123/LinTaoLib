package xmbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xmbook.dao.SalestableDao;
import xmbook.entity.Salestable;
import xmbook.utils.JdbcUtils;

public class SalestableDaoImpl implements SalestableDao {
	/**
	 * 将销售实体对象添加到数据库salestable表中
	 * @param salestable 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	@Override
	public int add(Salestable salestable) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//编写SQL语句
			String sql="insert into salestable values(?,?,?,?,?,?,?)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1, salestable.getOrdernumber());
			pstmt.setString(2,salestable.getBookname());
			pstmt.setInt(3, salestable.getSalesnumber());
			pstmt.setDouble(4, salestable.getShouldprice());
			pstmt.setDouble(5, salestable.getRealityprice());
			pstmt.setString(6, salestable.getSalestime());
			pstmt.setString(7, salestable.getSaleser());
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
	 * 根据销售编号从数据库表salestable中删除销售
	 * @param bookno 销售编号
	 * @return 成功删除的记录数(非0/大于0:表示删除成功，0：删除失败)
	 */
	@Override
	public int delete(int bookno) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//编写SQL语句
			String sql="delete from salestable where bookno=?;";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setInt(1, bookno);
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
	 * 将销售对象修改到数据库表
	 * @param salestable 销售对象
	 * @return 修改成功的记录数(1:表示修改成功,0:修改失败)
	 */
	@Override
	public int update(Salestable salestable) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//编写SQL语句
			String sql="update salestable set ordernumber=?,bookname=?,salesnumber=?,"
					+ "shouldprice=?,realityprice=?,salestime=?,saleser=? "
					+ "where bookno=?";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置SQL语句中的参数
			pstmt.setString(1,salestable.getOrdernumber());
			pstmt.setString(2, salestable.getBookname());
			pstmt.setInt(3, salestable.getSalesnumber());
			pstmt.setDouble(4,salestable.getShouldprice());
			pstmt.setDouble(5, salestable.getRealityprice());
			pstmt.setString(6, salestable.getSalestime());
			pstmt.setString(7, salestable.getSaleser());
			pstmt.setInt(8, salestable.getBookno());
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
	 * 从数据库表中查找指定页的销售对象
	 * @param rows 每页的记录数
	 * @param pages 页数
	 * @return 当前页的销售对象集合
	 */
	@Override
	public List<Salestable> findByPage(int rows, int pages) {
		//定义一个销售集合，用来保存当前页的销售对象
		List<Salestable> salestableList=new ArrayList<Salestable>();
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		
		try {
			//实例化连接对象
			conn=JdbcUtils.getConnection();
			//编写执行的SQL语句
			String sql="select top (?) * from salestable "
					+ "where bookno not in("
					+ "select top (?) bookno from salestable)";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//给参数赋值
			pstmt.setInt(1, rows);
			pstmt.setInt(2, (pages-1)*rows);			
			//执行分页查询的sql语句，将结果保存到结果集对象中
			rs=pstmt.executeQuery();
			//使用循环，对结果集中数据进行处理
			while(rs.next()){
				//定义销售对象,用于将结果集中的一行数据保存到销售对象中
				Salestable salestable=new Salestable();
				//将销售编号保存到对象中
				salestable.setBookno(rs.getInt("bookno"));
				//将销售订单号 保存到对象中
				salestable.setOrdernumber(rs.getString("ordernumber"));
				//将图书名称保存到销售对象中
				salestable.setBookname(rs.getString("bookname"));
				//将销售数量保存到销售对象中
				salestable.setSalesnumber(rs.getInt("salesnumber"));
				//将应收价格保存到销售对象中
				salestable.setShouldprice(rs.getDouble("shouldprice"));
				//将实收价格保存到销售对象
				salestable.setRealityprice(rs.getDouble("realityprice"));
				//将销售日期保存到销售对象中
				salestable.setSalestime(rs.getString("salestime"));
				//将销售员保存到销售对象中
				salestable.setSaleser(rs.getString("saleser"));
				//将当前销售对象添加到集合中
				salestableList.add(salestable);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源
			JdbcUtils.close(rs, pstmt, conn);
		}
		//返回分页结果
		return salestableList;
	}

	@Override
	public int salestableCount() {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//编写SQL语句
			String sql="select count(*) as salestablecount from salestable";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//处理结果集对象:将结果集中的内容保存到对象中 
			
			//定义一个变量，用于从结果集中获取行数
			int rowCount=0;
			if(rs.next()){
				//此处用统计列别名获得行数，如果没有指定别名，则用数字0表示
				rowCount=rs.getInt("salestablecount");
			}
			//返回方法值：销售对象
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}
	/**
	 * 从数据库表中根据图书名称查询图书数量
	 * @param bookname 图书名称
	 * @return 返回销售对象 
	 */
	@Override
	public Salestable findName(String bookname) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//编写SQL语句
			String sql="select *   from salestable where bookname=? ";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, bookname);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//声明一个空对象
			Salestable	salestable=null;
			//处理结果集对象:将结果集中的内容保存到对象中 
			if(rs.next()){
				salestable=new Salestable();
				//将销售编号保存到对象中
				salestable.setBookno(rs.getInt("bookno"));
				//将销售订单号 保存到对象中
				salestable.setOrdernumber(rs.getString("ordernumber"));
				//将图书名称保存到销售对象中
				salestable.setBookname(rs.getString("bookname"));
				//将销售数量保存到销售对象中
				salestable.setSalesnumber(rs.getInt("salesnumber"));
				//将应收价格保存到销售对象中
				salestable.setShouldprice(rs.getDouble("shouldprice"));
				//将实收价格保存到销售对象
				salestable.setRealityprice(rs.getDouble("realityprice"));
				//将销售日期保存到销售对象中
				salestable.setSalestime(rs.getString("salestime"));
				//将销售员保存到销售对象中
				salestable.setSaleser(rs.getString("saleser"));
			}
			//返回方法值：销售对象
			return salestable;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

	@Override
	public Salestable findId(int bookno) {
		//声明数据库连接对象
		Connection conn=null;
		//声明预编译语句对象
		PreparedStatement pstmt=null;
		//声明结果集对象
		ResultSet rs=null;
		
		try {
			//实例化数据库连接对象
			conn=JdbcUtils.getConnection();
			//编写SQL语句
			String sql="select *   from salestable where bookno=? ";
			//实例化预编译语句对象
			pstmt=conn.prepareStatement(sql);
			//设置参数
			pstmt.setInt(1, bookno);
			//执行SQL语句，返回结果集对象
			rs=pstmt.executeQuery();
			//声明一个空对象
			Salestable	salestable=null;
			//处理结果集对象:将结果集中的内容保存到对象中 
			if(rs.next()){
				salestable=new Salestable();
				//将销售编号保存到对象中
				salestable.setBookno(rs.getInt("bookno"));
				//将销售订单号 保存到对象中
				salestable.setOrdernumber(rs.getString("ordernumber"));
				//将图书名称保存到销售对象中
				salestable.setBookname(rs.getString("bookname"));
				//将销售数量保存到销售对象中
				salestable.setSalesnumber(rs.getInt("salesnumber"));
				//将应收价格保存到销售对象中
				salestable.setShouldprice(rs.getDouble("shouldprice"));
				//将实收价格保存到销售对象
				salestable.setRealityprice(rs.getDouble("realityprice"));
				//将销售日期保存到销售对象中
				salestable.setSalestime(rs.getString("salestime"));
				//将销售员保存到销售对象中
				salestable.setSaleser(rs.getString("saleser"));
			}
			//返回方法值：销售对象
			return salestable;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//关闭/释放资源(结果集对象，语句对象，连接对象)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}
}
