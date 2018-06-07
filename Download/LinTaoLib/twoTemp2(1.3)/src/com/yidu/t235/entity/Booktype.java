package com.yidu.t235.entity;

public class Booktype {
	//种类编号
	private int typeid; 
    //种类名称
	private String typename;
	//种类详情
    private String typedetailed; 
    
    /**
     * 默认构造方法
     */
    public Booktype(){
    	
    }
     /**
      * 
      * @param typeid 种类编号
      * @param typename 种类名称
      * @param typedetailed 种类详情
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
	//自动生成setter/getter方法
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
