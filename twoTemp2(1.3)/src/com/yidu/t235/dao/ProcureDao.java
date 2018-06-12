package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Procure;

public interface ProcureDao {
    //增
	public int add(Procure procure);
	//删
	public int delete(int bookid);
	//改
	public int update(Procure procure);
	//分页查
	public List<Procure> findByPage(int rows,int page);
	//统计记录数
	public int getProcureCount();
}
