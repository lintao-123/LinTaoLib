package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Bookoperation;

public interface BookoperationDao {
	//��
	public int add(Bookoperation bookoperation);
	//ɾ
	public int delete(int bookno);
	//��
	public int update(Bookoperation bookoperation);
	//��ҳ��
	public List<Bookoperation> findByPage(int rows,int page);
	//ͳ�Ƽ�¼��
    public int getBookoperationCount();

}
