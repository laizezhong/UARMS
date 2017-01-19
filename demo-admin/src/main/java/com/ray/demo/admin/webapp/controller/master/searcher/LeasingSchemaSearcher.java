package com.ray.demo.admin.webapp.controller.master.searcher;

import com.ray.framework.controller.BaseSearcher;

/**
 * @Title:LeasingSchemaSearcher     
 * @Description:Searcher
 * @Auth:LiangRui   
 * @CreateTime:2016年7月29日 上午11:35:55       
 * @version V1.0
 */
public class LeasingSchemaSearcher extends BaseSearcher{
	
	private String leasingName;
	private String itemBrand;
	private String itemModel;
	private String businessType;
	private String sellerId;
	private String sellerName;
	
	public String getLeasingName() {
		return leasingName;
	}
	public void setLeasingName(String leasingName) {
		this.leasingName = leasingName;
	}
	public String getItemBrand() {
		return itemBrand;
	}
	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}
	public String getItemModel() {
		return itemModel;
	}
	public void setItemModel(String itemModel) {
		this.itemModel = itemModel;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
}
