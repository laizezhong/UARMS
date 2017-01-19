package com.ray.demo.admin.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.framework.service.common.impl.BaseServiceImpl;
import com.ray.demo.admin.dao.master.SellerBankMapper;
import com.ray.demo.admin.model.master.SellerBank;
import com.ray.demo.admin.model.master.SellerBankExample;
import com.ray.demo.admin.model.master.SellerVo;
import com.ray.demo.admin.service.master.SellerBankService;
import com.ray.demo.admin.webapp.controller.master.searcher.SellerSearcher;

@Service("SellerBankService")
public class SellerBankServiceImpl extends BaseServiceImpl<SellerBank, SellerBankExample> implements SellerBankService {
	 @Autowired
	    private SellerBankMapper sellerBnakMapper;
	
    @Autowired
    public SellerBankServiceImpl(SellerBankMapper mapper) {
        super(mapper);
    }
	
    @Override
   	public List<SellerVo> selectSellerSellerBank(SellerSearcher searcher) {
   		return sellerBnakMapper.selectSellerSellerBank(searcher);
   	}

	@Override
	public void batchInsert(List<SellerBank> sellerBanks) {
		sellerBnakMapper.batchInsert(sellerBanks);
	}
    
}
