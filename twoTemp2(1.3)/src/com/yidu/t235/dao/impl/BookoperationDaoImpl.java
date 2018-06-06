package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yidu.t235.dao.BookoperationDao;
import com.yidu.t235.entity.Bookoperation;
import com.yidu.t235.utils.JdbcUtils;

public class BookoperationDaoImpl implements BookoperationDao{

	@Override
	public int add(Bookoperation bookoperation) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="insert into bookoperation values(?,?,?,?,?,?)";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setString(1, bookoperation.getBookname());
					pstmt.setString(2, bookoperation.getBooktype());
					pstmt.setString(3, bookoperation.getBookauthor());
					pstmt.setString(4, bookoperation.getSupplier());
					pstmt.setString(5, bookoperation.getBookprice());
					pstmt.setString(6, bookoperation.getBookunit());
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
					String sql="delete from bookoperation where bookno=?";
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


	@Override
	public int update(Bookoperation bookoperation) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="update bookoperation set bookname=?,booktype=?,bookauthor=?,"
							+ "supplier=?,bookprice=?,bookunit=? where bookno=?";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setString(1, bookoperation.getBookname());
					pstmt.setString(2, bookoperation.getBooktype());
					pstmt.setString(3, bookoperation.getBookauthor() );
					pstmt.setString(4, bookoperation.getSupplier());
					pstmt.setString(5, bookoperation.getBookprice());
					pstmt.setString(6, bookoperation.getBookunit());
					pstmt.setInt(7, bookoperation.getBookno());
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


	@Override
	public List<Bookoperation> findByPage(int rows, int page) {
		        //����һ�����ż��ϣ��������浱ǰҳ�Ĳ�����Ϣ
				List<Bookoperation> bookoperationList=new ArrayList<Bookoperation>();
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
					String sql="select top (?) * from bookoperation "
							+ "where bookno not in("
							+ "select top (?) bookno from bookoperation)";
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
						Bookoperation bookoperation=new Bookoperation();
						bookoperation.setBookno(rs.getInt("bookno"));
						bookoperation.setBookname(rs.getString("bookname"));
						bookoperation.setBooktype(rs.getString("booktype"));
						bookoperation.setBookauthor(rs.getString("bookauthor"));
						bookoperation.setSupplier(rs.getString("supplier"));
						bookoperation.setBookprice(rs.getString("bookprice"));
						bookoperation.setBookunit(rs.getString("bookunit"));
						
						bookoperationList.add(bookoperation);
					}
					
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//�ر�/�ͷ���Դ
					JdbcUtils.close(rs, pstmt, conn);
				}
				//���ط�ҳ���
				return bookoperationList;

			}
	@Override
	public int getBookoperationCount() {
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
					String sql="select count(*) as bookoperationcount from bookoperation";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//ִ��SQL��䣬���ؽ��������
					rs=pstmt.executeQuery();
					//������������:��������е����ݱ��浽������ 
					
					//����һ�����������ڴӽ�����л�ȡ����
					int rowCount=0;
					if(rs.next()){
						//�˴���ͳ���б���������������û��ָ����������������0��ʾ
						rowCount=rs.getInt("bookoperationcount");
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