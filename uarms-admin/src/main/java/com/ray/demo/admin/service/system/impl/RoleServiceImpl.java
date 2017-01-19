package com.ray.demo.admin.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.framework.service.common.impl.BaseServiceImpl;
import com.ray.demo.admin.dao.system.SysRoleMapper;
import com.ray.demo.admin.model.system.SysRole;
import com.ray.demo.admin.model.system.SysRoleExample;
import com.ray.demo.admin.service.system.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleExample> implements RoleService {

    @SuppressWarnings("unused")
	@Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(SysRoleMapper mapper) {
        super(mapper);
        this.roleMapper = mapper;
    }
	
}
