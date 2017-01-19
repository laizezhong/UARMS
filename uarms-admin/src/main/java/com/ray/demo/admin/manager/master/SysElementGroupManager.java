package com.ray.demo.admin.manager.master;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ray.demo.admin.manager.BaseManager;
import com.ray.demo.admin.model.master.SysElement;
import com.ray.demo.admin.model.master.SysElementGroup;
import com.ray.demo.admin.model.master.SysElementGroupVo;
import com.ray.demo.admin.model.master.SysElementGroupVo2;
import com.ray.demo.admin.service.master.SysElementGroupService;
import com.ray.demo.admin.service.master.SysElementService;
import com.ray.demo.admin.webapp.shiro.SessionObject;



@Component
public class SysElementGroupManager extends BaseManager{
	
	@Autowired
	private SysElementGroupService service;
	@Autowired
	private SysElementService syselementService;

	
	Logger logger = this.getLoggerObject();
	@SuppressWarnings("unused")
	private final SessionObject session = this.getCurrentSessionObject();
	
    @Transactional
	public SysElementGroup addBankAccount(SysElementGroup record) {
		service.insertSelective(record);
		return record;
	}
    
	public SysElementGroup findById(long id) {
		
		SysElementGroup sysElementGroup = service.selectByPrimaryKey(id);
		sysElementGroup.setCrtUser(this.getCurrentSessionObject().getUser().getId());
		sysElementGroup.setCrtDatetime(new Date());
		sysElementGroup.setUpdUser(this.getCurrentSessionObject().getUser().getId());
		sysElementGroup.setUpdDatetime(new Date());
		return sysElementGroup;
	}
	
	public SysElementGroup  findById1(Long eleGroupId) {
		SysElementGroup sysElementGroup=new SysElementGroup();
		sysElementGroup.setCrtUser(this.getCurrentSessionObject().getUser().getId());
		sysElementGroup.setCrtDatetime(new Date());
		sysElementGroup.setUpdUser(this.getCurrentSessionObject().getUser().getId());
		sysElementGroup.setUpdDatetime(new Date());
		sysElementGroup.setStatusFlag("0");
		sysElementGroup=service.selectByPrimaryKey(eleGroupId);
		return  sysElementGroup;

	}
	  /*  查询 selectBankBankAccountCompany*/
	public List<SysElementGroupVo> selectSysElementGroupSysElement(SysElementGroup record) {	
	List<SysElementGroupVo>  sysElementGroupVo=service.selectSysElementGroupSysElement(record);
record.setCrtUser(this.getCurrentSessionObject().getUser().getId());
	record.setCrtDatetime(new Date());
	record.setUpdUser(this.getCurrentSessionObject().getUser().getId());
	record.setUpdDatetime(new Date());
	return sysElementGroupVo;
}

	/**
	 * editBankBankAccountCompany
	 * 在一个manager中写三个service
	 */
	@Transactional
	public SysElementGroup logicDelSysElementGroup(SysElementGroup record) {
		
		record.setStatusFlag("1");
		record.setCrtUser(this.getCurrentSessionObject().getUser().getId());
		record.setCrtDatetime(new Date());
		record.setUpdUser(this.getCurrentSessionObject().getUser().getId());
		record.setUpdDatetime(new Date());
		service.updateByPrimaryKey(record);
		return record;
	}
	public int insertLeasingAndFee(SysElementGroupVo2 sysElementGroupVo) {
		SysElementGroup  sysElementGroup = sysElementGroupVo.getSyselementgroup();
		List<SysElement> sysElements = sysElementGroupVo.getSyselement();
		sysElementGroup.setStatusFlag("0");
		sysElementGroup.setCrtUser(this.getCurrentSessionObject().getUser().getId());
		sysElementGroup.setCrtDatetime(new Date());
		sysElementGroup.setUpdUser(this.getCurrentSessionObject().getUser().getId());
		sysElementGroup.setUpdDatetime(new Date());
		int  status = service.insert(sysElementGroup);

		long syseleId = sysElementGroup.getId();
		for (SysElement sysElement : sysElements) {
			sysElement.setEleGroupId(syseleId);
			sysElement.setStatusFlag("0");
			
		}
		syselementService.batchInsert(sysElements);
		return status;
	}
	
}


