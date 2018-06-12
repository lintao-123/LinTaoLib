package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Bookoperation;

public interface BookoperationDao {
	//增
	public int add(Bookoperation bookoperation);
	//删
	public int delete(int bookno);
	//改
	public int update(Bookoperation bookoperation);
	//分页查
	public List<Bookoperation> findByPage(int rows,int page);
	//统计记录数
    public int getBookoperationCount();

}
