package com.ray.demo.admin.webapp.controller.demo;

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
import com.ray.demo.admin.manager.demo.DemoManager;
import com.ray.demo.admin.model.master.Bank;
import com.ray.demo.admin.webapp.controller.demo.searcher.DemoSearcher;

@Controller
@RequestMapping("/master/demobanks")
public class DemoController extends BaseController {

	@Autowired
	private DemoManager manager;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> list(@ModelAttribute DemoSearcher searcher) {
		try {

			Object ob = manager.findByBankName(searcher);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RestResultResponse> create(@RequestBody Bank record) {
		try {

			//record.setDeleteFlag(0);
			Object ob = manager.addBank(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RestResultResponse> edit(@RequestBody Bank record) {
		try {

			Object ob = manager.editBank(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<RestResultResponse> logicDel(@PathVariable("id") long id) {
		try {

			Bank record = manager.findById(id);
			Object ob = manager.logicDelBank(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> findById(@PathVariable("id") long id) {
		try {

			Bank record = manager.findById(id);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(record), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

}
