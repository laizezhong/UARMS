package com.ray.demo.admin.manager.master;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ray.demo.admin.manager.BaseManager;
import com.ray.demo.admin.model.master.SellerBank;
import com.ray.demo.admin.model.master.SellerBankExample;
import com.ray.demo.admin.model.master.SellerVo;
import com.ray.demo.admin.service.master.SellerBankService;
import com.ray.demo.admin.webapp.controller.master.searcher.SellerSearcher;

@Component
public class SellerBankManager extends BaseManager {

	@Autowired
	private SellerBankService service;

	Logger logger = this.getLoggerObject();

	public List<SellerVo> selectSellerSellerBank(SellerSearcher searcher){
		List<SellerVo>  SellerVos=service.selectSellerSellerBank(searcher);
		return SellerVos;
	}
	
	public SellerBank findById(long id) {
		SellerBank SellerBank = service.selectByPrimaryKey(id);
		return SellerBank;
	}
	
	@Transactional
	public SellerBank logicDelBank(SellerBank record) {
		record.setDeleteFlag(1);
		record.setModifyTime(new Date());
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		service.updateByPrimaryKey(record);
		return record;
	}

	public int deleteByExample(SellerBankExample example) {
		return service.deleteByExample(example);
	}

	public int deleteByPrimaryKey(Long id) {
		return service.deleteByPrimaryKey(id);
	}

	public int insert(SellerBank record) {
		record.setDeleteFlag(0);
		record.setCreateTime(new Date());
		record.setCreator(this.getCurrentSessionObject().getUser().getId());
		record.setModifyTime(new Date());
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		return service.insert(record);
	}

	public int insertSelective(SellerBank record) {
		record.setCreateTime(new Date());
		record.setCreator(this.getCurrentSessionObject().getUser().getId());
		record.setModifyTime(new Date());
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		return service.insertSelective(record);
	}

	public List<SellerBank> selectByExample(SellerBankExample example) {
		return service.selectByExample(example);
	}

	public SellerBank selectByPrimaryKey(Long id) {
		return service.selectByPrimaryKey(id);
	}

	public int updateByExampleSelective(SellerBank record,
			SellerBankExample example) {
		return service.updateByExampleSelective(record, example);
	}

	public int updateByExample(SellerBank record,
			SellerBankExample example) {
		return service.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(SellerBank record) {
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		record.setModifyTime(new Date());
		return service.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public SellerBank updateByPrimaryKey(SellerBank record) {
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		record.setModifyTime(new Date());
		service.updateByPrimaryKey(record);
		return record;
	}
	
	public List<SellerBank> selectByExample1(SellerBank searcher) {
		SellerBankExample lsExample = new SellerBankExample();
		SellerBankExample.Criteria criteria = lsExample.createCriteria();
		criteria.andDeleteFlagEqualTo(0);
		if(null != searcher.getBankAccount() && !searcher.getBankAccount().equals("")){
			criteria.andBankAccountEqualTo(searcher.getBankAccount());
		}
		if(null != searcher.getSellerId() && !searcher.getSellerId().equals("")){
			criteria.andSellerIdEqualTo(searcher.getSellerId());
		}
		if(null != searcher.getId() && !searcher.getId().equals("")){
			criteria.andIdEqualTo(searcher.getId());
		}
		return service.selectByExample(lsExample);
	}

}
