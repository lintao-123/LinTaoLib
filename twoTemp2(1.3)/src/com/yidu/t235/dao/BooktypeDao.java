package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Booktype;

public interface BooktypeDao {
    //增
	public int add(Booktype booktype);
	//删
	public int delete(int typeid);
	//改
	public int update(Booktype booktype);
	//分页查
	public List<Booktype> findByPage(int rows, int page);
	//统计记录数
    public int getBooktypeCount();
}
