package com.yidu.t235.entity;

public class Booktype {
	//������
	private int typeid; 
    //��������
	private String typename;
	//��������
    private String typedetailed; 
    
    /**
     * Ĭ�Ϲ��췽��
     */
    public Booktype(){
    	
    }
     /**
      * 
      * @param typeid ������
      * @param typename ��������
      * @param typedetailed ��������
      */
	public Booktype(int typeid, String typename, String typedetailed) {
		this.typeid = typeid;
		this.typename = typename;
		this.typedetailed = typedetailed;
	}
	
	
	
    public Booktype(String typename, String typedetailed) {
		this.typename = typename;
		this.typedetailed = typedetailed;
	}
	//�Զ�����setter/getter����
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getTypedetailed() {
		return typedetailed;
	}
	public void setTypedetailed(String typedetailed) {
		this.typedetailed = typedetailed;
	}
	
}
