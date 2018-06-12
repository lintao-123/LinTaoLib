package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.OperatorsDao;
import com.yidu.t235.xm.entity.Operators;
import com.yidu.t235.xm.utils.JdbcUtils;

public class OperatorsDaoImpl implements OperatorsDao {

	@Override
	public int add(Operators operators) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="insert into operators values( ?,?,?,?,?) ";
			pstmt=conn.prepareStatement(sql);
			 
			pstmt.setString(1, operators.getName());
			 
			pstmt.setString(2, operators.getAddress());
			pstmt.setString(3, operators.getTelephoneNumber());
			pstmt.setString(4, operators.getEmpType());
			pstmt.setDouble(5, operators.getEmpMoney());
			 
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	@Override
	public int delete(int userId) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="delete from operators where userId=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
				
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	@Override
	public int update(Operators operators) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="update operators set  name=?,"
					+ "  address=?,telephoneNumber=?, "
					+ "  empType=?, empMoney=? where userId=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, operators.getName());
			 
			pstmt.setString(2, operators.getAddress());
			pstmt.setString(3, operators.getTelephoneNumber());
			pstmt.setString(4, operators.getEmpType());
			pstmt.setDouble(5, operators.getEmpMoney());
			pstmt.setInt(6, operators.getUserId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	@Override
	public List<Operators> findPage(int rows, int page) {
		List<Operators> operatorsList=new ArrayList<Operators>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="select top(?)* from operators "
					+ " where userId not in(  "
					+ " select top(?) userId from operators) ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rows);
			pstmt.setInt(2, rows*(page-1));
			rs=pstmt.executeQuery();
			while(rs.next()){
				Operators operators=new Operators();
				operators.setUserId(rs.getInt("userId"));
				 
				operators.setName(rs.getString("name"));
			 
				operators.setAddress(rs.getString("address"));
				operators.setTelephoneNumber(rs.getString("telephoneNumber"));
				operators.setEmpType (rs.getString("empType"));
				operators.setEmpMoney(rs.getDouble("empMoney"));
				operatorsList.add(operators);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		return operatorsList;
	}

	@Override
	public int getCount() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="select count(*) as operatorsInfo from operators ";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int rowCount=0;
			if(rs.next()){
				rowCount=rs.getInt("operatorsInfo");
			}
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

}
