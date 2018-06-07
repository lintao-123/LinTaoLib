package com.yidu.t235.xm.entity;
/**
 * MemberLevelʵ����
 * @author �����
 * ���ڣ�2018��3��8�� ����4:26:30
 */
public class MemberLevel {
	//�ȼ����
	private int levelNumber;
	//�ȼ�����
	private String levelName;
	//��Ա�ۿ�
	private double memberDiscount;
	//��������
	private String upgradeIntegral;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public MemberLevel(){
		
	}

	/**
	 * �������췽��
	 * @param levelName �ȼ�����
	 * @param memberDiscount ��Ա�ۿ� 
	 * @param upgradeIntegral ��������
	 */
	public MemberLevel(String levelName, double memberDiscount, String upgradeIntegral) {
		super();
		this.levelName = levelName;
		this.memberDiscount = memberDiscount;
		this.upgradeIntegral = upgradeIntegral;
	}

	/**
	 * �������췽��
	 * @param levelNumber �ȼ����
	 * @param levelName �ȼ�����
	 * @param memberDiscount ��Ա�ۿ�
	 * @param upgradeIntegral ��������
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
