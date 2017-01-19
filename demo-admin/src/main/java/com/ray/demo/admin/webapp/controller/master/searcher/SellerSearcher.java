package com.ray.demo.admin.webapp.controller.master.searcher;

import com.ray.framework.controller.BaseSearcher;

public class SellerSearcher extends BaseSearcher{
	
	private String sellerName;
	private Integer sellerType;
	private Integer status;
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Integer getSellerType() {
		return sellerType;
	}
	public void setSellerType(Integer sellerType) {
		this.sellerType = sellerType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
