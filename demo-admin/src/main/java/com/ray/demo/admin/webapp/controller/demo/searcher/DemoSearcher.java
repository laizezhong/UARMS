package com.ray.demo.admin.webapp.controller.demo.searcher;

import com.ray.framework.controller.BaseSearcher;

public class DemoSearcher extends BaseSearcher{

	private String bankName = "";
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}
