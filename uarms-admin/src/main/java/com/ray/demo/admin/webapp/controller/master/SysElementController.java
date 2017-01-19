package com.ray.demo.admin.webapp.controller.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ray.demo.admin.manager.master.SysElementManager;
import com.ray.demo.admin.model.master.SysElement;
import com.ray.demo.admin.model.master.SysElementGroupVo2;
import com.ray.framework.controller.BaseController;
import com.ray.framework.webapp.rest.RestResultResponse;

@Controller
@RequestMapping("/master/syselement")
public class SysElementController extends BaseController{

	@Autowired
	private SysElementManager syselement;
	
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<RestResultResponse> eidtcreate ( @RequestBody SysElementGroupVo2 sysElementGroupVo  ){
		try {
			Object ob = syselement.addSysElement(sysElementGroupVo);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
    @RequestMapping(value ="/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<RestResultResponse> edit ( @RequestBody SysElement record ){
    	try{
    		
		Object ob = syselement.editSysElement1(record);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
   
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity<RestResultResponse> logicDel ( @PathVariable("id") long id){
		try {
			
			SysElement record = syselement.findById(id);
			Object ob = syselement.logicDelSysElement(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  	public ResponseEntity<RestResultResponse> list1(@PathVariable("id") long id) {
    	try{
    	
 	List<SysElement> record =  syselement.findByeleGroupId(id);
    
  			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(record), HttpStatus.OK);

  		} catch (Exception ex) {
  			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
  					HttpStatus.EXPECTATION_FAILED);
  		}
  	}
}