package com.ray.demo.admin.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.framework.service.common.impl.BaseServiceImpl;
import com.ray.demo.admin.dao.master.SysElementMapper;
import com.ray.demo.admin.model.master.SysElement;
import com.ray.demo.admin.model.master.SysElementExample;
import com.ray.demo.admin.service.master.SysElementService;

@Service("SysElementService")
public class SysElementServiceImpl extends BaseServiceImpl<SysElement, SysElementExample> implements SysElementService {

    @Autowired
    private SysElementMapper sysElementMapperMapper;

    @Autowired
    public SysElementServiceImpl(SysElementMapper mapper) {
        super(mapper);
        this.sysElementMapperMapper = mapper;
    }

    @Override
	public void batchInsert(List<SysElement> sysElements) {
    	sysElementMapperMapper.batchInsert(sysElements);
	}
    @Override
    public void  batchupdateByPrimaryKey(List<SysElement> sysElement) {
	
    
		sysElementMapperMapper.batchupdateByPrimaryKey(sysElement);
	
    }
}
