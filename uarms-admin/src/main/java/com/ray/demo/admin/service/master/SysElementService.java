package com.ray.demo.admin.service.master;


import java.util.List;

import com.ray.framework.service.common.BaseService;
import com.ray.demo.admin.model.master.SysElement;
import com.ray.demo.admin.model.master.SysElementExample;

public interface SysElementService extends BaseService<SysElement, SysElementExample>{
	
	public void batchInsert(List<SysElement> sysElement);
	public void  batchupdateByPrimaryKey(List<SysElement> sysElements);
}
