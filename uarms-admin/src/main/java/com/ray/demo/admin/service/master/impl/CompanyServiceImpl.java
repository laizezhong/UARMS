package com.ray.demo.admin.service.master.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.framework.service.common.impl.BaseServiceImpl;
import com.ray.demo.admin.dao.master.CompanyMapper;
import com.ray.demo.admin.model.master.Company;
import com.ray.demo.admin.model.master.CompanyExample;
import com.ray.demo.admin.service.master.CompanyService;

@Service("CompanyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company, CompanyExample> implements CompanyService {

    @SuppressWarnings("unused")
	@Autowired
    private CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyMapper mapper) {
        super(mapper);
        this.companyMapper = mapper;
    }
	
}
