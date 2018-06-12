package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.StockCheckDao;
import com.yidu.t235.xm.entity.StockCheck;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * ���ݲ�����ݿ���в���
 * @author �����
 * ���ڣ�2018��3��8�� ����4:18:23
 */
public class StockCheckDaoImpl implements StockCheckDao {
	/**
	 * �����ʵ�������ӵ����ݿ�stockCheck����
	 * @param stockCheck ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	@Override
	public int add(StockCheck stockCheck) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="insert into StockCheck values(?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1,stockCheck.getBookName());
			pstmt.setInt(2, stockCheck.getStockNamber());
			//ִ��SQL����
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	/**
	 * ���������޸ĵ����ݿ��
	 * @param stockCheck ������
	 * @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
	 */
	@Override
	public int update(String bookName,int number) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="update stockCheck set stockNamber=stockNamber+(?) where bookName=(?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setInt(1, number);
			pstmt.setString(2, bookName);
			//ִ��SQL����
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	/**
	 * ����ͼ�����ƴ����ݿ���в���ͼ��
	 * @param bookName ͼ������
	 * @return ���ҳɹ���ͼ�����(�������null���󣬱�ʾ����ʧ��)
	 */
	@Override
	public StockCheck findByName(String bookName) {
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
			String sql="select * from stockCheck where bookName=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����sql����еĲ���
			pstmt.setString(1, bookName);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			//����һ��ͼ�����
			StockCheck stockCheck=null;
			if(rs.next()){
				//ʵ����ͼ�����,���ڽ�������е����ݱ��浽ͼ�������
				stockCheck=new StockCheck();
				//��ͼ���ű��浽ͼ�������
				stockCheck.setBookNo(rs.getInt("bookNo"));
				//��ͼ�����Ʊ��浽ͼ�������
				stockCheck.setBookName(rs.getString("bookName"));
				//���ɹ��������浽ͼ�������
				stockCheck.setStockNamber(rs.getInt("stockNamber"));
			}
			//���ط���ֵ��ͼ�����
			return stockCheck;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

	 
 
	/**
	 * �����ݿ���в���ָ��ҳ��ͼ�����
	 * @param rows ÿҳ�ļ�¼��
	 * @param pages ҳ��
	 * @return ��ǰҳ��ͼ����󼯺�
	 */
	@Override
	public List<StockCheck> findByPage(int rows, int pages) {
		//����һ��ͼ�鼯�ϣ��������浱ǰҳ��ͼ�����
		List<StockCheck> stockCheckList=new ArrayList<StockCheck>();
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
			String sql="select top (?) * from stockCheck"
					+ " where bookno not in("
					+ "select top (?) bookno from StockCheck)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��������ֵ
			pstmt.setInt(1, rows);
			pstmt.setInt(2, (pages-1)*rows);
			
			//System.out.println("SQL:"+sql);
			//ִ�з�ҳ��ѯ��sql��䣬��������浽�����������
			rs=pstmt.executeQuery();
			//ʹ��ѭ�����Խ���������ݽ��д���
			while(rs.next()){
				//����ͼ�����,���ڽ�������е�һ�����ݱ��浽ͼ�������
				StockCheck stockCheck=new StockCheck();
				//��ͼ���ű��浽ͼ�������
				stockCheck.setBookNo(rs.getInt("bookNo"));
				//��ͼ�����Ʊ��浽ͼ�������
				stockCheck.setBookName(rs.getString("bookName"));
				//���ɹ��������浽ͼ�������
				stockCheck.setStockNamber(rs.getInt("stockNamber"));
				//����ǰͼ�������ӵ�������
				stockCheckList.add(stockCheck);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ط�ҳ���
		return stockCheckList;
	}

	/**
	 * ͳ�Ƽ�¼��
	 */
	@Override
	public int stockCheckCount() {
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
			String sql="select count(*) as StockCheckCount from StockCheck";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			
			//����һ�����������ڴӽ�����л�ȡ����
			int rowCount=0;
			if(rs.next()){
				//�˴���ͳ���б���������������û��ָ����������������0��ʾ
				rowCount=rs.getInt("StockCheckCount");
			}
			//���ط���ֵ��ͼ�����
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}
}
