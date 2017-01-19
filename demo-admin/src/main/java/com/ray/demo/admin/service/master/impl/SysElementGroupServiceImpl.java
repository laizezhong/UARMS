package com.ray.demo.admin.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.framework.service.common.impl.BaseServiceImpl;
import com.ray.demo.admin.dao.master.SysElementGroupMapper;
import com.ray.demo.admin.model.master.SysElementGroup;
import com.ray.demo.admin.model.master.SysElementGroupExample;
import com.ray.demo.admin.model.master.SysElementGroupVo;
import com.ray.demo.admin.service.master.SysElementGroupService;

@Service("SysElementGroupService")
public class SysElementGroupServiceImpl extends BaseServiceImpl<SysElementGroup, SysElementGroupExample> implements SysElementGroupService {
	
	@Autowired
    private SysElementGroupMapper sysElementGroup;

    @Autowired
    public SysElementGroupServiceImpl(SysElementGroupMapper mapper) {
        super(mapper);
        this.sysElementGroup = mapper;
    }

	@Override
	public List<SysElementGroupVo> selectSysElementGroupSysElement(SysElementGroup record) {
		return sysElementGroup.selectSysElementGroupSysElement(record);
	}

}
