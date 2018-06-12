package com.yidu.t235.xm.dao;

import java.util.List;
import com.yidu.t235.xm.entity.MemberLevel;
/**
 * ��Ա�ȼ����ݲ㣺��Ա��Ϣ���ӡ��޸ġ�ɾ������ҳ��ѯ����������ͳ��
 * @author �����
 * ���ڣ�2018��3��8�� ����4:36:02
 */
public interface MemberLevelDao {
	/**
	 * ����
	 * @param memberLevel ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int add(MemberLevel memberLevel);
	/**
	 * ɾ��
	 * @param levelNumber �ȼ����
	 * @return �ɹ�ɾ��������(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int delete(int levelNumber);
	/**
	 * �޸�
	 * @param memberLevel ʵ�����
	 * @return �ɹ��޸ĵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int update(MemberLevel memberLevel);
	/**
	 * ��ҳ��ѯ�ȼ������鼮��¼
	 * @param rows ÿҳ����
	 * @param pages ��ǰ����
	 * @return ��ǰҳ�鼮��¼����
	 */
	public List<MemberLevel> findByPage(int rows,int pages);
	/**
	 * ͳ�ƻ�Ա��Ϣ���м�¼��
	 * @return
	 */
	public int memberLevelCount();
}
