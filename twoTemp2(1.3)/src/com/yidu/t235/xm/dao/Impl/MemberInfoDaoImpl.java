package com.yidu.t235.xm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.xm.dao.MemberInfoDao;
import com.yidu.t235.xm.entity.MemberInfo;
import com.yidu.t235.xm.utils.JdbcUtils;
/**
 * ���ݲ�����ݿ���в���
 * @author �����
 * ���ڣ�2018��3��8�� ����4:48:25
 */
public class MemberInfoDaoImpl implements MemberInfoDao {
	/**
	 * ����Ա��Ϣ������ӵ����ݿ�memberInfo����
	 * @param memberInfo ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	@Override
	public int add(MemberInfo memberInfo) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="insert into memberInfo values(?,?,?,?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1,memberInfo.getMemberName());
			pstmt.setString(2,memberInfo.getMemberSex());
			pstmt.setString(3, memberInfo.getMemberTelephone());
			pstmt.setString(4, memberInfo.getMemberLevel());
			pstmt.setDouble(5, memberInfo.getMemberIntegral());
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
	 * ���ݻ�Ա��Ŵ����ݿ��memberInfo��ɾ����Ϣ
	 * @param memberId ��Ա���
	 * @return �ɹ�ɾ���ļ�¼��(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
	 */
	@Override
	public int delete(int memberId) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="delete from memberInfo where memberId=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setInt(1, memberId);
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
	 * @param memberInfo ��Ա����
	 * @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
	 */
	@Override
	public int update(MemberInfo memberInfo) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дSQL���
			String sql="update memberInfo set memberName=?,memberSex=?,"
					+ "memberTelephone=?,memberLevel=?,memberIntegral=? "
					+ "where memberId=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1,memberInfo.getMemberName());
			pstmt.setString(2,memberInfo.getMemberSex());
			pstmt.setString(3, memberInfo.getMemberTelephone());
			pstmt.setString(4, memberInfo.getMemberLevel());
			pstmt.setDouble(5, memberInfo.getMemberIntegral());
			pstmt.setInt(6, memberInfo.getMemberId());
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
	 * ���ݻ�Ա��Ŵ����ݿ���в��һ�Ա
	 * @param memberId ��Ա���
	 * @return ���ҳɹ��Ļ�Ա����(�������null���󣬱�ʾ����ʧ��)
	 */
	@Override
	public MemberInfo findById(int memberId) {
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
			String sql="select * from memberInfo where memberId=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����sql����еĲ���
			pstmt.setInt(1, memberId);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			//����һ����Ա����
			MemberInfo memberInfo=null;
			if(rs.next()){
				//ʵ������Ա����,���ڽ�������е����ݱ��浽��Ա������
				memberInfo=new MemberInfo();
				//����Ա��ű��浽��Ա������
				memberInfo.setMemberId(rs.getInt("memberId"));
				//����Ա�������浽��Ա������
				memberInfo.setMemberName(rs.getString("memberName"));
				//����Ա�Ա𱣴浽��Ա����
				memberInfo.setMemberSex(rs.getString("memberSex"));
				//����Ա�绰����Ա����
				memberInfo.setMemberTelephone(rs.getString("memberTelephone"));
				//����Ա�ȼ����浽��Ա������
				memberInfo.setMemberLevel(rs.getString("memberLevel"));
				//����Ա���ֱ��浽��Ա����
				memberInfo.setMemberIntegral(rs.getDouble("memberIntegral"));
			}
			//���ط���ֵ����Ա����
			return memberInfo;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

	/**
	 * �����ݿ���в���ָ��ҳ�Ļ�Ա����
	 * @param rows ÿҳ�ļ�¼��
	 * @param pages ҳ��
	 * @return ��ǰҳ�Ļ�Ա���󼯺�
	 */
	@Override
	public List<MemberInfo> findByPage(int rows, int pages) {
		//����һ����Ա���ϣ��������浱ǰҳ�Ļ�Ա����
		List<MemberInfo> memberInfoList=new ArrayList<MemberInfo>();
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
			String sql="select top (?) * from memberInfo"
					+ " where memberId not in("
					+ "select top (?) memberId from memberInfo)";
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
				//�����Ա����,���ڽ�������е�һ�����ݱ��浽��Ա������
				MemberInfo memberInfo=new MemberInfo();
				//����Ա��ű��浽��Ա������
				memberInfo.setMemberId(rs.getInt("memberId"));
				//����Ա�������浽��Ա������
				memberInfo.setMemberName(rs.getString("memberName"));
				//����Ա�Ա𱣴浽��Ա����
				memberInfo.setMemberSex(rs.getString("memberSex"));
				//����Ա�绰����Ա����
				memberInfo.setMemberTelephone(rs.getString("memberTelephone"));
				//����Ա�ȼ����浽��Ա������
				memberInfo.setMemberLevel(rs.getString("memberLevel"));
				//����Ա���ֱ��浽��Ա����
				memberInfo.setMemberIntegral(rs.getDouble("memberIntegral"));
				//����ǰ��Ա������ӵ�������
				memberInfoList.add(memberInfo);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ط�ҳ���
		return memberInfoList;
	}

	/**
	 * ͳ�Ƽ�¼��
	 */
	@Override
	public int memberInfoCount() {
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
			String sql="select count(*) as memberInfoCount from memberInfo";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			
			//����һ�����������ڴӽ�����л�ȡ����
			int rowCount=0;
			if(rs.next()){
				//�˴���ͳ���б���������������û��ָ����������������0��ʾ
				rowCount=rs.getInt("memberInfoCount");
			}
			//���ط���ֵ����Ա����
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}
}
