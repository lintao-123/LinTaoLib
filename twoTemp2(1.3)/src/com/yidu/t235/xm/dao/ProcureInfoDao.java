package com.yidu.t235.xm.dao;

import java.util.List;

import com.yidu.t235.xm.entity.ProcureInfo;
/**
 * �ɹ�������ӿ�
 * @author Administrator
 *
 */
public interface ProcureInfoDao {
	/**
	 * ���ɹ�����������ӵ����ݿ����
	 * @param procureInfo �ɹ���������
	 * @return���ش���1����ʾ��ӳɹ�������0�����ʧ��
	 */
	public int add (ProcureInfo procureInfo);
	/**
	 *����Ŵ����ݿ����ɾ��
	 * @param tempId ���
	 * @return ���ش���1����ʾɾ���ɹ�������0��ɾ��ʧ��
	 */
	public int delete(int tempId);
	/**
	 * ���ɹ����������޸ĵ����ݿ�
	 * @param procureInfo �ɹ���������
	 * @return ���ش���1����ʾ�޸ĳɹ�������0���޸�ʧ��
	 */
	public int update(ProcureInfo procureInfo);
	/**
	 * �����ݿ���в���ָ��ҳ
	 * @param rows ����
	 * @param page ҳ��
	 * @return ���ؼ��϶���
	 */
	public List<ProcureInfo> findPage(int rows,int page);
	/**
	 * ͳ�����ݿ���¼��
	 * @return ���ؼ�¼��
	 */
	public int getCount();
}
