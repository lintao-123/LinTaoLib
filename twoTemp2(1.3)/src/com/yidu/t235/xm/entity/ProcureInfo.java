package com.yidu.t235.xm.entity;

public class ProcureInfo {
	private int tempId ;//--编号
 
	private String procurementNo ;//--采购订单号
 
	private String supplier ; 	//供应商
	private String libraryTime	;//采购日期

	 
	
	public ProcureInfo() {
	}


	/**
	 * 部分带参函数
	 * @param procurementNo --采购订单号
	 * @param supplier 供应商
	 * @param libraryTime 采购日期
	 */
	public ProcureInfo(String procurementNo, String supplier, String libraryTime) {
		this.procurementNo = procurementNo;
		this.supplier = supplier;
		this.libraryTime = libraryTime;
	}

	/**
	 * 带参函数
	 * @param tempId--编号
	 * @param procurementNo --采购订单号
	 * @param supplier 供应商
	 * @param libraryTime 采购日期
	 */
	
	public ProcureInfo(int tempId, String procurementNo, String supplier, String libraryTime) {
		this.tempId = tempId;
		this.procurementNo = procurementNo;
		this.supplier = supplier;
		this.libraryTime = libraryTime;
	}

	//自动生成setter和getter
	public int getTempId() {
		return tempId;
	}


	public void setTempId(int tempId) {
		this.tempId = tempId;
	}


	public String getProcurementNo() {
		return procurementNo;
	}


	public void setProcurementNo(String procurementNo) {
		this.procurementNo = procurementNo;
	}


	public String getSupplier() {
		return supplier;
	}


	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}


	public String getLibraryTime() {
		return libraryTime;
	}


	public void setLibraryTime(String libraryTime) {
		this.libraryTime = libraryTime;
	}
		 	
}
