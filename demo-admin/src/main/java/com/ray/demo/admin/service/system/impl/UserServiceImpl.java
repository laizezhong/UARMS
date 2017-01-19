package com.ray.demo.admin.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.demo.admin.dao.system.SysUserMapper;
import com.ray.demo.admin.model.system.SysUser;
import com.ray.demo.admin.model.system.SysUserExample;
import com.ray.demo.admin.service.system.UserService;
import com.ray.framework.service.common.impl.BaseServiceImpl;

//@Service("BankService")

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser, SysUserExample> implements UserService {

    @SuppressWarnings("unused")
	@Autowired
    private SysUserMapper userMapper;

    @Autowired
    public UserServiceImpl(SysUserMapper mapper) {
        super(mapper);
        this.userMapper = mapper;
    }
	
}
