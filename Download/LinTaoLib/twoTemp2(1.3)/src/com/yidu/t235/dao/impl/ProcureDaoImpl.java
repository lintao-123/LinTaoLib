package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.dao.ProcureDao;
import com.yidu.t235.entity.Procure;
import com.yidu.t235.utils.JdbcUtils;

public class ProcureDaoImpl implements ProcureDao{

	@Override
	public int add(Procure procure) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дsql���
			String sql="insert into procure values(?,?,?,?,?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����Ԥ����������
			pstmt.setString(1, procure.getOrdernumber());
			pstmt.setString(2, procure.getBookname());
			pstmt.setInt(3, procure.getSalesvolume());
			pstmt.setDouble(4, procure.getBid());
			pstmt.setString(5, procure.getSalesperiods());
			pstmt.setString(6, procure.getOperator());
			//ִ��SQL����
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//�ر����ݿ�����
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	@Override
	public int delete(int bookid) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дsql���
			String sql="delete from procure where bookid=?";
			//ʵ����Ԥ�������
			pstmt=conn.prepareStatement(sql);
			//����Ԥ����������
			pstmt.setInt(1, bookid);
			//ִ��SQL����
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			//�ر����ݿ�����
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	@Override
	public int update(Procure procure) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дsql���
			String sql="update procure set ordernumber=?,bookname=?,salesvolume=?,"
					+ "bid=? ,salesperiods=?,operator=? where bookid=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����Ԥ����������
			pstmt.setString(1, procure.getOrdernumber());
			pstmt.setString(2, procure.getBookname());
			pstmt.setInt(3, procure.getSalesvolume());
			pstmt.setDouble(4, procure.getBid());
			pstmt.setString(5, procure.getSalesperiods());
			pstmt.setString(6, procure.getOperator());
			pstmt.setInt(7, procure.getBookid());
			//ִ��SQL����
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			//�ر����ݿ�����
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	@Override
	public List<Procure> findByPage(int rows, int page) {
		//����һ�����ż��ϣ��������浱ǰҳ�Ĳ�����Ϣ
		List<Procure> procureList=new ArrayList<Procure>();
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		
		try {
			//ʵ�������Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дִ�е�SQL���
			String sql="select top (?) * from procure"
			+" where bookid not in("
			+"select top (?) bookid from procure)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��������ֵ
			pstmt.setInt(1, rows);
			pstmt.setInt(2, (page-1)*rows);
			
			//System.out.println("SQL:"+sql);
			//ִ�з�ҳ��ѯ��sql��䣬��������浽�����������
			rs=pstmt.executeQuery();
			//ʹ��ѭ�����Խ���������ݽ��д���
			while(rs.next()){
				//���岿�Ŷ���,���ڽ�������е�һ�����ݱ��浽���Ŷ�����
				Procure procure=new Procure();
				procure.setBookid(rs.getInt("bookid"));
				procure.setOrdernumber(rs.getString("ordernumber"));
				procure.setBookname(rs.getString("bookname"));
				procure.setSalesvolume(rs.getInt("salesvolume"));
				procure.setBid(rs.getDouble("bid"));
				procure.setTotal(procure.getTotal()); 
				procure.setSalesperiods(rs.getString("salesperiods"));
				procure.setOperator(rs.getString("operator"));
				
				procureList.add(procure);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			//�ر�/�ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ط�ҳ���
		return procureList;
	}

	@Override
	public int getProcureCount() {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="select count(*) as procurecount from procure";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			
			//����һ�����������ڴӽ�����л�ȡ����
			int rowCount=0;
			if(rs.next()){
			  //�˴���ͳ���б���������������û��ָ����������������0��ʾ
				rowCount=rs.getInt("procurecount");
			}
			//���ط���ֵ��Ա������
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

}
