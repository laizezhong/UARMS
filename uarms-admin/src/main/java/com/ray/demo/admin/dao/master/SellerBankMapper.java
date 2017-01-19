package com.ray.demo.admin.dao.master;

import java.util.List;

import com.ray.framework.daomapper.BaseMapper;
import com.ray.demo.admin.model.master.SellerBank;
import com.ray.demo.admin.model.master.SellerBankExample;
import com.ray.demo.admin.model.master.SellerVo;
import com.ray.demo.admin.webapp.controller.master.searcher.SellerSearcher;

public interface SellerBankMapper extends BaseMapper<SellerBank, SellerBankExample>{
 
	public List<SellerVo> selectSellerSellerBank(SellerSearcher searcher);
	
}