package com.yidu.t235.xm.dao;
import java.util.List;
import com.yidu.t235.xm.entity.EnterInventory;
/**
 * ���������ݲ㣺����ͼ�����ӡ��޸ġ�ɾ��������ͼ�����Ʋ����ݿ⡢��ҳ��ѯ����������ͳ��
 * @author �����
 * ���ڣ�2018��3��8�� ����4:22:08
 */
public interface EnterInventoryDao {
	/**
	 * ���ӽ���ͼ��
	 * @param enterInventory ��������
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int add(EnterInventory enterInventory);
	/**
	 * ɾ������ͼ��
	 * @param bookNo ͼ����
	 * @return �ɹ�ɾ��������(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int delete(int bookNo);
	/**
	 * �޸Ľ���ͼ��
	 * @param enterInventory ��������
	 * @return �ɹ��޸ĵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int update(EnterInventory enterInventory);
	/**
	 * �����鼮���ƴ����ݿ����ͼ���Ƿ���ڸ��鼮
	 * @param bookName ͼ������
	 * @return �鼮����(����null��ʾ�����ڸ��鼮)
	 */
	public EnterInventory findByName(String bookName);
	/**
	 * ����ͼ���Ŵ����ݿ����ͼ��
	 * @param bookNo ͼ����
	 * @return ���ҳɹ���ͼ�����(�������null���󣬱�ʾ����ʧ��)
	 */
	public EnterInventory findById(int bookNo);
	/**
	 * ��ҳ��ѯ���������鼮��¼
	 * @param rows ÿҳ����
	 * @param pages ��ǰ����
	 * @return ��ǰҳ�鼮��¼����
	 */
	public List<EnterInventory> findByPage(int rows,int pages);
	/**
	 * ͳ�ƽ������м�¼��
	 * @return
	 */
	public int enterInventoryCount();
	
}
