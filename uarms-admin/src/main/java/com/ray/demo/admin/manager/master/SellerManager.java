package com.ray.demo.admin.manager.master;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ray.demo.admin.manager.BaseManager;
import com.ray.demo.admin.model.master.Seller;
import com.ray.demo.admin.model.master.SellerAndBank;
import com.ray.demo.admin.model.master.SellerBank;
import com.ray.demo.admin.model.master.SellerExample;
import com.ray.demo.admin.model.master.SellerVo;
import com.ray.demo.admin.service.master.SellerBankService;
import com.ray.demo.admin.service.master.SellerService;
import com.ray.demo.admin.webapp.controller.master.searcher.SellerSearcher;

@Component
public class SellerManager extends BaseManager {

	@Autowired
	private SellerService service;
	
	@Autowired
	private SellerBankService sellerBankService;
	
	Logger logger = this.getLoggerObject();
	
	public List<SellerVo> selectSellerSellerBank(SellerSearcher searcher){
		List<SellerVo>  SellerVos=service.selectSellerSellerBank(searcher);
		return SellerVos;
	}


	public List<Seller> listSeller( SellerSearcher searcher ) {
		
		SellerExample example = new SellerExample();
		SellerExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagNotEqualTo(1);
		
		if(null != searcher.getSellerName() && !searcher.getSellerName().equals("")) {
			criteria.andSellerNameLike("%"+ searcher.getSellerName() +"%");
		}
		if(null != searcher.getSellerType() && !searcher.getSellerType().equals("")) {
			criteria.andSellerTypeEqualTo(searcher.getSellerType());
		}
		if(null != searcher.getStatus() && !searcher.getStatus().equals("")) {
			criteria.andStatusEqualTo(searcher.getStatus());
		}
		List<Seller> sellers = service.selectByExample(example);
		return sellers;
	}

	public Seller findById(long id) {
		Seller seller = service.selectByPrimaryKey(id);
		return seller;
	}

	@Transactional
	public Seller addSeller(Seller record) {
		record.setStatus(0);
		record.setCreateTime(new Date()); 
		record.setCreator(this.getCurrentSessionObject().getUser().getId());
		record.setModifyTime(new Date());
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		service.insertSelective(record);
		return record;
	}
	
	@Transactional
	public int insertSellerAndBank(SellerAndBank record) {
		Seller  seller = record.getSeller();
		List<SellerBank> sellrBanks = record.getSellerBank();
		seller.setDeleteFlag(0);
		seller.setCreateTime(new Date()); 
		seller.setCreator(this.getCurrentSessionObject().getUser().getId());
		seller.setModifyTime(new Date());
		seller.setModifier(this.getCurrentSessionObject().getUser().getId());
		seller.setStatus(0);
		int status = service.insert(seller);
		long sellerId = seller.getId();
		if (!sellrBanks.isEmpty()){
			for (SellerBank sellerBank : sellrBanks) {
				sellerBank.setSellerId(sellerId);
				sellerBank.setDeleteFlag(0);
				sellerBank.setCreateTime(new Date());
				sellerBank.setCreator(this.getCurrentSessionObject().getUser().getId());
				sellerBank.setModifyTime(new Date());
				sellerBank.setModifier(this.getCurrentSessionObject().getUser().getId());
			}
			sellerBankService.batchInsert(sellrBanks);
		}
		return status;
	}

	@Transactional
	public Seller editSeller(Seller record) {
		record.setModifyTime(new Date());
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		service.updateByPrimaryKey(record);
		return record;
	}

	@Transactional
	public Seller logicDelSeller(Seller record) {
		//出卖方合作终止
		record.setStatus(1);
		record.setModifyTime(new Date());
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		service.updateByPrimaryKey(record);
		return record;
	}

}
