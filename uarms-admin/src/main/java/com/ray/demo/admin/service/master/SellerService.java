package com.ray.demo.admin.service.master;


import java.util.List;

import com.ray.framework.service.common.BaseService;
import com.ray.demo.admin.model.master.Seller;
import com.ray.demo.admin.model.master.SellerExample;
import com.ray.demo.admin.model.master.SellerVo;
import com.ray.demo.admin.webapp.controller.master.searcher.SellerSearcher;

public interface SellerService extends BaseService<Seller, SellerExample>{
	
	public List<SellerVo> selectSellerSellerBank(SellerSearcher searcher);
	
}
