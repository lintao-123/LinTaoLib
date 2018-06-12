package com.yidu.t235.xm.dao;

import java.util.List;
import com.yidu.t235.xm.entity.MemberLevel;
/**
 * 会员等级数据层：会员信息增加、修改、删除、分页查询、根据书名统计
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:36:02
 */
public interface MemberLevelDao {
	/**
	 * 增加
	 * @param memberLevel 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int add(MemberLevel memberLevel);
	/**
	 * 删除
	 * @param levelNumber 等级编号
	 * @return 成功删除的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int delete(int levelNumber);
	/**
	 * 修改
	 * @param memberLevel 实体对象
	 * @return 成功修改的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int update(MemberLevel memberLevel);
	/**
	 * 分页查询等级表中书籍记录
	 * @param rows 每页行数
	 * @param pages 当前行数
	 * @return 当前页书籍记录集合
	 */
	public List<MemberLevel> findByPage(int rows,int pages);
	/**
	 * 统计会员信息表中记录数
	 * @return
	 */
	public int memberLevelCount();
}
