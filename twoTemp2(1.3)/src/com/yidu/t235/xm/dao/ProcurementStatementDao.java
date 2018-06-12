package com.yidu.t235.xm.dao;

import java.util.List;

import com.yidu.t235.xm.entity.ProcurementStatement;
/**
 * �ɹ����������ӿ�
 * @author Administrator
 *
 */
public interface ProcurementStatementDao {
	/**
	 * ��Ӳɹ��������鵽���ݿ����
	 * @param procurement �ɹ������������
	 * @return ���ش���1,��ʾ��ӳɹ� ����0,����ӱ�ʾʧ��
	 */
	public int add(ProcurementStatement procurement);
	/**
	 * ���ݱ��ɾ���������鵽���ݿ���
	 * @param tempId ���
	 * @return ���ش���1,��ʾɾ���ɹ� ����0,��ɾ����ʾʧ��
	 */
	public int delete(int tempId);
	/**
	 * ���ɹ����������޸ĵ����ݿ����
	 * @param procurement �ɹ���������
	 * @return ���ش���1,��ʾ�޸ĳɹ� ����0,���޸ı�ʾʧ��
	 */
	public int update(ProcurementStatement procurement);
	/**
	 * �����ݿ���в��ҳ�ָ��ҳ
	 * @param rows ÿҳ��¼��
	 * @param page ҳ��
	 * @return ���ؼ��϶���
	 */
	public List<ProcurementStatement> findPage(int rows ,int page);
	/**
	 * ͳ�Ʋɹ����������ļ�¼��
	 * @return
	 */
	public int getCount();
}
