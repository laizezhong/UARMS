package com.ray.demo.admin.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.demo.admin.dao.system.PermissionMapper;
import com.ray.demo.admin.model.system.Permission;
import com.ray.demo.admin.model.system.PermissionExample;
import com.ray.demo.admin.service.system.PermissionService;
import com.ray.framework.service.common.impl.BaseServiceImpl;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, PermissionExample> implements PermissionService {

    @SuppressWarnings("unused")
	@Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper mapper) {
        super(mapper);
        this.permissionMapper = mapper;
    }
	
}
