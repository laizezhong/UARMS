package com.ray.demo.admin.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.framework.service.common.impl.BaseServiceImpl;
import com.ray.demo.admin.dao.system.RolePermissionMapper;
import com.ray.demo.admin.model.system.RolePermission;
import com.ray.demo.admin.model.system.RolePermissionExample;
import com.ray.demo.admin.service.system.RolePermissionService;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission, RolePermissionExample> implements RolePermissionService {

    @SuppressWarnings("unused")
	@Autowired
    private RolePermissionMapper rolePerMapper;

    @Autowired
    public RolePermissionServiceImpl(RolePermissionMapper mapper) {
        super(mapper);
        this.rolePerMapper = mapper;
    }
	
}
