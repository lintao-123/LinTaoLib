package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Bookdetailed;


public interface BookdetailedDao {
	/**
     * �����۶���ʵ�������ӵ����ݿ�bookdetailed����
     * @param bookdetailedʵ�����
     * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
     */
	public int add(Bookdetailed bookdetailed);
	/**
     * �����۶���ʵ�����ɾ�������ݿ�bookdetailed����
     * @param bookdetailedʵ�����
     * @return �ɹ�ɾ��������(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
     */
	public int delete(int bookno);
	/**
     * �����۶����޸ĵ����ݿ��
     * @param bookdetailed ���۶���
     * @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
     */
	public int update(Bookdetailed bookdetailed);
	/**
     * �����ݿ���в���ָ��ҳ�����۶���
     * @param rows ÿҳ�ļ�¼��
     * @param pages ҳ��
     * @return ��ǰҳ��ͼ����󼯺�
     */
	public List<Bookdetailed> findByPage(int rows,int page);
   
	
	
	/**
	 * ͳ�Ƽ�¼��
	 * @return ��¼��
	 */
	public int getBookdetailedCount();
}
