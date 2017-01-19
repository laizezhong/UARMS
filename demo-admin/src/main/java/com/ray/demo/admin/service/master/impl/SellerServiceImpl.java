package com.ray.demo.admin.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.framework.service.common.impl.BaseServiceImpl;
import com.ray.demo.admin.dao.master.SellerMapper;
import com.ray.demo.admin.model.master.Seller;
import com.ray.demo.admin.model.master.SellerExample;
import com.ray.demo.admin.model.master.SellerVo;
import com.ray.demo.admin.service.master.SellerService;
import com.ray.demo.admin.webapp.controller.master.searcher.SellerSearcher;

@Service("SellerService")
public class SellerServiceImpl extends BaseServiceImpl<Seller, SellerExample> implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    public SellerServiceImpl(SellerMapper mapper) {
        super(mapper);
        this.sellerMapper = mapper;
    }
    
    @Override
	public List<SellerVo> selectSellerSellerBank(SellerSearcher searcher) {
		return sellerMapper.selectSellerSellerBank(searcher);
	}
	
}
