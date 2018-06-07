package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Salesinquiry;

public interface SalesinquiryDao {
	/**
     * �����۶�������ʵ�������ӵ����ݿ�salesinquiry����
     * @param salesinquiryʵ�����
     * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
     */
	public int add(Salesinquiry salesinquiry);
	/**
     * �������۱�Ŵ����ݿ��salesinquiry��ɾ��ͼ��
     * @param ordernumber ���۶���������
     * @return �ɹ�ɾ���ļ�¼��(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
     */
	public int delete(int ordernumber);
	/**
     * �����۶��������Ŷ����޸ĵ����ݿ����
     * @param salesinquiry ��Ŷ���
     *  @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
     */
	public int update(Salesinquiry salesinquiry);
	/**
     * �����ݿ���в���ָ��ҳ��ͼ�����
     * @param rows ÿҳ�ļ�¼��
     * @param pagesҳ��
     * @return ��ǰҳ��ͼ����󼯺�
     */
	public List<Salesinquiry> findByPage(int rows,int page);
	/**
	 * ͳ�Ƽ�¼��
	 * @return ��¼��
	 */
	public int getSalesinquiryCount();
}
