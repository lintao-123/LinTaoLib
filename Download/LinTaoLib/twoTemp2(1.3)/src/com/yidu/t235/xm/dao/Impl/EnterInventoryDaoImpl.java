package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.EnterInventoryDao;
import com.yidu.t235.xm.entity.EnterInventory;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * ���ݲ�����ݿ���в���
 * @author �����
 * ���ڣ�2018��3��8�� ����4:18:23
 */
public class EnterInventoryDaoImpl implements EnterInventoryDao {
	/**
	 * ��ͼ��ʵ�������ӵ����ݿ�enterInventory����
	 * @param enterInventory ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	@Override
	public int add(EnterInventory enterInventory) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="insert into enterInventory values(?,?,?,?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1,enterInventory.getBookName());
			pstmt.setInt(2, enterInventory.getPurchaseQuantity());
			pstmt.setDouble( 3,enterInventory.getBookBid());
			pstmt.setString(4, enterInventory.getInventoryTime());
			pstmt.setString(5, enterInventory.getConsoleOperator());
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
	 * ����ͼ���Ŵ����ݿ��enterInventory��ɾ��ͼ��
	 * @param bookNo ͼ����
	 * @return �ɹ�ɾ���ļ�¼��(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
	 */
	@Override
	public int delete(int bookNo) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="delete from enterInventory where bookNo=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setInt(1, bookNo);
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
	 * ��ͼ������޸ĵ����ݿ��
	 * @param enterInventory ͼ�����
	 * @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
	 */
	@Override
	public int update(EnterInventory enterInventory) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="update enterInventory set bookName=?,purchaseQuantity=?,"
					+ " bookBid=?, inventoryTime=?,consoleOperator=? "
					+ "where bookNo=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1,enterInventory.getBookName());
			pstmt.setInt(2, enterInventory.getPurchaseQuantity());
			pstmt.setDouble( 3,enterInventory.getBookBid());
			pstmt.setString(4, enterInventory.getInventoryTime());
			pstmt.setString(5, enterInventory.getConsoleOperator());
			pstmt.setInt(6, enterInventory.getBookNo());
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
	public EnterInventory findByName(String bookName) {
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
			String sql="select * from enterInventory where bookname=? ";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����sql����еĲ���
			pstmt.setString(1, bookName);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			//����һ��ͼ�����
			EnterInventory enterInventory=null;
			if(rs.next()){
				//ʵ����ͼ�����,���ڽ�������е����ݱ��浽ͼ�������
				enterInventory=new EnterInventory();
				//��ͼ���ű��浽ͼ�������
				enterInventory.setBookNo(rs.getInt("bookNo"));
				//��ͼ�����Ʊ��浽ͼ�������
				enterInventory.setBookName(rs.getString("bookName"));
				//���ɹ��������浽ͼ�������
				enterInventory.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				//��ͼ����۱��浽ͼ�������
				enterInventory.setBookBid(rs.getDouble("bookBid"));
				//�����ʱ�䱣�浽ͼ�����
				enterInventory.setInventoryTime(rs.getString("inventoryTime"));
				//������Ա���浽ͼ�������
				enterInventory.setConsoleOperator(rs.getString("consoleOperator"));
			}
			//���ط���ֵ��ͼ�����
			return enterInventory;
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
	public List<EnterInventory> findByPage(int rows, int pages) {
		//����һ��ͼ�鼯�ϣ��������浱ǰҳ��ͼ�����
		List<EnterInventory> enterInventoryList=new ArrayList<EnterInventory>();
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
			String sql="select top (?) * from enterInventory"
					+ " where bookno not in("
					+ "select top (?) bookno from enterInventory)";
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
				EnterInventory enterInventory=new EnterInventory();
				//��ͼ���ű��浽ͼ�������
				enterInventory.setBookNo(rs.getInt("bookNo"));
				//��ͼ�����Ʊ��浽ͼ�������
				enterInventory.setBookName(rs.getString("bookName"));
				//���ɹ��������浽ͼ�������
				enterInventory.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				//��ͼ����۱��浽ͼ�������
				enterInventory.setBookBid(rs.getDouble("bookBid"));
				//�����ʱ�䱣�浽ͼ�����
				enterInventory.setInventoryTime(rs.getString("inventoryTime"));
				//������Ա���浽ͼ�������
				enterInventory.setConsoleOperator(rs.getString("consoleOperator"));
				//����ǰͼ�������ӵ�������
				enterInventoryList.add(enterInventory);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ط�ҳ���
		return enterInventoryList;
	}

	
	/**
	 * ͳ�Ƽ�¼��
	 */
	@Override
	public int enterInventoryCount() {
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
			String sql="select count(*) as enterInventoryCount from enterInventory";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			
			//����һ�����������ڴӽ�����л�ȡ����
			int rowCount=0;
			if(rs.next()){
				//�˴���ͳ���б���������������û��ָ����������������0��ʾ
				rowCount=rs.getInt("enterInventoryCount");
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
	
	/**
	 * ����ͼ���Ų�ѯͼ��
	 */
	@Override
	public EnterInventory findById(int bookNo) {
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
			String sql="select * from enterInventory where bookno=? ";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����sql����еĲ���
			pstmt.setInt(1, bookNo);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			//����һ��ͼ�����
			EnterInventory enterInventory=null;
			if(rs.next()){
				//ʵ����ͼ�����,���ڽ�������е����ݱ��浽ͼ�������
				enterInventory=new EnterInventory();
				//��ͼ���ű��浽ͼ�������
				enterInventory.setBookNo(rs.getInt("bookNo"));
				//��ͼ�����Ʊ��浽ͼ�������
				enterInventory.setBookName(rs.getString("bookName"));
				//���ɹ��������浽ͼ�������
				enterInventory.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				//���ɹ��۸񱣴浽ͼ�������
				enterInventory.setBookBid(rs.getDouble("bookBid"));
				//�����ʱ�䱣�浽ͼ�����
				enterInventory.setInventoryTime(rs.getString("inventoryTime"));
				//������Ա���浽ͼ�������
				enterInventory.setConsoleOperator(rs.getString("consoleOperator"));
			}
			//���ط���ֵ��ͼ�����
			return enterInventory;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}	
}
