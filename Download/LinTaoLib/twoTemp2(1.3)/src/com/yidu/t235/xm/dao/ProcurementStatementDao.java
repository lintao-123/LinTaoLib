package com.yidu.t235.xm.dao;

import java.util.List;

import com.yidu.t235.xm.entity.ProcurementStatement;
/**
 * 采购订单详情表接口
 * @author Administrator
 *
 */
public interface ProcurementStatementDao {
	/**
	 * 添加采购订单详情到数据库表中
	 * @param procurement 采购订单详情对象
	 * @return 返回大于1,表示添加成功 返回0,则添加表示失败
	 */
	public int add(ProcurementStatement procurement);
	/**
	 * 根据编号删除订单详情到数据库中
	 * @param tempId 编号
	 * @return 返回大于1,表示删除成功 返回0,则删除表示失败
	 */
	public int delete(int tempId);
	/**
	 * 将采购订单详情修改到数据库表中
	 * @param procurement 采购订单详情
	 * @return 返回大于1,表示修改成功 返回0,则修改表示失败
	 */
	public int update(ProcurementStatement procurement);
	/**
	 * 从数据库表中查找出指定页
	 * @param rows 每页记录数
	 * @param page 页数
	 * @return 返回集合对象
	 */
	public List<ProcurementStatement> findPage(int rows ,int page);
	/**
	 * 统计采购订单详情表的记录数
	 * @return
	 */
	public int getCount();
}
