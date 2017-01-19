package com.ray.demo.admin.model.master;

import java.io.Serializable;

public class SysElementGroupVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long eleGroupCode;
	private Long eleGroupID;//字典组ID
	private String eleGroupName;//类型名称
	private String statusFlag;//状态（两种状态，使用中0已废置1 ）
	private  Long   crtUser;
	private String eleCode;//元素代码
	private String eleName;//元素名称
	private String eleKey;//元素值
	private String orderNum;//排序
	private  Long   StypeCount;


	


	public Long getCrtUser() {
		return crtUser;
	}
	public void setCrtUser(Long crtUser) {
		this.crtUser = crtUser;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEleGroupCode() {
		return eleGroupCode;
	}
	public void setEleGroupCode(Long eleGroupCode) {
		this.eleGroupCode = eleGroupCode;
	}

	public Long getEleGroupID() {
		return eleGroupID;
	}
	public void setEleGroupID(Long eleGroupID) {
		this.eleGroupID = eleGroupID;
	}
	public String getEleGroupName() {
		return eleGroupName;
	}
	public void setEleGroupName(String eleGroupName) {
		this.eleGroupName = eleGroupName;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	public String getEleCode() {
		return eleCode;
	}
	public void setEleCode(String eleCode) {
		this.eleCode = eleCode;
	}
	public String getEleName() {
		return eleName;
	}
	public void setEleName(String eleName) {
		this.eleName = eleName;
	}
	public String getEleKey() {
		return eleKey;
	}
	public void setEleKey(String eleKey) {
		this.eleKey = eleKey;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Long getStypeCount() {
		return StypeCount;
	}
	public void setStypeCount(Long stypeCount) {
		StypeCount = stypeCount;
	}

	

}
