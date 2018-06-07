package com.yidu.t235.xm.dao;
/**
 * 库存表的数据层：增加库存、修改库存、根据书名查询库存、分页查询库存、统计记录数、根据书名统计库存
 */
import java.util.List;
import com.yidu.t235.xm.entity.StockCheck;

public interface StockCheckDao {
	/**
	 * 增加库存
	 * @param stockCheck 库存对象
	 * @return 增加是否成功
	 */
	public int add(StockCheck stockCheck);
	/**
	 * 根据书籍名称来修改库存数量
	 * @param bookName 书籍名称
	 * @param number 增加的库存量
	 * @return 1：修改成功；0：修改失败
	 */
	public int update(String bookName,int number);
	/**
	 * 根据书籍名称查询库存中是否存在该书籍
	 * @param bookName 书籍名称
	 * @return 书籍对象(返回null表示不存在该书籍)
	 */
	public StockCheck findByName(String bookName);
	/**
	 * 分页查询库存表中书籍记录
	 * @param rows 每页行数
	 * @param pages 当前页码
	 * @return 当前页书籍记录集合
	 */
	public List<StockCheck> findByPage(int rows,int pages);
	/**
	 * 统计库存表中记录数
	 * @return 库存表中记录数
	 */
	public int stockCheckCount();
}
