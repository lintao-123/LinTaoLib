package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.dao.BookdetailedDao;
import com.yidu.t235.entity.Bookdetailed;
import com.yidu.t235.utils.JdbcUtils;

/**
 * ���ݲ�����ݿ���в���
 * @author Ф�Ʒ�
 * ���ڣ�2018-3-09 
 */

public class BookdetailedDaoImpl implements  BookdetailedDao{

	/**
     * �����۶���ʵ�������ӵ����ݿ�bookdetailed����
     * @param bookdetailedʵ�����
     * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
     */
	
	@Override
	public int add(Bookdetailed bookdetailed) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="insert into bookdetailed values(?,?,?,?)";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setString(1, bookdetailed.getOrdernumber());
					pstmt.setString(2, bookdetailed.getBookname());
					pstmt.setString(3, bookdetailed.getBookprice());
					pstmt.setString(4, bookdetailed.getSalesman());
					//ִ��SQL����
					//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//�ر����ݿ�����
					JdbcUtils.close(null, pstmt, conn);
				}
			}
	/**
     * �����۶���ʵ�����ɾ�������ݿ�bookdetailed����
     * @param bookdetailedʵ�����
     * @return �ɹ�ɾ��������(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
     */
	@Override
	public int delete(int bookno) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="delete from bookdetailed where bookno=?";
					//ʵ����Ԥ�������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setInt(1, bookno);
					//ִ��SQL����
					//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//�ر����ݿ�����
					JdbcUtils.close(null, pstmt, conn);
				}
			}
	/**
     * �����۶����޸ĵ����ݿ��
     * @param bookdetailed ���۶���
     * @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
     */
	@Override
	public int update(Bookdetailed bookdetailed) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="update bookdetailed set ordernumber=?,bookname=?,bookprice=?,salesman=? where bookno=?";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setString(1, bookdetailed.getOrdernumber());
					pstmt.setString(2, bookdetailed.getBookname());
					pstmt.setString(3, bookdetailed.getBookprice());
					pstmt.setString(4, bookdetailed.getSalesman());
					pstmt.setInt(5, bookdetailed.getBookno());
					//ִ��SQL����
					//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//�ر����ݿ�����
					JdbcUtils.close(null, pstmt, conn);
				}
			}
	/**
     * �����ݿ���в���ָ��ҳ�����۶���
     * @param rows ÿҳ�ļ�¼��
     * @param pages ҳ��
     * @return ��ǰҳ��ͼ����󼯺�
     */
	@Override
	public List<Bookdetailed> findByPage(int rows, int page) {
		        //����һ�����ż��ϣ��������浱ǰҳ�Ĳ�����Ϣ
				List<Bookdetailed> bookdetailedList=new ArrayList<Bookdetailed>();
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
					String sql="select top (?) * from bookdetailed "
							+ " where bookno not in( "
							+ " select top (?) bookno from bookdetailed)";
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
						Bookdetailed bookdetailed=new Bookdetailed();
						bookdetailed.setBookno(rs.getInt("bookno"));
						bookdetailed.setOrdernumber(rs.getString("ordernumber"));
						bookdetailed.setBookname(rs.getString("bookname"));
						bookdetailed.setBookprice(rs.getString("bookprice"));
						bookdetailed.setSalesman (rs.getString("salesman"));
						
						bookdetailedList.add(bookdetailed);
					}
					
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//�ر�/�ͷ���Դ
					JdbcUtils.close(rs, pstmt, conn);
				}
				//���ط�ҳ���
				return bookdetailedList;

			}
	/**
	 * ͳ�Ƽ�¼��
	 * @return ��¼��
	 */
	@Override
	public int getBookdetailedCount() {
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
					String sql="select count(*) as bookdetailedcount from bookdetailed";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//ִ��SQL��䣬���ؽ��������
					rs=pstmt.executeQuery();
					//������������:��������е����ݱ��浽������ 
					
					//����һ�����������ڴӽ�����л�ȡ����
					int rowCount=0;
					if(rs.next()){
						//�˴���ͳ���б���������������û��ָ����������������0��ʾ
						rowCount=rs.getInt("bookdetailedcount");
					}
					//���ط���ֵ��Ա������
					return rowCount;
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//�ر�/�ͷ���Դ(������������������Ӷ���)
					JdbcUtils.close(rs, pstmt, conn);
				}
			}

		}