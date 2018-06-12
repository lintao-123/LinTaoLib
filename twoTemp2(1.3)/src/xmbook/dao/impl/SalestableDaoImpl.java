package xmbook.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xmbook.dao.SalestableDao;
import xmbook.entity.Salestable;
import xmbook.utils.JdbcUtils;

public class SalestableDaoImpl implements SalestableDao {
	/**
	 * ������ʵ�������ӵ����ݿ�salestable����
	 * @param salestable ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	@Override
	public int add(Salestable salestable) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//��дSQL���
			String sql="insert into salestable values(?,?,?,?,?,?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1, salestable.getOrdernumber());
			pstmt.setString(2,salestable.getBookname());
			pstmt.setInt(3, salestable.getSalesnumber());
			pstmt.setDouble(4, salestable.getShouldprice());
			pstmt.setDouble(5, salestable.getRealityprice());
			pstmt.setString(6, salestable.getSalestime());
			pstmt.setString(7, salestable.getSaleser());
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
	 * �������۱�Ŵ����ݿ��salestable��ɾ������
	 * @param bookno ���۱��
	 * @return �ɹ�ɾ���ļ�¼��(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
	 */
	@Override
	public int delete(int bookno) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//��дSQL���
			String sql="delete from salestable where bookno=?;";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setInt(1, bookno);
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
	 * �����۶����޸ĵ����ݿ��
	 * @param salestable ���۶���
	 * @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
	 */
	@Override
	public int update(Salestable salestable) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//��дSQL���
			String sql="update salestable set ordernumber=?,bookname=?,salesnumber=?,"
					+ "shouldprice=?,realityprice=?,salestime=?,saleser=? "
					+ "where bookno=?";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//����SQL����еĲ���
			pstmt.setString(1,salestable.getOrdernumber());
			pstmt.setString(2, salestable.getBookname());
			pstmt.setInt(3, salestable.getSalesnumber());
			pstmt.setDouble(4,salestable.getShouldprice());
			pstmt.setDouble(5, salestable.getRealityprice());
			pstmt.setString(6, salestable.getSalestime());
			pstmt.setString(7, salestable.getSaleser());
			pstmt.setInt(8, salestable.getBookno());
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
	 * �����ݿ���в���ָ��ҳ�����۶���
	 * @param rows ÿҳ�ļ�¼��
	 * @param pages ҳ��
	 * @return ��ǰҳ�����۶��󼯺�
	 */
	@Override
	public List<Salestable> findByPage(int rows, int pages) {
		//����һ�����ۼ��ϣ��������浱ǰҳ�����۶���
		List<Salestable> salestableList=new ArrayList<Salestable>();
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		
		try {
			//ʵ�������Ӷ���
			conn=JdbcUtils.getConnection();
			//��дִ�е�SQL���
			String sql="select top (?) * from salestable "
					+ "where bookno not in("
					+ "select top (?) bookno from salestable)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//��������ֵ
			pstmt.setInt(1, rows);
			pstmt.setInt(2, (pages-1)*rows);			
			//ִ�з�ҳ��ѯ��sql��䣬��������浽�����������
			rs=pstmt.executeQuery();
			//ʹ��ѭ�����Խ���������ݽ��д���
			while(rs.next()){
				//�������۶���,���ڽ�������е�һ�����ݱ��浽���۶�����
				Salestable salestable=new Salestable();
				//�����۱�ű��浽������
				salestable.setBookno(rs.getInt("bookno"));
				//�����۶����� ���浽������
				salestable.setOrdernumber(rs.getString("ordernumber"));
				//��ͼ�����Ʊ��浽���۶�����
				salestable.setBookname(rs.getString("bookname"));
				//�������������浽���۶�����
				salestable.setSalesnumber(rs.getInt("salesnumber"));
				//��Ӧ�ռ۸񱣴浽���۶�����
				salestable.setShouldprice(rs.getDouble("shouldprice"));
				//��ʵ�ռ۸񱣴浽���۶���
				salestable.setRealityprice(rs.getDouble("realityprice"));
				//���������ڱ��浽���۶�����
				salestable.setSalestime(rs.getString("salestime"));
				//������Ա���浽���۶�����
				salestable.setSaleser(rs.getString("saleser"));
				//����ǰ���۶�����ӵ�������
				salestableList.add(salestable);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ
			JdbcUtils.close(rs, pstmt, conn);
		}
		//���ط�ҳ���
		return salestableList;
	}

	@Override
	public int salestableCount() {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//��дSQL���
			String sql="select count(*) as salestablecount from salestable";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//������������:��������е����ݱ��浽������ 
			
			//����һ�����������ڴӽ�����л�ȡ����
			int rowCount=0;
			if(rs.next()){
				//�˴���ͳ���б���������������û��ָ����������������0��ʾ
				rowCount=rs.getInt("salestablecount");
			}
			//���ط���ֵ�����۶���
			return rowCount;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}
	/**
	 * �����ݿ���и���ͼ�����Ʋ�ѯͼ������
	 * @param bookname ͼ������
	 * @return �������۶��� 
	 */
	@Override
	public Salestable findName(String bookname) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//��дSQL���
			String sql="select *   from salestable where bookname=? ";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, bookname);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//����һ���ն���
			Salestable	salestable=null;
			//������������:��������е����ݱ��浽������ 
			if(rs.next()){
				salestable=new Salestable();
				//�����۱�ű��浽������
				salestable.setBookno(rs.getInt("bookno"));
				//�����۶����� ���浽������
				salestable.setOrdernumber(rs.getString("ordernumber"));
				//��ͼ�����Ʊ��浽���۶�����
				salestable.setBookname(rs.getString("bookname"));
				//�������������浽���۶�����
				salestable.setSalesnumber(rs.getInt("salesnumber"));
				//��Ӧ�ռ۸񱣴浽���۶�����
				salestable.setShouldprice(rs.getDouble("shouldprice"));
				//��ʵ�ռ۸񱣴浽���۶���
				salestable.setRealityprice(rs.getDouble("realityprice"));
				//���������ڱ��浽���۶�����
				salestable.setSalestime(rs.getString("salestime"));
				//������Ա���浽���۶�����
				salestable.setSaleser(rs.getString("saleser"));
			}
			//���ط���ֵ�����۶���
			return salestable;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}

	@Override
	public Salestable findId(int bookno) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		//�������������
		ResultSet rs=null;
		
		try {
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnection();
			//��дSQL���
			String sql="select *   from salestable where bookno=? ";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			//���ò���
			pstmt.setInt(1, bookno);
			//ִ��SQL��䣬���ؽ��������
			rs=pstmt.executeQuery();
			//����һ���ն���
			Salestable	salestable=null;
			//������������:��������е����ݱ��浽������ 
			if(rs.next()){
				salestable=new Salestable();
				//�����۱�ű��浽������
				salestable.setBookno(rs.getInt("bookno"));
				//�����۶����� ���浽������
				salestable.setOrdernumber(rs.getString("ordernumber"));
				//��ͼ�����Ʊ��浽���۶�����
				salestable.setBookname(rs.getString("bookname"));
				//�������������浽���۶�����
				salestable.setSalesnumber(rs.getInt("salesnumber"));
				//��Ӧ�ռ۸񱣴浽���۶�����
				salestable.setShouldprice(rs.getDouble("shouldprice"));
				//��ʵ�ռ۸񱣴浽���۶���
				salestable.setRealityprice(rs.getDouble("realityprice"));
				//���������ڱ��浽���۶�����
				salestable.setSalestime(rs.getString("salestime"));
				//������Ա���浽���۶�����
				salestable.setSaleser(rs.getString("saleser"));
			}
			//���ط���ֵ�����۶���
			return salestable;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally{
			//�ر�/�ͷ���Դ(������������������Ӷ���)
			JdbcUtils.close(rs, pstmt, conn);
		}
	}
}
