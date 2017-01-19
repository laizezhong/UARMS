package com.ray.demo.admin.webapp.controller.master.searcher;

import com.ray.framework.controller.BaseSearcher;

public class CompanySearcher extends BaseSearcher {
   
	private String allName;
	private String name;
	private String shortName;
	private Long parentId;  //新增搜索字段,用于删除时对应的判断

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	
	
	
}
