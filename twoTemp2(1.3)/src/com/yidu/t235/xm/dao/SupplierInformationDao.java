package com.yidu.t235.xm.dao;

import java.util.List;

import com.yidu.t235.xm.entity.SupplierInformation;
/**
 * �����̱����ݲ㣺��������Ϣ���ӡ��޸ġ�ɾ�������ݹ����̱�Ų����ݿ⡢��ҳ��ѯ�����ݹ����̱�ͳ��
 * @author �����
 * ���ڣ�2018��3��8�� ����4:39:59
 */
public interface SupplierInformationDao {
	/**
	 * ����
	 * @param supplierInformation ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int add(SupplierInformation supplierInformation);
	/**
	 * ɾ��
	 * @param memberId �ȼ����
	 * @return �ɹ�ɾ��������(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int delete(int supplierNumber);
	/**
	 * �޸�
	 * @param supplierInformation ʵ�����
	 * @return �ɹ��޸ĵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int update(SupplierInformation supplierInformation);
	/**
	 * ���ݹ����̱�Ŵ����ݿ���ҹ�����
	 * @param supplierNumber �����̱��
	 * @return ���ҳɹ��Ĺ����̶���(�������null���󣬱�ʾ����ʧ��)
	 */
	public SupplierInformation findById(int supplierNumber);
	/**
	 * ��ҳ��ѯ�����̱�����Ϣ��¼
	 * @param rows ÿҳ����
	 * @param pages ��ǰ����
	 * @return ��ǰҳ��Ϣ��¼����
	 */
	public List<SupplierInformation> findByPage(int rows,int pages);
	/**
	 * ͳ�ƹ����̱��м�¼��
	 * @return
	 */
	public int supplierInformationCount();
}
