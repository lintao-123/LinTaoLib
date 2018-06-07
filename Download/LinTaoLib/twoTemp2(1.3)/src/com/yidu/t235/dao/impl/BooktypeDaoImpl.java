package com.yidu.t235.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.yidu.t235.dao.BooktypeDao;
import com.yidu.t235.entity.Booktype;
import com.yidu.t235.utils.JdbcUtils;

public class BooktypeDaoImpl implements BooktypeDao{

	@Override
	public int add(Booktype booktype) {
		//�������ݿ����Ӷ���
		Connection conn=null;
		//����Ԥ����������
		PreparedStatement pstmt=null;
		
		try {
			
		
			//ʵ�������ݿ����Ӷ���
			conn=JdbcUtils.getConnectoin();
			//��дsql���
			String sql="insert into booktype values(?,?)";
			//ʵ����Ԥ����������
			pstmt=conn.prepareStatement(sql);
			pstmt .setString(1, booktype.getTypename());
			pstmt.setString(2,booktype.getTypedetailed() );
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}finally {
			JdbcUtils.close(null, pstmt, conn);
		}
			
	}
	@Override
	public int delete(int typeid) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="delete from booktype where typeid=?";
					//ʵ����Ԥ�������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setInt(1, typeid);
					//ִ��SQL����
					//��ִ�к������ݿ����Ӱ���������Ϊ����ֵ����
					return pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					JdbcUtils.close(null, pstmt, conn);
				}
			}

	@Override
	public int update(Booktype booktype) {
		        //�������ݿ����Ӷ���
				Connection conn=null;
				//����Ԥ����������
				PreparedStatement pstmt=null;
				
				try {
					//ʵ�������ݿ����Ӷ���
					conn=JdbcUtils.getConnectoin();
					//��дsql���
					String sql="update booktype set typename=?,typedetailed=? where typeid=?";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//����Ԥ����������
					pstmt.setString(1, booktype.getTypename());
					pstmt.setString(2, booktype.getTypedetailed());
					pstmt.setInt(3, booktype.getTypeid());
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
	public List<Booktype> findByPage(int rows, int page) {
		        //����һ�����ż��ϣ��������浱ǰҳ�Ĳ�����Ϣ
				List<Booktype> booktypeList=new ArrayList<Booktype>();
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
					String sql="select top (?) * from booktype "
							+ "where typeid not in("
							+ "select top (?) typeid from booktype)";
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
						Booktype booktype=new Booktype();
						booktype.setTypeid(rs.getInt("typeid"));
						booktype.setTypename(rs.getString("typename"));
						booktype.setTypedetailed(rs.getString("typedetailed"));
						
						booktypeList.add(booktype);
					}
					
				} catch (SQLException e) {
					throw new RuntimeException(e.getMessage(),e);
				} finally{
					//�ر�/�ͷ���Դ
					JdbcUtils.close(rs, pstmt, conn);
				}
				//���ط�ҳ���
				return booktypeList;
				
			}

	@Override
	public int getBooktypeCount() {
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
					String sql="select count(*) as booktypecount from booktype";
					//ʵ����Ԥ����������
					pstmt=conn.prepareStatement(sql);
					//ִ��SQL��䣬���ؽ��������
					rs=pstmt.executeQuery();
					//������������:��������е����ݱ��浽������ 
					
					//����һ�����������ڴӽ�����л�ȡ����
					int rowCount=0;
					if(rs.next()){
						//�˴���ͳ���б���������������û��ָ����������������0��ʾ
						rowCount=rs.getInt("booktypecount");
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