package xmbook.dao;

import java.util.List;

import xmbook.entity.Salestable;


/**
 * 销售表的数据层接口类:实现对销售表的增、删、改、查操作
 * @author 刘斌
 * @version 1.0
 * 日期：2018-2-24
 */
public interface SalestableDao {
	/**
	 * 将销售实体对象添加到数据库salestable表中
	 * @param salestable 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int add(Salestable salestable);
	/**
	 * 根据销售编号从数据库表salestable中删除销售
	 * @param salestableNo 销售编号
	 * @return 成功删除的记录数(非0/大于0:表示删除成功，0：删除失败)
	 */
	public int delete(int bookno);
	/**
	 * 将销售对象修改到数据库表
	 * @param salestable 销售对象
	 * @return 修改成功的记录数(1:表示修改成功,0:修改失败)
	 */
	public int update(Salestable salestable);
	/**
	 * 从数据库表中查找指定页的销售对象
	 * @param rows 每页的记录数
	 * @param pages 页数
	 * @return 当前页的销售对象集合
	 */
	public List<Salestable> findByPage(int rows,int pages);
	/**
	 * 从数据库表中根据图书名称查询图书对象
	 * @param bookname 图书名称
	 * @return 返回销售对象 
	 */
	public  Salestable  findName(String bookname);
	/**
	 * 从数据库表中根据图书编号查询图书对象
	 * @param bookno 图书编号
	 * @return 返回销售对象 
	 */
	public  Salestable  findId(int bookno);
	
	/**
	 * 统计销售表记录数方法 
	 * @return 销售表记录数
	 */
	public int salestableCount();
}
