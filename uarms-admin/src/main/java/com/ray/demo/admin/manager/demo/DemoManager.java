package com.ray.demo.admin.manager.demo;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ray.demo.admin.manager.BaseManager;
import com.ray.demo.admin.model.master.Bank;
import com.ray.demo.admin.model.master.BankExample;
import com.ray.demo.admin.service.demo.DemoService;
import com.ray.demo.admin.webapp.controller.demo.searcher.DemoSearcher;

@Component
public class DemoManager extends BaseManager{
	
	@Autowired
	private DemoService service;
	
	Logger logger = this.getLoggerObject();
	//private final SessionObject session = this.getCurrentSessionObject();
	
	public List<Bank> listAll() {
		
		List<Bank> banks = service.listAll();
		return banks;
	}

	public List<Bank> findByBankName(DemoSearcher searcher) {
		
		BankExample example = new BankExample();
		BankExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagNotEqualTo(1);
		if(null != searcher.getBankName()) {
			criteria.andBankNameLike("%"+ searcher.getBankName() +"%");
		}
		List<Bank> banks = service.selectByExample(example);
		return banks;
	}
	
	public Bank findById(long id) {
		
		Bank bank = service.selectByPrimaryKey(id);
		return bank;
	}
	
	@Transactional
	public Bank addBank(Bank record) {
		
		//service.insert(record);
		record.setDeleteFlag(0);
		record.setCreator(this.getCurrentSessionObject().getUser().getId());
		record.setCreateTime(new Date());
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		record.setModifyTime(new Date());
		service.insertSelective(record);
		System.out.println("new id:" + record.getId());
		return record;
	}
	
	@Transactional
	public Bank editBank(Bank record) {

		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		record.setModifyTime(new Date());
		service.updateByPrimaryKey(record);
		return record;
	}

	@Transactional
	public Bank logicDelBank(Bank record) {
		
		record.setDeleteFlag(1);
		service.updateByPrimaryKey(record);
		return record;
	}
	
}
