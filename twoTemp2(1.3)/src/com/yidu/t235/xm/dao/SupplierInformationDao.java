package com.yidu.t235.xm.dao;

import java.util.List;

import com.yidu.t235.xm.entity.SupplierInformation;
/**
 * 供货商表数据层：供货商信息增加、修改、删除、根据供货商编号查数据库、分页查询、根据供货商表统计
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:39:59
 */
public interface SupplierInformationDao {
	/**
	 * 增加
	 * @param supplierInformation 实体对象
	 * @return 成功添加的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int add(SupplierInformation supplierInformation);
	/**
	 * 删除
	 * @param memberId 等级编号
	 * @return 成功删除的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int delete(int supplierNumber);
	/**
	 * 修改
	 * @param supplierInformation 实体对象
	 * @return 成功修改的行数(非0/大于0:表示添加成功，0：添加失败)
	 */
	public int update(SupplierInformation supplierInformation);
	/**
	 * 根据供货商编号从数据库查找供货商
	 * @param supplierNumber 供货商编号
	 * @return 查找成功的供货商对象(如果返回null对象，表示查找失败)
	 */
	public SupplierInformation findById(int supplierNumber);
	/**
	 * 分页查询供货商表中信息记录
	 * @param rows 每页行数
	 * @param pages 当前行数
	 * @return 当前页信息记录集合
	 */
	public List<SupplierInformation> findByPage(int rows,int pages);
	/**
	 * 统计供货商表中记录数
	 * @return
	 */
	public int supplierInformationCount();
}
