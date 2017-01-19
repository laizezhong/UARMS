package com.ray.demo.admin.dao.master;

import java.util.List;

import com.ray.framework.daomapper.BaseMapper;
import com.ray.demo.admin.model.master.Seller;
import com.ray.demo.admin.model.master.SellerExample;
import com.ray.demo.admin.model.master.SellerVo;
import com.ray.demo.admin.webapp.controller.master.searcher.SellerSearcher;

public interface SellerMapper extends BaseMapper<Seller, SellerExample>{
 
	public List<SellerVo> selectSellerSellerBank(SellerSearcher searcher);
	
}