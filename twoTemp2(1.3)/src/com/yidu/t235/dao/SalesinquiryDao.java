package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Salesinquiry;

public interface SalesinquiryDao {
	/**
     * 将销售订单详情实体对象添加到数据库salesinquiry表中
     * @param salesinquiry实体对象
     * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
     */
	public int add(Salesinquiry salesinquiry);
	/**
     * 根据销售编号从数据库表salesinquiry中删除图书
     * @param ordernumber 销售订单详情编号
     * @return 成功删除的记录数(非0/大于0:表示删除成功，0：删除失败)
     */
	public int delete(int ordernumber);
	/**
     * 将销售订单详情编号对象修改到数据库表中
     * @param salesinquiry 编号对象
     *  @return 修改成功的记录数(1:表示修改成功,0:修改失败)
     */
	public int update(Salesinquiry salesinquiry);
	/**
     * 从数据库表中查找指定页的图书对象
     * @param rows 每页的记录数
     * @param pages页数
     * @return 当前页的图书对象集合
     */
	public List<Salesinquiry> findByPage(int rows,int page);
	/**
	 * 统计记录数
	 * @return 记录数
	 */
	public int getSalesinquiryCount();
}
