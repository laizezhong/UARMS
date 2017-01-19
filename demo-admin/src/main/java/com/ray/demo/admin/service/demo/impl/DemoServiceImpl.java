package com.ray.demo.admin.service.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.demo.admin.dao.master.BankMapper;
import com.ray.demo.admin.model.master.Bank;
import com.ray.demo.admin.model.master.BankExample;
import com.ray.demo.admin.service.demo.DemoService;
import com.ray.framework.service.common.impl.BaseServiceImpl;

//@Service("BankService")

@Service
public class DemoServiceImpl extends BaseServiceImpl<Bank, BankExample> implements DemoService {

    @SuppressWarnings("unused")
	@Autowired
    private BankMapper demoMapper;

    @Autowired
    public DemoServiceImpl(BankMapper mapper) {
        super(mapper);
        this.demoMapper = mapper;
    }
	
}
