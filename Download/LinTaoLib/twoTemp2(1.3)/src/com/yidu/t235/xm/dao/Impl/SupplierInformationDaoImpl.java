package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.SupplierInformationDao;
import com.yidu.t235.xm.entity.SupplierInformation;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * ���ݲ�����ݿ���в���
 * @author �����
 * ���ڣ�2018��3��8�� ����4:18:23
 */
public class SupplierInformationDaoImpl implements SupplierInformationDao{
	/**
	 * ����Ӧ�̱�Ŷ�����ӵ����ݿ�supplierInformation����
	 * @param supplierInformation ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	@Override
	public int add(SupplierInformation supplierInformation) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="insert into supplierInformation values(?,?,?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1, supplierInformation.getSupplier());
			pstmt.setString(2, supplierInformation.getTelephoneNumbers());
			pstmt.setString(3,supplierInformation.getAddress());
			pstmt.setString(4, supplierInformation.getContacts());
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
	 * ���ݹ�Ӧ�̱�Ŵ����ݿ��supplierInformation��ɾ����Ӧ�̱��
	 * @param supplierNumber ��Ӧ�̱��
	 * @return �ɹ�ɾ���ļ�¼��(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
	 */
	@Override
	public int delete(int supplierNumber) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="delete from supplierInformation where supplierNumber=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setInt(1, supplierNumber);
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
	 * ����Ӧ�̶����޸ĵ����ݿ��
	 * @param supplierInformation ��Ӧ�̶���
	 * @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
	 */
	@Override
	public int update(SupplierInformation supplierInformation) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="update SupplierInformation set supplier=?,telephoneNumbers=?,address=?,contacts=? "
					+ "where supplierNumber=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1,supplierInformation.getSupplier());
			pstmt.setString(2,supplierInformation.getTelephoneNumbers());
			pstmt.setString(3, supplierInformation.getAddress());
			pstmt.setString(4, supplierInformation.getContacts());
			pstmt.setInt(5, supplierInformation.getSupplierNumber());
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
	 * ���ݹ�Ӧ�̱�Ŵ����ݿ���в��ұ��
	 * @param supplierNumber ��Ӧ�̱��
	 * @return ���ҳɹ���Ӧ�̵Ķ���(�������null���󣬱�ʾ����ʧ��)
	 */
	@Override
	public SupplierInformation findById(int supplierNumber) {
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
			String sql="select * from supplierInformation where supplierNumber=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����sql����еĲ���
			pstmt.setInt(1, supplierNumber);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			//����һ����Ӧ�̶���
			SupplierInformation supplierInformation=null;
			if(rs.next()){
				//ʵ������Ӧ�̶���,���ڽ�������е����ݱ��浽��Ӧ�̶�����
				supplierInformation=new SupplierInformation();
				//����Ӧ�̱�ű��浽��Ӧ�̶�����
				supplierInformation.setSupplierNumber(rs.getInt("supplierNumber"));
				//����Ӧ�̱��浽��Ӧ�̶�����
				supplierInformation.setSupplier(rs.getString("Supplier"));
				//����ϵ�绰���浽��Ӧ�̶�����
				supplierInformation.setTelephoneNumbers(rs.getString("telephoneNumbers"));
				//����ϵ��ַ���浽��Ӧ�̶�����
				supplierInformation.setAddress(rs.getString("address"));
				//����ϵ�˱��浽��Ӧ�̶���
				supplierInformation.setContacts(rs.getString("contacts"));
			}
			//���ط���ֵ����Ӧ�̶���
			return supplierInformation;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

	/**
	 * �����ݿ���в���ָ��ҳ�Ĺ�Ӧ�̶���
	 * @param rows ÿҳ�ļ�¼��
	 * @param pages ҳ��
	 * @return ��ǰҳ�Ĺ�Ӧ�̶��󼯺�
	 */
	@Override
	public List<SupplierInformation> findByPage(int rows, int pages) {
		//����һ����Ӧ�̼��ϣ��������浱ǰҳ�Ĺ�Ӧ�̶���
		List<SupplierInformation> supplierInformationList=new ArrayList<SupplierInformation>();
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
			String sql="select top (?) * from supplierInformation"
					+ " where suppliernumber not in("
					+ "select top (?) suppliernumber from supplierInformation)";
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
				//���幩Ӧ�̶���,���ڽ�������е�һ�����ݱ��浽��Ӧ�̶�����
				SupplierInformation supplierInformation=new SupplierInformation();
				//����Ӧ�̱�ű��浽��Ӧ�̶�����
				supplierInformation.setSupplierNumber(rs.getInt("supplierNumber"));
				//����Ӧ�̱��浽��Ӧ�̶�����
				supplierInformation.setSupplier(rs.getString("Supplier"));
				//����ϵ�绰���浽��Ӧ�̶�����
				supplierInformation.setTelephoneNumbers(rs.getString("telephoneNumbers"));
				//����ϵ��ַ���浽��Ӧ�̶�����
				supplierInformation.setAddress(rs.getString("address"));
				//����ϵ�˱��浽��Ӧ�̶���
				supplierInformation.setContacts(rs.getString("contacts"));
				//����ǰ��Ӧ�̶�����ӵ�������
				supplierInformationList.add(supplierInformation);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ط�ҳ���
		return supplierInformationList;
	}

	/**
	 * ͳ�Ƽ�¼��
	 */
	@Override
	public int supplierInformationCount() {
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
			String sql="select count(*) as supplierInformationCount from supplierInformation";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			
			//����һ�����������ڴӽ�����л�ȡ����
			int rowCount=0;
			if(rs.next()){
				//�˴���ͳ���б���������������û��ָ����������������0��ʾ
				rowCount=rs.getInt("supplierInformationCount");
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
