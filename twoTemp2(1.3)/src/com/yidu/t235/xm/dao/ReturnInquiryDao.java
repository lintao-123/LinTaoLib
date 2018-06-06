package com.yidu.t235.xm.dao;

import java.util.List;

import com.yidu.t235.xm.entity.ReturnInquiry;

public interface ReturnInquiryDao {
	/**
	 * ����˻�����
	 * @param returnInquiry �˻�����
	 * @return �����������
	 */
	public int add(ReturnInquiry returnInquiry);
	/**
	 * ͨ������ɾ���˻�����
	 * @param orderNumber �˻����ţ�������
	 * @return ����ɾ������
	 */
	public int delete(int tempId);
	/**
	 * �޸��˻�����
	 * @param returnInquiry �˻�����
	 * @return �����޸�����
	 */
	public int update(ReturnInquiry returnInquiry);
	/**
	 * ����Ų��Ҷ���
	 * @param tempId ���
	 * @return  ���ز��Ҷ���
	 */
	public ReturnInquiry findById(int tempId);
	/**
	 * ��ʾ��ҳ
	 * @param rows ����
	 * @param page ҳ��
	 * @return ���ؼ��϶���
	 */
	
	public  List<ReturnInquiry> findPage(int rows ,int page);
	/**
	 * �������������
	 * @return ����������
	 */
	public int getCount();
}
