package com.ray.demo.admin.dao.master;

import java.util.List;

import com.ray.framework.daomapper.BaseMapper;
import com.ray.demo.admin.model.master.SysElementGroup;
import com.ray.demo.admin.model.master.SysElementGroupExample;
import com.ray.demo.admin.model.master.SysElementGroupVo; 

public interface SysElementGroupMapper extends BaseMapper<SysElementGroup, SysElementGroupExample>{
	
	public List<SysElementGroupVo> selectSysElementGroupSysElement(SysElementGroup SysElementGroup);
//	public List<SysElementGroupVo> selectLeasingSchema(LeasingSchemaSearcher searcher);
//	public List<SysElementGroupVo> selectSysElementGroup(SysElementGroup searcher);
}
