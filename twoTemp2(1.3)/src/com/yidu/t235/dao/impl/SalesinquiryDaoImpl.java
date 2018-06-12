package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.dao.SalesinquiryDao;
import com.yidu.t235.entity.Salesinquiry;
import com.yidu.t235.utils.JdbcUtils;
/**
 * ���ݲ�����ݿ���в���
 * @author Ф�Ʒ�
 * ���ڣ�2018-3-09
 */
public class SalesinquiryDaoImpl implements SalesinquiryDao{
    /**
     * �����۶�������ʵ�������ӵ����ݿ�salesinquiry����
     * @param salesinquiryʵ�����
     * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
     */
	@Override
	public int add(Salesinquiry salesinquiry) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="insert into salesinquiry values(?,?,?,?,? )";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setString(1, salesinquiry.getOrdernumberid());
					pstmt.setString(2, salesinquiry.getBookname());
					pstmt.setString(3, salesinquiry.getBooktype());
					pstmt.setDouble(4, salesinquiry.getBookprice());
					pstmt.setInt(5, salesinquiry.getSalesvolume());
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
     * �������۱�Ŵ����ݿ��salesinquiry��ɾ��ͼ��
     * @param ordernumber ���۶���������
     * @return �ɹ�ɾ���ļ�¼��(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
     */
	@Override
	public int delete(int ordernumber) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="delete from salesinquiry where ordernumber=?";
					//ʵ����Ԥ�������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setInt(1, ordernumber);
					//ִ��SQL����
					//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					JdbcUtils.close(null, pstmt, conn);
				}
			}

	/**
     * �����۶��������Ŷ����޸ĵ����ݿ����
     * @param salesinquiry ��Ŷ���
     *  @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
     */
	@Override
	public int update(Salesinquiry salesinquiry) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="update salesinquiry set ordernumberid=?,bookname=?,booktype=?,"
							+ "bookprice=?,salesvolume=?  where ordernumber=?";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setString(1, salesinquiry.getOrdernumberid());
					pstmt.setString(2, salesinquiry.getBookname());
					pstmt.setString(3, salesinquiry.getBooktype());
					pstmt.setDouble(4, salesinquiry.getBookprice());
					pstmt.setInt(5, salesinquiry.getSalesvolume());
					pstmt.setInt(6, salesinquiry.getOrdernumber());
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
     * �����ݿ���в���ָ��ҳ��ͼ�����
     * @param rows ÿҳ�ļ�¼��
     * @param pagesҳ��
     * @return ��ǰҳ��ͼ����󼯺�
     */
	@Override
	public List<Salesinquiry> findByPage(int rows, int page) {
		        //����һ�����ż��ϣ��������浱ǰҳ�Ĳ�����Ϣ
				List<Salesinquiry> salesinquiryList=new ArrayList<Salesinquiry>();
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
					String sql="select top (?) * from salesinquiry "
							+ "where ordernumber not in("
							+ "select top (?) ordernumber from salesinquiry)";
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
						Salesinquiry salesinquiry=new Salesinquiry();
						salesinquiry.setOrdernumber(rs.getInt("ordernumber"));
						salesinquiry.setOrdernumberid(rs.getString("ordernumberid"));
						salesinquiry.setBookname(rs.getString("bookname"));
						salesinquiry.setBooktype(rs.getString("booktype"));
						salesinquiry.setBookprice(rs.getDouble("bookprice"));
						salesinquiry.setSalesvolume(rs.getInt("salesvolume"));
						salesinquiry.setTotal(salesinquiry.getTotal());
	
						salesinquiryList.add(salesinquiry);
					}
					
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//�ر�/�ͷ���Դ
					JdbcUtils.close(rs, pstmt, conn);
				}
				//���ط�ҳ���
				return salesinquiryList;

			}
	/**
	 * ͳ�Ƽ�¼��
	 * @return ��¼��
	 */
	@Override
	public int getSalesinquiryCount() {
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
					String sql="select count(*) as salesinquirycount from salesinquiry";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//ִ��SQL��䣬���ؽ��������
					rs=pstmt.executeQuery();
					//������������:��������е����ݱ��浽������ 
					
					//����һ�����������ڴӽ�����л�ȡ����
					int rowCount=0;
					if(rs.next()){
						//�˴���ͳ���б���������������û��ָ����������������0��ʾ
						rowCount=rs.getInt("salesinquirycount");
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