package com.ray.demo.admin.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.demo.admin.dao.master.BankMapper;
import com.ray.demo.admin.model.master.Bank;
import com.ray.demo.admin.model.master.BankExample;
import com.ray.demo.admin.service.master.BankService;
import com.ray.framework.service.common.impl.BaseServiceImpl;

@Service("BankService")
public class BankServiceImpl extends BaseServiceImpl<Bank, BankExample> implements BankService {

    @SuppressWarnings("unused")
	@Autowired
    private BankMapper bankMapper;

    @Autowired
    public BankServiceImpl(BankMapper mapper) {
        super(mapper);
        this.bankMapper = mapper;
    }
	
}
