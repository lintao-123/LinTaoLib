package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.ProcureInfoDao;
import com.yidu.t235.xm.entity.ProcureInfo;
import com.yidu.t235.xm.utils.JdbcUtils;

/**
 * �ɹ�������ʵ����
 * 
 * @author Administrator
 *
 */
public class ProcureInfoDaoImpl implements ProcureInfoDao {
	/**
	 * ���ɹ�����������ӵ����ݿ����
	 * 
	 * @param procureInfo
	 *            �ɹ���������
	 * @return���ش���1����ʾ��ӳɹ�������0�����ʧ��
	 */
	@Override
	public int add(ProcureInfo procureInfo) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����Ԥ����������
		PreparedStatement pstmt = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnectoin();
			//��дSQL���
			String sql = "insert into  procureInfo values(?,?,?) ";
			//ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			/*����SQL������*/
			//���òɹ�������
			pstmt.setString(1, procureInfo.getProcurementNo());
			//���ù�Ӧ��
			pstmt.setString(2, procureInfo.getSupplier());
			//���òɹ�����
			pstmt.setString(3, procureInfo.getLibraryTime());
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}

	}

	/**
	 * ����Ŵ����ݿ����ɾ��
	 * 
	 * @param tempId
	 *            ���
	 * @return ���ش���1����ʾɾ���ɹ�������0��ɾ��ʧ��
	 */
	@Override
	public int delete(int tempId) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����Ԥ����������
		PreparedStatement pstmt = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnectoin();
			//��дSQL���
			String sql = "delete from  procureInfo where tempId=? ";
			//ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			/*����SQL����еĲ���*/
			//���ñ��
			pstmt.setInt(1, tempId);
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	/**
	 * ���ɹ����������޸ĵ����ݿ�
	 * 
	 * @param procureInfo
	 *            �ɹ���������
	 * @return ���ش���1����ʾ�޸ĳɹ�������0���޸�ʧ��
	 */
	@Override
	public int update(ProcureInfo procureInfo) {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����Ԥ����������
		PreparedStatement pstmt = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnectoin();
			//��дSQL���
			String sql = "update   procureInfo set procurementNo=?, supplier=?, " 
						+ " libraryTime=? where tempId=? ";
			//ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			/*����SQL������*/
			//���òɹ�������
			pstmt.setString(1, procureInfo.getProcurementNo());
			//���ù�Ӧ��
			pstmt.setString(2, procureInfo.getSupplier());
			//���òɹ�ʱ��
			pstmt.setString(3, procureInfo.getLibraryTime());
			//���ñ��
			pstmt.setInt(4, procureInfo.getTempId());
			//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(null, pstmt, conn);
		}
	}

	/**
	 * �����ݿ���в���ָ��ҳ
	 * 
	 * @param rows
	 *            ����
	 * @param page
	 *            ҳ��
	 * @return ���ؼ��϶���
	 */
	@Override
	public List<ProcureInfo> findPage(int rows, int page) {
		//����һ���ɹ��������󼯺�,�������浱ǰҳ����
		List<ProcureInfo> procureInfosList = new ArrayList<ProcureInfo>();
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����Ԥ����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs = null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn = JdbcUtils.getConnectoin();
			//��дSQL���
			String sql = "select top(?) * from procureInfo  "  
						+" where tempId not in( "
						+ " select top(?) tempId from procureInfo) ";
			//ʵ����Ԥ����������
			pstmt = conn.prepareStatement(sql);
			/*����SQL������*/
			//��������
			pstmt.setInt(1, rows);
			//��������*ҳ��-1
			pstmt.setInt(2, rows * (page - 1));
			//ִ��SQL��䣬���ؽ��������
			rs = pstmt.executeQuery();
			//ʹ��ѭ��,�Խ�����е����ݽ��д���
			while (rs.next()) {
				//����ɹ������������ڶԽ�����е�һ�����ݱ��浽����������
				ProcureInfo procureInfo = new ProcureInfo();
				//����ű��浽������
				procureInfo.setTempId(rs.getInt("tempId"));
				//���ɹ������ű��浽������
				procureInfo.setProcurementNo(rs.getString("procurementNo"));
				//����Ӧ�̱��浽������
				procureInfo.setSupplier(rs.getString("supplier"));
				//���ɹ�ʱ�䱣�浽������
				procureInfo.setLibraryTime(rs.getString("libraryTime"));
				//����ǰ�ɹ�����������ӵ�������
				procureInfosList.add(procureInfo);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ط�ҳ���
		return procureInfosList;
	}

	/**
	 * ͳ�����ݿ���¼��
	 * 
	 * @return ���ؼ�¼��
	 */

	@Override
	public int getCount() {
		//�������ݿ����Ӷ���
		Connection conn = null;
		//����Ԥ����������
		PreparedStatement pstmt = null;
		//�������������
		ResultSet rs=null;
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="select count(*)as procureInfos from procureInfo";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//����һ�����������ڴӽ�����л�ȡ����
			int rowCount=0;
			if (rs.next()) {
				//�˴���ͳ���б���������������û��ָ����������������0��ʾ
				rowCount=rs.getInt("procureInfos");
			}
			//���ط���ֵ:ͳ�Ƽ�¼��
			return rowCount;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			//�ر����ݿ����Ӻ��ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

}
