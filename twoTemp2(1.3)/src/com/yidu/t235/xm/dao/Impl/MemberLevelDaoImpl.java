package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.MemberLevelDao;
import com.yidu.t235.xm.entity.MemberLevel;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * ���ݲ�����ݿ���в���
 * @author �����
 * ���ڣ�2018��3��8�� ����4:51:44
 */
public class MemberLevelDaoImpl implements MemberLevelDao {
	/**
	 * ����Ա�ȼ�������ӵ����ݿ�memberLevel����
	 * @param memberLevel ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	@Override
	public int add(MemberLevel memberLevel) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="insert into memberLevel values(?,?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1,memberLevel.getLevelName());
			pstmt.setDouble(2,memberLevel.getMemberDiscount());
			pstmt.setString(3, memberLevel.getUpgradeIntegral());
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
	 * ���ݵȼ���Ŵ����ݿ��memberLevel��ɾ����Ա��Ϣ
	 * @param levelNumber �ȼ����
	 * @return �ɹ�ɾ���ļ�¼��(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
	 */
	@Override
	public int delete(int levelNumber) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="delete from memberLevel where levelNumber=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setInt(1, levelNumber);
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
	 * ����Ա�����޸ĵ����ݿ��
	 * @param memberLevel ��Ա����
	 * @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
	 */
	@Override
	public int update(MemberLevel memberLevel) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="update memberLevel set levelName=?,memberDiscount=?,"
					+ "upgradeIntegral=? "
					+ "where levelNumber=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1,memberLevel.getLevelName());
			pstmt.setDouble(2,memberLevel.getMemberDiscount());
			pstmt.setString(3, memberLevel.getUpgradeIntegral());
			pstmt.setInt(4, memberLevel.getLevelNumber());
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
	 * �����ݿ���в���ָ��ҳ�ĵȼ�����
	 * @param rows ÿҳ�ļ�¼��
	 * @param pages ҳ��
	 * @return ��ǰҳ�ĵȼ����󼯺�
	 */
	@Override
	public List<MemberLevel> findByPage(int rows, int pages) {
		//����һ���ȼ����ϣ��������浱ǰҳ�ĵȼ�����
		List<MemberLevel> memberLevelList=new ArrayList<MemberLevel>();
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
			String sql="select top (?) * from memberLevel"
					+ " where levelNumber not in("
					+ "select top (?) levelNumber from memberLevel)";
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
				//����ȼ�����,���ڽ�������е�һ�����ݱ��浽�ȼ�������
				MemberLevel memberLevel=new MemberLevel();
				//���ȼ���ű��浽�ȼ�������
				memberLevel.setLevelNumber(rs.getInt("levelNumber"));
				//���ȼ����Ʊ��浽�ȼ�������
				memberLevel.setLevelName(rs.getString("levelName"));
				//����Ա�ۿ۱��浽�ȼ�����
				memberLevel.setMemberDiscount(rs.getDouble("memberDiscount"));
				//���������ֱ��浽�ȼ�����
				memberLevel.setUpgradeIntegral(rs.getString("upgradeIntegral"));
				//����ǰ�ȼ�������ӵ�������
				memberLevelList.add(memberLevel);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ط�ҳ���
		return memberLevelList;
	}

	/**
	 * ͳ�Ƽ�¼��
	 */
	@Override
	public int memberLevelCount() {
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
			String sql="select count(*) as memberLevelCount from memberLevel";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			
			//����һ�����������ڴӽ�����л�ȡ����
			int rowCount=0;
			if(rs.next()){
				//�˴���ͳ���б���������������û��ָ����������������0��ʾ
				rowCount=rs.getInt("memberLevelCount");
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
