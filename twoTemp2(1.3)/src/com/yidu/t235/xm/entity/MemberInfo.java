package com.yidu.t235.xm.entity;
/**
 * MemberInfoʵ����
 * @author �����
 * ���ڣ�2018��3��8�� ����4:33:42
 */
public class MemberInfo {
	//��Ա���
	private int memberId;
	//��Ա����
	private String memberName;
	//��Ա�Ա�
	private String memberSex;
	//��Ա�绰
	private String memberTelephone;
	//��Ա�ȼ�
	private String memberLevel;
	//��Ա����
	private double memberIntegral;
	
	/**
	 * Ĭ�Ϲ��췽��
	 */
	public MemberInfo(){
		
	}

	/**
	 * �������췽��
	 * @param memberName ��Ա����
	 * @param memberSex ��Ա�Ա�
	 * @param memberAge ��Ա����
	 * @param memberTelephone ��Ա�绰
	 * @param memberAddress ��Ա��ַ
	 * @param memberLevel ��Ա�ȼ�
	 * @param memberIntegral ��Ա����
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
	 * �������췽��
	 * @param memberId ��Ա���
	 * @param memberName ��Ա����
	 * @param memberSex ��Ա�Ա�
	 * @param memberAge ��Ա����
	 * @param memberTelephone ��Ա�绰
	 * @param memberAddress ��Ա��ַ
	 * @param memberLevel ��Ա�ȼ�
	 * @param memberIntegral ��Ա����
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

	//�Զ�����
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
