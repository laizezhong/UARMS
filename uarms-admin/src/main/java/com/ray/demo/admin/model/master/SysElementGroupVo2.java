package com.ray.demo.admin.model.master;

import java.io.Serializable;
import java.util.List;

public class SysElementGroupVo2 implements Serializable{
	
	private static final long serialVersionUID = -2343360441034909010L;

	private SysElementGroup  syselementgroup;
	
	private List<SysElement> syselement;
	public SysElementGroup getSyselementgroup() {
		return syselementgroup;
	}
	public void setSyselementgroup(SysElementGroup syselementgroup) {
		this.syselementgroup = syselementgroup;
	}
	public List<SysElement> getSyselement() {
		return syselement;
	}
	public void setSyselement(List<SysElement> syselement) {
		this.syselement = syselement;
	}

	


	

}
