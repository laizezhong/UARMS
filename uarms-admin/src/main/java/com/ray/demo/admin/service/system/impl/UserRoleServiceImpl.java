package com.ray.demo.admin.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.framework.service.common.impl.BaseServiceImpl;
import com.ray.demo.admin.dao.system.SysUserroleMapper;
import com.ray.demo.admin.model.system.SysUserroleExample;
import com.ray.demo.admin.model.system.SysUserroleKey;
import com.ray.demo.admin.service.system.UserRoleService;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<SysUserroleKey, SysUserroleExample> implements UserRoleService {

    @SuppressWarnings("unused")
	@Autowired
    private SysUserroleMapper userroleMapper;

    @Autowired
    public UserRoleServiceImpl(SysUserroleMapper mapper) {
        super(mapper);
        this.userroleMapper = mapper;
    }
	
}
