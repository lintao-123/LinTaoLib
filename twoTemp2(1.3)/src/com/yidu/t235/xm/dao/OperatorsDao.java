package com.yidu.t235.xm.dao;

import java.util.List;

import com.yidu.t235.xm.entity.Operators;

public interface OperatorsDao {
	public int add(Operators operators);
	public int delete(int userId);
	public int update(Operators operators);
	public List<Operators> findPage(int rows,int page);
	public int getCount();
}
