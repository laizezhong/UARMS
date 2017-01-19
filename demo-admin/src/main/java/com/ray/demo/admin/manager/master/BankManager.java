package com.ray.demo.admin.manager.master;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ray.demo.admin.manager.BaseManager;
import com.ray.demo.admin.model.master.Bank;
import com.ray.demo.admin.model.master.BankExample;
import com.ray.demo.admin.service.master.BankService;
import com.ray.demo.admin.webapp.shiro.SessionObject;

@Component
public class BankManager extends BaseManager{
	
	@Autowired
	private BankService service;
	
	Logger logger = this.getLoggerObject();
	@SuppressWarnings("unused")
	private final SessionObject session = this.getCurrentSessionObject();
	
	public List<Bank> selectByExample(Bank bank) {
		BankExample lsExample = new BankExample();
		
		BankExample.Criteria criteria = lsExample.createCriteria();
		
		if(null != bank.getBankName() && !bank.getBankName().equals("")){
			criteria.andBankNameLike("%"+bank.getBankName()+"%");
		}
		if(null != bank.getBankCode()&& !bank.getBankCode().equals("")){
			criteria.andBankCodeEqualTo(bank.getBankCode());
		}
		if(null != bank.getId()&& !bank.getId().equals("")){
			criteria.andIdEqualTo(bank.getId());
		}
		criteria.andDeleteFlagEqualTo(0);
		return service.selectByExample(lsExample);
	}


	public List<Bank> findByBankName(String bankName) {
		
		BankExample example = new BankExample();
		example.createCriteria().andBankNameLike("%"+ bankName +"%");
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
		return record;
	}
	
	@Transactional
	public Bank editBank(Bank record) {
//		record.setDeleteFlag(0);
		record.setCreator(this.getCurrentSessionObject().getUser().getId());
		record.setCreateTime(new Date());
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		record.setModifyTime(new Date());
		service.updateByPrimaryKey(record);
		return record;
	}


	@Transactional
	public Bank logicDelBank(Bank record) {
		record.setCreator(this.getCurrentSessionObject().getUser().getId());
		record.setCreateTime(new Date());
		record.setModifier(this.getCurrentSessionObject().getUser().getId());
		record.setModifyTime(new Date());
		record.setDeleteFlag(1);
		service.updateByPrimaryKey(record);
		return record;
	}
}
