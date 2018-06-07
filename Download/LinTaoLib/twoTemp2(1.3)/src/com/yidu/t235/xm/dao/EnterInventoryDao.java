package com.yidu.t235.xm.dao;
import java.util.List;
import com.yidu.t235.xm.entity.EnterInventory;
/**
 * 进货表数据层：进货图书增加、修改、删除、根据图书名称查数据库、分页查询、根据书名统计
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:22:08
 */
public interface EnterInventoryDao {
	/**
	 * 增加进货图书
	 * @param enterInventory 进货对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int add(EnterInventory enterInventory);
	/**
	 * 删除进货图书
	 * @param bookNo 图书编号
	 * @return 成功删除的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int delete(int bookNo);
	/**
	 * 修改进货图书
	 * @param enterInventory 进货对象
	 * @return 成功修改的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int update(EnterInventory enterInventory);
	/**
	 * 根据书籍名称从数据库查找图书是否存在该书籍
	 * @param bookName 图书名称
	 * @return 书籍对象(返回null表示不存在该书籍)
	 */
	public EnterInventory findByName(String bookName);
	/**
	 * 根据图书编号从数据库查找图书
	 * @param bookNo 图书编号
	 * @return 查找成功的图书对象(如果返回null对象，表示查找失败)
	 */
	public EnterInventory findById(int bookNo);
	/**
	 * 分页查询进货表中书籍记录
	 * @param rows 每页行数
	 * @param pages 当前行数
	 * @return 当前页书籍记录集合
	 */
	public List<EnterInventory> findByPage(int rows,int pages);
	/**
	 * 统计进货表中记录数
	 * @return
	 */
	public int enterInventoryCount();
	
}
