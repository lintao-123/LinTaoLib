package com.yidu.t235.xm.entity;
/**
 * MemberInfo实体类
 * @author 朱瑾涛
 * 日期：2018年3月8日 下午4:33:42
 */
public class MemberInfo {
	//会员编号
	private int memberId;
	//会员姓名
	private String memberName;
	//会员性别
	private String memberSex;
	//会员电话
	private String memberTelephone;
	//会员等级
	private String memberLevel;
	//会员积分
	private double memberIntegral;
	
	/**
	 * 默认构造方法
	 */
	public MemberInfo(){
		
	}

	/**
	 * 带叁构造方法
	 * @param memberName 会员姓名
	 * @param memberSex 会员性别
	 * @param memberAge 会员年龄
	 * @param memberTelephone 会员电话
	 * @param memberAddress 会员地址
	 * @param memberLevel 会员等级
	 * @param memberIntegral 会员积分
	 */
	public MemberInfo(String memberName, String memberSex, String memberTelephone, String memberLevel, 
			double memberIntegral) {
		this.memberName = memberName;
		this.memberSex = memberSex;
		this.memberTelephone = memberTelephone;
		this.memberLevel = memberLevel;
		this.memberIntegral = memberIntegral;
	}

	/**
	 * 带叁构造方法
	 * @param memberId 会员编号
	 * @param memberName 会员姓名
	 * @param memberSex 会员性别
	 * @param memberAge 会员年龄
	 * @param memberTelephone 会员电话
	 * @param memberAddress 会员地址
	 * @param memberLevel 会员等级
	 * @param memberIntegral 会员积分
	 */
	public MemberInfo(int memberId, String memberName, String memberSex, String memberTelephone, String memberLevel, 
			double memberIntegral) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberSex = memberSex;
		this.memberTelephone = memberTelephone;
		this.memberLevel = memberLevel;
		this.memberIntegral = memberIntegral;
	}

	//自动生成
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}

	public String getMemberTelephone() {
		return memberTelephone;
	}

	public void setMemberTelephone(String memberTelephone) {
		this.memberTelephone = memberTelephone;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public double getMemberIntegral() {
		return memberIntegral;
	}

	public void setMemberIntegral(double memberIntegral) {
		this.memberIntegral = memberIntegral;
	}
}
