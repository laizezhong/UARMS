package com.ray.demo.admin.service.master;

import java.util.List;

import com.ray.framework.service.common.BaseService;
import com.ray.demo.admin.model.master.SysElementGroup;
import com.ray.demo.admin.model.master.SysElementGroupExample;
import com.ray.demo.admin.model.master.SysElementGroupVo;

public interface SysElementGroupService extends BaseService<SysElementGroup,SysElementGroupExample>{
	
	public List<SysElementGroupVo> selectSysElementGroupSysElement(SysElementGroup record) ;

}
