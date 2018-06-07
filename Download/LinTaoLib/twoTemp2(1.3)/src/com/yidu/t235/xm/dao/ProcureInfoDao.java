package com.yidu.t235.xm.dao;

import java.util.List;

import com.yidu.t235.xm.entity.ProcureInfo;
/**
 * 采购订单表接口
 * @author Administrator
 *
 */
public interface ProcureInfoDao {
	/**
	 * 将采购订单对象添加到数据库表中
	 * @param procureInfo 采购订单对象
	 * @return返回大于1，表示添加成功，返回0则添加失败
	 */
	public int add (ProcureInfo procureInfo);
	/**
	 *按编号从数据库表中删除
	 * @param tempId 编号
	 * @return 返回大于1，表示删除成功，返回0则删除失败
	 */
	public int delete(int tempId);
	/**
	 * 将采购订单对象修改到数据库
	 * @param procureInfo 采购订单对象
	 * @return 返回大于1，表示修改成功，返回0则修改失败
	 */
	public int update(ProcureInfo procureInfo);
	/**
	 * 从数据库表中查找指定页
	 * @param rows 行数
	 * @param page 页数
	 * @return 返回集合对象
	 */
	public List<ProcureInfo> findPage(int rows,int page);
	/**
	 * 统计数据库表记录数
	 * @return 返回记录数
	 */
	public int getCount();
}
