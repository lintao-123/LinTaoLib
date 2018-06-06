package xmbook.dao;

import java.util.List;

import xmbook.entity.Salestable;


/**
 * ���۱�����ݲ�ӿ���:ʵ�ֶ����۱������ɾ���ġ������
 * @author ����
 * @version 1.0
 * ���ڣ�2018-2-24
 */
public interface SalestableDao {
	/**
	 * ������ʵ�������ӵ����ݿ�salestable����
	 * @param salestable ʵ�����
	 * @return �ɹ���ӵ�����(��0/����0:��ʾ��ӳɹ���0�����ʧ��)
	 */
	public int add(Salestable salestable);
	/**
	 * �������۱�Ŵ����ݿ��salestable��ɾ������
	 * @param salestableNo ���۱��
	 * @return �ɹ�ɾ���ļ�¼��(��0/����0:��ʾɾ���ɹ���0��ɾ��ʧ��)
	 */
	public int delete(int bookno);
	/**
	 * �����۶����޸ĵ����ݿ��
	 * @param salestable ���۶���
	 * @return �޸ĳɹ��ļ�¼��(1:��ʾ�޸ĳɹ�,0:�޸�ʧ��)
	 */
	public int update(Salestable salestable);
	/**
	 * �����ݿ���в���ָ��ҳ�����۶���
	 * @param rows ÿҳ�ļ�¼��
	 * @param pages ҳ��
	 * @return ��ǰҳ�����۶��󼯺�
	 */
	public List<Salestable> findByPage(int rows,int pages);
	/**
	 * �����ݿ���и���ͼ�����Ʋ�ѯͼ�����
	 * @param bookname ͼ������
	 * @return �������۶��� 
	 */
	public  Salestable  findName(String bookname);
	/**
	 * �����ݿ���и���ͼ���Ų�ѯͼ�����
	 * @param bookno ͼ����
	 * @return �������۶��� 
	 */
	public  Salestable  findId(int bookno);
	
	/**
	 * ͳ�����۱��¼������ 
	 * @return ���۱��¼��
	 */
	public int salestableCount();
}
