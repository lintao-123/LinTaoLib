package com.yidu.t235.xm.dao;

import java.util.List;

import com.yidu.t235.xm.entity.ReturnInquiry;

public interface ReturnInquiryDao {
	/**
	 * 添加退货对象
	 * @param returnInquiry 退货对象
	 * @return 返回添加行数
	 */
	public int add(ReturnInquiry returnInquiry);
	/**
	 * 通过主键删除退货对象
	 * @param orderNumber 退货单号（主键）
	 * @return 返回删除行数
	 */
	public int delete(int tempId);
	/**
	 * 修改退货对象
	 * @param returnInquiry 退货对象
	 * @return 返回修改行数
	 */
	public int update(ReturnInquiry returnInquiry);
	/**
	 * 按编号查找对象
	 * @param tempId 编号
	 * @return  返回查找对象
	 */
	public ReturnInquiry findById(int tempId);
	/**
	 * 显示分页
	 * @param rows 行数
	 * @param page 页数
	 * @return 返回集合对象
	 */
	
	public  List<ReturnInquiry> findPage(int rows ,int page);
	/**
	 * 获得数据总行数
	 * @return 返回总行数
	 */
	public int getCount();
}
