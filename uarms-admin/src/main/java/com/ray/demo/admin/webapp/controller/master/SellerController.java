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
import com.ray.demo.admin.manager.master.SellerManager;
import com.ray.demo.admin.model.master.Seller;
import com.ray.demo.admin.model.master.SellerAndBank;
import com.ray.demo.admin.webapp.controller.master.searcher.SellerSearcher;

@Controller
@RequestMapping("/master/sellers")
public class SellerController extends BaseController {
 
	@Autowired
	private SellerManager manager;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> list(@ModelAttribute SellerSearcher searcher) {
		try {

			Object ob = manager.listSeller(searcher);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RestResultResponse> create(@RequestBody SellerAndBank record) {
		try {

			Object ob = manager.insertSellerAndBank(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RestResultResponse> edit(@RequestBody Seller record) {
		try {

			Object ob = manager.editSeller(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<RestResultResponse> logicDel(@PathVariable("id") long id) {
		try {

			Seller record = manager.findById(id);
			Object ob = manager.logicDelSeller(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> findById(@PathVariable("id") long id) {
		try {

			Seller record = manager.findById(id);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(record), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}	
}
