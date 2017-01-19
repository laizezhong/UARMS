package com.ray.demo.admin.webapp.controller.master;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ray.framework.controller.BaseController;
import com.ray.framework.webapp.rest.RestResultResponse;
import com.ray.demo.admin.manager.master.SysElementGroupManager;
import com.ray.demo.admin.model.master.SysElementGroup;
import com.ray.demo.admin.model.master.SysElementGroupVo2;



@Controller
@RequestMapping("/master/syselementgroup")
public class SysElementGroupController extends BaseController{

	@Autowired
	private SysElementGroupManager syselementgroup;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> list(@ModelAttribute SysElementGroup record) {
		try {
			Object ob = null;
		    ob = syselementgroup.selectSysElementGroupSysElement(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<RestResultResponse> create ( @RequestBody SysElementGroup record  ){
//		try {
//			record.setStatusFlag("0");
//			Object ob = syselementgroup.addBankAccount(record);
//			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
//		} catch (Exception ex) {
//			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
//		}
//    }
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<RestResultResponse> create ( @RequestBody SysElementGroupVo2  sysElementGroupVo){
		try {
			Object ob = syselementgroup.insertLeasingAndFee(sysElementGroupVo);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity<RestResultResponse> logicDel ( @PathVariable("id")  long id){
		try {
			
			SysElementGroup record = syselementgroup.findById(id);
			Object ob = syselementgroup.logicDelSysElementGroup(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  	public ResponseEntity<RestResultResponse> list1(@PathVariable("id") long id) {
    	try{
    	
 	SysElementGroup record =  syselementgroup.findById1(id);
    
  			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(record), HttpStatus.OK);

  		} catch (Exception ex) {
  			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
  					HttpStatus.EXPECTATION_FAILED);
  		}
  	}
}