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
import com.ray.demo.admin.manager.master.CompanyManager;
import com.ray.demo.admin.model.master.Company;
import com.ray.demo.admin.webapp.controller.master.searcher.CompanySearcher;
@Controller
@RequestMapping("/master/companies")
public class CompanyController extends BaseController{
	@Autowired
	private CompanyManager manager = new CompanyManager();
	
	
	 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<RestResultResponse> list(@ModelAttribute CompanySearcher searcher){
    //public ResponseEntity<RestResultResponse> list(@RequestParam(value="name") String searcher){
		try {
			Object ob = manager.selectByExample(searcher);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RestResultResponse> create(@RequestBody Company record) {
		try {
			//record.setDeleteFlag(0);
			Object ob = manager.addCompany(record);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),HttpStatus.EXPECTATION_FAILED);
		}
	}

   /* @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<RestResultResponse> create (@RequestBody Company record ){
		try {
			
			Object ob = manager.addCompany(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
    */
    @RequestMapping(value="/{id}" ,method=RequestMethod.PUT,consumes="application/json") 
    public ResponseEntity<RestResultResponse> edit (@RequestBody Company record ){
		try {
			Object ob = manager.editCompany(record);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
    
    @RequestMapping(value="/{id}" ,method=RequestMethod.DELETE,consumes="application/json") 
    public ResponseEntity<RestResultResponse> logicalDel(@PathVariable("id") long id) {
		try {
			Company record=manager.findById(id);
			Object ob = manager.logicDelCompany(record);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
	
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> findById(@PathVariable("id") long id) {
		try {
			Company record = manager.findById(id);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(record), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),HttpStatus.EXPECTATION_FAILED);
		}
	}
}


