package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.ReturnInquiryDao;
import com.yidu.t235.xm.entity.ReturnInquiry;
import com.yidu.t235.xm.utils.JdbcUtils;

public class ReturnInquiryDaoImpl implements ReturnInquiryDao{
	/**
	 * ����˻�����
	 * @param returnInquiry �˻�����
	 * @return �����������
	 */
	@Override
	public int add(ReturnInquiry returnInquiry) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="insert into returnInquiry values(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			 
			pstmt.setString(1, returnInquiry.getOrderNumber());
			pstmt.setString(2, returnInquiry.getBookName());
			
			pstmt.setDouble(3, returnInquiry.getBookPrice());
			pstmt.setInt(4, returnInquiry.getSalesVolume());
			pstmt.setString(5, returnInquiry.getSalesPeriods());
			pstmt.setString(6, returnInquiry.getConsoleOperator());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
	}
	/**
	 * ͨ������ɾ���˻�����
	 * @param orderNumber �˻����ţ�������
	 * @return ����ɾ������
	 */
	@Override
	public int delete(int tempId) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="delete from returnInquiry where tempId=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, tempId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
	}
	/**
	 * �޸��˻�����
	 * @param returnInquiry �˻�����
	 * @return �����޸�����
	 */
	@Override
	public int update(ReturnInquiry returnInquiry) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="update returnInquiry set orderNumber=?, bookName=?,"
					+ "  bookPrice=?,salesVolume=?, "
					+ " salesPeriods=?,consoleOperator=? "
					+ "where tempId=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, returnInquiry.getOrderNumber());
			pstmt.setString(2, returnInquiry.getBookName());
			pstmt.setDouble(3, returnInquiry.getBookPrice());
			pstmt.setInt(4, returnInquiry.getSalesVolume());
			pstmt.setString(5, returnInquiry.getSalesPeriods());
			pstmt.setString(6, returnInquiry.getConsoleOperator());
			pstmt.setInt(7, returnInquiry.getTempId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
	}
	/**
	 * ��ʾ��ҳ
	 * @param rows ����
	 * @param page ҳ��
	 * @return ���ؼ��϶���
	 */
	@Override
	public List<ReturnInquiry> findPage(int rows, int page) {
		List<ReturnInquiry> returnInquiriesList=new ArrayList<ReturnInquiry>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="select top(?) * from returnInquiry "
					+ " where orderNumber not in ( "
					+ " select top(?) orderNumber from returnInquiry ) ";
			pstmt=conn.prepareStatement(sql);
			 pstmt.setInt(1, rows);
			 pstmt.setInt(2, rows*(page-1));			
			rs= pstmt.executeQuery();
			while (rs.next()){
				ReturnInquiry returnInquiry=new ReturnInquiry();
				returnInquiry.setTempId (rs.getInt("tempId"));
				returnInquiry.setOrderNumber(rs.getString("orderNumber"));
				returnInquiry.setBookName(rs.getString("bookName"));
				returnInquiry.setBookPrice (rs.getDouble("bookPrice"));
				returnInquiry.setSalesVolume (rs.getInt("salesVolume"));
				returnInquiry.setSalesPeriods (rs.getString("salesPeriods"));
				returnInquiry.setConsoleOperator  (rs.getString("consoleOperator"));
				returnInquiry.setTotal(returnInquiry.getTotal());
				returnInquiriesList.add(returnInquiry);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		return returnInquiriesList;
	}
	/**
	 * �������������
	 * @return ����������
	 */
	@Override
	public int getCount() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="select count(*) as returnInquiryInfo from returnInquiry ";
			pstmt=conn.prepareStatement(sql);
			 int rowsCount=0;
			rs= pstmt.executeQuery();
			if(rs.next()){
				rowsCount=rs.getInt("returnInquiryInfo");
			}
			return rowsCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(null, pstmt, conn);
		}
	}
	/**
	 * ����Ų��Ҷ���
	 * @param tempId ���
	 * @return  ���ز��Ҷ���
	 */
	@Override
	public ReturnInquiry findById(int tempId) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtils.getConnectoin();
			String sql="select  * from returnInquiry  where tempId=? ";
			pstmt=conn.prepareStatement(sql);
			 pstmt.setInt(1, tempId);		 		
			rs= pstmt.executeQuery();
			ReturnInquiry returnInquiry=null;
			if (rs.next()){
				returnInquiry=new ReturnInquiry();
				returnInquiry.setTempId (rs.getInt("tempId"));
				returnInquiry.setOrderNumber(rs.getString("orderNumber"));
				returnInquiry.setBookName(rs.getString("bookName"));
				returnInquiry.setBookPrice (rs.getDouble("bookPrice"));
				returnInquiry.setSalesVolume (rs.getInt("salesVolume"));
				returnInquiry.setSalesPeriods (rs.getString("salesPeriods"));
				returnInquiry.setConsoleOperator  (rs.getString("consoleOperator"));
				returnInquiry.setTotal(returnInquiry.getTotal());
			}
			return returnInquiry;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			JdbcUtils.close(rs, pstmt, conn);
		}
		
	}
	 
}
