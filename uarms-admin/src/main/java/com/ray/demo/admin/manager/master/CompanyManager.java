package com.ray.demo.admin.manager.master;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ray.demo.admin.manager.BaseManager;
import com.ray.demo.admin.model.master.Company;
import com.ray.demo.admin.model.master.CompanyExample;
import com.ray.demo.admin.service.master.CompanyService;
import com.ray.demo.admin.webapp.controller.master.searcher.CompanySearcher;
import com.ray.demo.admin.webapp.shiro.SessionObject;

@Component
public class CompanyManager extends BaseManager{
	
	@Autowired
	private CompanyService service;
	
	Logger logger = this.getLoggerObject();
	@SuppressWarnings("unused")
	private final SessionObject session = this.getCurrentSessionObject();
	
	
	public List<Company> listAll() {
		
		List<Company> companies = service.listAll();

		return companies;
		
	}
	public List<Company> selectByExample(CompanySearcher searcher) {
		CompanyExample example = new CompanyExample();
		CompanyExample.Criteria criteria = example.createCriteria();
		CompanyExample.Criteria criteriaOr = example.createCriteria();
		example.or(criteriaOr);
		criteria.andDeleteFlagEqualTo(0);
		if(null != searcher.getAllName() && !searcher.getAllName().equals("")){
			criteria.andNameLike("%"+ searcher.getAllName() +"%");
		}
		
		if(null != searcher.getAllName() && !searcher.getAllName().equals("")){
			    criteriaOr.andDeleteFlagEqualTo(0);
				criteriaOr.andShortNameLike("%"+ searcher.getAllName() +"%");
			}
		if(null != searcher.getParentId() && !searcher.getParentId().equals("")){   //用于删除时搜索总公司对应的子公司的数据
			criteria.andParentIdEqualTo(searcher.getParentId());
		}
		List<Company>companies = service.selectByExample(example);
		return companies;
	}
	
	public Company findById(long id) {
		
		Company company = service.selectByPrimaryKey(id);
		return company;
	}
	
	
	@Transactional
	public Company addCompany(Company record) {
		record.setDeleteFlag(0);                                      
		record.setCreator(this.getCurrentSessionObject().getUser().getId());   //得到当前登录的用户,"创建人"
		record.setCreateTime(new Date());                                      //得到当前系统时间,"创建时间"
		record.setModifier(this.getCurrentSessionObject().getUser().getId());  //得到当前用户,"修改人"
		record.setModifyTime(new Date());                                      //得到当前系统时间,"修改时间"
		service.insertSelective(record);
		return record;
	}
	@Transactional
	public Company editCompany(Company record) {
		record.setModifier(this.getCurrentSessionObject().getUser().getId());  //得到当前用户,"修改人"
		record.setModifyTime(new Date());                                      //得到当前系统时间,"修改时间"
		service.updateByPrimaryKey(record);
		return record;
	}
	public Company logicDelCompany(Company record){
		record.setModifier(this.getCurrentSessionObject().getUser().getId());  //得到当前用户,"修改人"
		record.setModifyTime(new Date());                                      //得到当前系统时间,"修改时间"
		record.setDeleteFlag(1);                                                                       //deleteFlag为1的时候为逻辑删除状态
		service.updateByPrimaryKey(record);
		return record;
	}
	
	
}
