package com.yidu.t235.xm.dao;
/**
 * ��������ݲ㣺���ӿ�桢�޸Ŀ�桢����������ѯ��桢��ҳ��ѯ��桢ͳ�Ƽ�¼������������ͳ�ƿ��
 */
import java.util.List;
import com.yidu.t235.xm.entity.StockCheck;

public interface StockCheckDao {
	/**
	 * ���ӿ��
	 * @param stockCheck ������
	 * @return �����Ƿ�ɹ�
	 */
	public int add(StockCheck stockCheck);
	/**
	 * �����鼮�������޸Ŀ������
	 * @param bookName �鼮����
	 * @param number ���ӵĿ����
	 * @return 1���޸ĳɹ���0���޸�ʧ��
	 */
	public int update(String bookName,int number);
	/**
	 * �����鼮���Ʋ�ѯ������Ƿ���ڸ��鼮
	 * @param bookName �鼮����
	 * @return �鼮����(����null��ʾ�����ڸ��鼮)
	 */
	public StockCheck findByName(String bookName);
	/**
	 * ��ҳ��ѯ�������鼮��¼
	 * @param rows ÿҳ����
	 * @param pages ��ǰҳ��
	 * @return ��ǰҳ�鼮��¼����
	 */
	public List<StockCheck> findByPage(int rows,int pages);
	/**
	 * ͳ�ƿ����м�¼��
	 * @return �����м�¼��
	 */
	public int stockCheckCount();
}
