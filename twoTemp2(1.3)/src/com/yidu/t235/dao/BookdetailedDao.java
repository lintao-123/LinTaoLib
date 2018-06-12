package com.yidu.t235.dao;

import java.util.List;

import com.yidu.t235.entity.Bookdetailed;


public interface BookdetailedDao {
	/**
     * 将销售订单实体对象添加到数据库bookdetailed表中
     * @param bookdetailed实体对象
     * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
     */
	public int add(Bookdetailed bookdetailed);
	/**
     * 将销售订单实体对象删除到数据库bookdetailed表中
     * @param bookdetailed实体对象
     * @return 成功删除的行数(非0/大于0:表示删除成功，0：删除失败)
     */
	public int delete(int bookno);
	/**
     * 将销售对象修改到数据库表
     * @param bookdetailed 销售对象
     * @return 修改成功的记录数(1:表示修改成功,0:修改失败)
     */
	public int update(Bookdetailed bookdetailed);
	/**
     * 从数据库表中查找指定页的销售对象
     * @param rows 每页的记录数
     * @param pages 页数
     * @return 当前页的图书对象集合
     */
	public List<Bookdetailed> findByPage(int rows,int page);
   
	
	
	/**
	 * 统计记录数
	 * @return 记录数
	 */
	public int getBookdetailedCount();
}
