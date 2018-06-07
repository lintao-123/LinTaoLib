package com.yidu.t235.xm.dao;

import java.util.List;
import com.yidu.t235.xm.entity.MemberInfo;
/**
 * 会员信息数据层：会员信息增加、修改、删除、根据会员编号查数据库、分页查询、根据会员信息表统计
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:21:48
 */
public interface MemberInfoDao {
	/**
	 * 增加
	 * @param memberInfo 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int add(MemberInfo memberInfo);
	/**
	 * 删除
	 * @param memberId 会员编号
	 * @return 成功删除的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int delete(int memberId);
	/**
	 * 修改
	 * @param memberInfo 实体对象
	 * @return 成功修改的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int update(MemberInfo memberInfo);
	/**
	 * 根据会员编号从数据库查找会员
	 * @param memberId 会员编号
	 * @return 查找成功的会员对象(如果返回null对象，表示查找失败)
	 */
	public MemberInfo findById(int memberId);
	/**
	 * 分页查询信息表中信息记录
	 * @param rows 每页行数
	 * @param pages 当前行数
	 * @return 当前页信息记录集合
	 */
	public List<MemberInfo> findByPage(int rows,int pages);
	/**
	 * 统计会员信息表中记录数
	 * @return
	 */
	public int memberInfoCount();
}
