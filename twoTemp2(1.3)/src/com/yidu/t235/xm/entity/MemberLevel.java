package com.yidu.t235.xm.entity;
/**
 * MemberLevel实体类
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:26:30
 */
public class MemberLevel {
	//等级编号
	private int levelNumber;
	//等级名称
	private String levelName;
	//会员折扣
	private double memberDiscount;
	//升级积分
	private String upgradeIntegral;
	
	/**
	 * 默认构造方法
	 */
	public MemberLevel(){
		
	}

	/**
	 * 带叁构造方法
	 * @param levelName 等级名称
	 * @param memberDiscount 会员折扣 
	 * @param upgradeIntegral 升级积分
	 */
	public MemberLevel(String levelName, double memberDiscount, String upgradeIntegral) {
		super();
		this.levelName = levelName;
		this.memberDiscount = memberDiscount;
		this.upgradeIntegral = upgradeIntegral;
	}

	/**
	 * 带叁构造方法
	 * @param levelNumber 等级编号
	 * @param levelName 等级名称
	 * @param memberDiscount 会员折扣
	 * @param upgradeIntegral 升级积分
	 */
	public MemberLevel(int levelNumber, String levelName, double memberDiscount, String upgradeIntegral) {
		super();
		this.levelNumber = levelNumber;
		this.levelName = levelName;
		this.memberDiscount = memberDiscount;
		this.upgradeIntegral = upgradeIntegral;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public double getMemberDiscount() {
		return memberDiscount;
	}

	public void setMemberDiscount(double memberDiscount) {
		this.memberDiscount = memberDiscount;
	}

	public String getUpgradeIntegral() {
		return upgradeIntegral;
	}

	public void setUpgradeIntegral(String upgradeIntegral) {
		this.upgradeIntegral = upgradeIntegral;
	}
}
