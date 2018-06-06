package com.yidu.t235.xm.dao;

import java.util.List;
import com.yidu.t235.xm.entity.MemberInfo;
/**
 * ��Ա��Ϣ���ݲ㣺��Ա��Ϣ���ӡ��޸ġ�ɾ�������ݻ�Ա��Ų����ݿ⡢��ҳ��ѯ�����ݻ�Ա��Ϣ��ͳ��
 * @author �����
 * ���ڣ�2018��3��8�� ����4:21:48
 */
public interface MemberInfoDao {
	/**
	 * ����
	 * @param memberInfo ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int add(MemberInfo memberInfo);
	/**
	 * ɾ��
	 * @param memberId ��Ա���
	 * @return �ɹ�ɾ��������(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int delete(int memberId);
	/**
	 * �޸�
	 * @param memberInfo ʵ�����
	 * @return �ɹ��޸ĵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int update(MemberInfo memberInfo);
	/**
	 * ���ݻ�Ա��Ŵ����ݿ���һ�Ա
	 * @param memberId ��Ա���
	 * @return ���ҳɹ��Ļ�Ա����(�������null���󣬱�ʾ����ʧ��)
	 */
	public MemberInfo findById(int memberId);
	/**
	 * ��ҳ��ѯ��Ϣ������Ϣ��¼
	 * @param rows ÿҳ����
	 * @param pages ��ǰ����
	 * @return ��ǰҳ��Ϣ��¼����
	 */
	public List<MemberInfo> findByPage(int rows,int pages);
	/**
	 * ͳ�ƻ�Ա��Ϣ���м�¼��
	 * @return
	 */
	public int memberInfoCount();
}
