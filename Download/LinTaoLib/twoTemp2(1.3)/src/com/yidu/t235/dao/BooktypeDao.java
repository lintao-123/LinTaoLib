package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Booktype;

public interface BooktypeDao {
    //��
	public int add(Booktype booktype);
	//ɾ
	public int delete(int typeid);
	//��
	public int update(Booktype booktype);
	//��ҳ��
	public List<Booktype> findByPage(int rows, int page);
	//ͳ�Ƽ�¼��
    public int getBooktypeCount();
}
