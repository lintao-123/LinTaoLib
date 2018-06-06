package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.ProcurementStatementDao;
import com.yidu.t235.xm.entity.ProcurementStatement;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * �ɹ���������ʵ����
 * @author Administrator
 *
 */
public class ProcurementStatementDaoImpl implements ProcurementStatementDao{
	/**
	 * ��Ӳɹ��������鵽���ݿ����
	 * @param procurement �ɹ������������
	 * @return ���ش���1,��ʾ��ӳɹ� ����0,����ӱ�ʾʧ��
	 */
	@Override
	public int add(ProcurementStatement procurement) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
	    PreparedStatement pstmt=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//Ԥ����SQL���
			String sql="insert into procurementStatement values(?,?,?,?,?,?,?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			/*����SQL������*/
			//���òɹ�������
			pstmt.setString(1, procurement.getProcurementNo());
			//����ͼ������
			pstmt.setString(2, procurement.getBookName());
			//����ͼ������
			pstmt.setString(3, procurement.getBookType());
			//����ͼ�����
			pstmt.setDouble(4, procurement.getBookBid());
			//���ù�Ӧ��
			pstmt.setString(5, procurement.getSupplier());
			//���òɹ�ʱ��
			pstmt.setString(6,procurement.getLibraryTime());
			//���òɹ�����
			pstmt.setInt(7, procurement.getPurchaseQuantity());
			//���ò���Ա
			pstmt.setString(8,procurement.getPlayEmp());
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}
		
	}
	/**
	 * ���ݱ��ɾ���������鵽���ݿ���
	 * @param tempId ���
	 * @return ���ش���1,��ʾɾ���ɹ� ����0,��ɾ����ʾʧ��
	 */
	@Override
	public int delete(int tempId) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="delete from procurementStatement where tempId=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			/*����SQL����еĲ���*/
			//���ñ��
			pstmt.setInt(1, tempId);
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}
		
	}
	/**
	 * ���ɹ����������޸ĵ����ݿ����
	 * @param procurement �ɹ���������
	 * @return ���ش���1,��ʾ�޸ĳɹ� ����0,���޸ı�ʾʧ��
	 */
	@Override
	public int update(ProcurementStatement procurement) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="update procurementStatement set procurementNo=?, bookName=?, bookType=?,"
					+ "  bookBid=?,supplier=?,libraryTime=?,purchaseQuantity=?, "
					+ "   playEmp=? where tempId=?  ";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			/*����SQL������*/
			//���òɹ�������
			pstmt.setString(1, procurement.getProcurementNo());
			//����ͼ������
			pstmt.setString(2, procurement.getBookName());
			//����ͼ������
			pstmt.setString(3, procurement.getBookType());
			//����ͼ�����
			pstmt.setDouble(4, procurement.getBookBid());
			//���ù�Ӧ��
			pstmt.setString(5, procurement.getSupplier());
			//���òɹ�ʱ��
			pstmt.setString(6,procurement.getLibraryTime());
			//���òɹ�����
			pstmt.setInt(7, procurement.getPurchaseQuantity());
			//���ò���Ա
			pstmt.setString(8,procurement.getPlayEmp());
			//���ñ��
			pstmt.setInt(9, procurement.getTempId());
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(null,pstmt,conn);
		}
	}
	/**
	 * �����ݿ���в��ҳ�ָ��ҳ
	 * @param rows ÿҳ��¼��
	 * @param page ҳ��
	 * @return ���ؼ��϶���
	 */
	@Override
	public List<ProcurementStatement> findPage(int rows, int page) {
		//����һ���ɹ��������󼯺�,�������浱ǰҳ����
		List<ProcurementStatement> procurementStatementsList=new ArrayList<ProcurementStatement>();
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
			String sql=" select top(?) * from procurementStatement "
					+ " where procurementNo not in( "
					+ " select top(?) procurementNo from procurementStatement) ";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			/*����SQL������*/
			//��������
			pstmt.setInt(1, rows);
			//��������*ҳ��-1
			pstmt.setInt(2, rows*(page-1));
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//ʹ��ѭ��,�Խ�����е����ݽ��д���
			while(rs.next()){
				//����ɹ������������ڶԽ�����е�һ�����ݱ��浽����������
				ProcurementStatement procurementStatement=new ProcurementStatement();
				//��Ա����ű��浽������
				procurementStatement.setTempId(rs.getInt("tempId"));
				//���ɹ������ű��浽������
				procurementStatement.setProcurementNo(rs.getString("procurementNo"));
				//��ͼ�����Ʊ��浽������
				procurementStatement.setBookName(rs.getString("bookName"));
				//��ͼ�����ͱ��浽������
				procurementStatement.setBookType(rs.getString("bookType"));
				//��ͼ����۱��浽������
				procurementStatement.setBookBid(rs.getDouble("bookBid"));
				//����Ӧ�̱��浽������
				procurementStatement.setSupplier(rs.getString("supplier"));
				//���ɹ�ʱ�䱣�浽������
				procurementStatement.setLibraryTime(rs.getString("libraryTime"));
				//���ɹ��������浽������
				procurementStatement.setPurchaseQuantity(rs.getInt("purchaseQuantity"));
				//������Ա���浽������
				procurementStatement.setPlayEmp(rs.getString("playEmp"));
				//��������ܼ۱��浽������
				procurementStatement.setTotal(procurementStatement.getTotal());
				//����ǰ�ɹ��������������ӵ�������
				procurementStatementsList.add(procurementStatement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ط�ҳ���
		return procurementStatementsList;
	}
	/**
	 * ͳ�Ʋɹ����������ļ�¼��
	 * @return
	 */
	@Override
	public int getCount() {
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
			String sql="select count(*) as procurementStatementInfo from procurementStatement ";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��SQL��䣬���ؽ��������
			rs= pstmt.executeQuery();
			//����һ�����������ڴӽ�����л�ȡ����
			int rowCount=0;
			if(rs.next()){
				//�˴���ͳ���б���������������û��ָ����������������0��ʾ
				rowCount=rs.getInt("procurementStatementInfo");
			}
			//���ط���ֵ���ɹ������������
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
				
	}

}
