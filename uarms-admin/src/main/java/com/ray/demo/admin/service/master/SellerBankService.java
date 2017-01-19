package com.ray.demo.admin.service.master;


import java.util.List;

import com.ray.framework.service.common.BaseService;
import com.ray.demo.admin.model.master.SellerBank;
import com.ray.demo.admin.model.master.SellerBankExample;
import com.ray.demo.admin.model.master.SellerVo;
import com.ray.demo.admin.webapp.controller.master.searcher.SellerSearcher;

public interface SellerBankService extends BaseService<SellerBank, SellerBankExample>{
	
	public List<SellerVo> selectSellerSellerBank(SellerSearcher searcher);
	
	public void batchInsert(List<SellerBank> sellerBanks);
	
}
