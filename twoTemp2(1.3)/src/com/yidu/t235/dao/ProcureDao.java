package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Procure;

public interface ProcureDao {
    //��
	public int add(Procure procure);
	//ɾ
	public int delete(int bookid);
	//��
	public int update(Procure procure);
	//��ҳ��
	public List<Procure> findByPage(int rows,int page);
	//ͳ�Ƽ�¼��
	public int getProcureCount();
}
