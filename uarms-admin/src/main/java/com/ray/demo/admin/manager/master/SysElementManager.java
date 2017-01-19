package com.ray.demo.admin.manager.master;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ray.demo.admin.manager.BaseManager;
import com.ray.demo.admin.model.master.SysElement;
import com.ray.demo.admin.model.master.SysElementExample;
import com.ray.demo.admin.model.master.SysElementGroup;
import com.ray.demo.admin.model.master.SysElementGroupVo2;
import com.ray.demo.admin.service.master.SysElementGroupService;
import com.ray.demo.admin.service.master.SysElementService;
import com.ray.demo.admin.webapp.shiro.SessionObject;

@Component
public class SysElementManager extends BaseManager{

	@Autowired
	private SysElementGroupService servicegroup;
	@Autowired
	private SysElementService service;
	
	
	Logger logger = this.getLoggerObject();
	@SuppressWarnings("unused")
	private final SessionObject session = this.getCurrentSessionObject();
	
    @Transactional
	public  int addSysElement(SysElementGroupVo2 sysElementGroupVo){
    	SysElementGroup  sysElementGroup = sysElementGroupVo.getSyselementgroup();
    	List<SysElement> sysElements = sysElementGroupVo.getSyselement();
    	sysElementGroup.setCrtUser(this.getCurrentSessionObject().getUser().getId());
		sysElementGroup.setCrtDatetime(new Date());
		sysElementGroup.setUpdUser(this.getCurrentSessionObject().getUser().getId());
		sysElementGroup.setUpdDatetime(new Date());

		int status = servicegroup.updateByPrimaryKey(sysElementGroup);
    	 Long  syseleId = sysElementGroup.getId();
    	
    	for (SysElement sysElement : sysElements) {
    		    if(sysElement.getId()!=null){
    		    	service.updateByPrimaryKey(sysElement);
    		    }
    		    else{
    		    	sysElement.setEleGroupId(syseleId);
                	 sysElement.setStatusFlag("0");
             		service.insertSelective(sysElement);
    		    }
          	     
             	
		}

    	return status;
	
	}
	
    public List<SysElement> selectByExample(SysElement sysElement) {
    	SysElementExample lsExample = new SysElementExample();
		
    	SysElementExample.Criteria criteria = lsExample.createCriteria();
		

		if(null != sysElement.getEleGroupId()&& !sysElement.getEleGroupId().equals("")){
			criteria.andEleGroupIdEqualTo(sysElement.getEleGroupId());
		}
		criteria.andStatusFlagEqualTo("0");
		return service.selectByExample(lsExample);
	}
	public List<SysElement>  findByeleGroupId(Long id) {
	
		SysElementExample lsExample  = new SysElementExample();
		SysElementExample.Criteria criteria = lsExample.createCriteria();
		

		if(null != id){
			criteria.andEleGroupIdEqualTo(id);
		}
		criteria.andStatusFlagNotLike("1");

		return service.selectByExample(lsExample);

	}
	@Transactional
	public SysElement addBank(SysElement record) {
       record.setStatusFlag("0");
		service.insertSelective(record);
		return record;
	}
	@Transactional
	public SysElement editSysElement(SysElement record) {
        record.setStatusFlag("2");
		service.updateByPrimaryKey(record);
		return record;
	}
	@Transactional
	public SysElement editSysElement1(SysElement record) {
		record.setCrtUser(this.getCurrentSessionObject().getUser().getId());
		record.setCrtDatetime(new Date());
		record.setUpdUser(this.getCurrentSessionObject().getUser().getId());
		record.setUpdDatetime(new Date());
		String bString=record.getStatusFlag();
	System.out.println(bString);
		if(bString. equals("0")){
			  record.setStatusFlag("2");
			
		}else{
			
			 record.setStatusFlag("0");
		}
		service.updateByPrimaryKey(record);
		return record;
	}

	public SysElement findById(long id) {
		
	SysElement record = service.selectByPrimaryKey(id);
		return record;
	}
	@Transactional
	public SysElement logicDelSysElement(SysElement record) {
		if(record.getStatusFlag()=="0"){
	        record.setStatusFlag("2");
			}else{
				 record.setStatusFlag("0");
			}
		record.setCrtUser(this.getCurrentSessionObject().getUser().getId());
		record.setCrtDatetime(new Date());
		record.setUpdUser(this.getCurrentSessionObject().getUser().getId());
		record.setUpdDatetime(new Date());
		record.setStatusFlag("1");
		service.updateByPrimaryKey(record);
		return record;
	}
	
	 

	

}


