package com.ray.demo.admin.webapp.controller.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ray.demo.admin.manager.master.SellerBankManager;
import com.ray.demo.admin.model.master.SellerBank;
import com.ray.demo.admin.model.master.SellerBankExample;
import com.ray.framework.controller.BaseController;
import com.ray.framework.webapp.rest.RestResultResponse;


@Controller
@RequestMapping("/master/sellerBanks")
public class SellerBankController  extends BaseController{
	@Autowired
	private SellerBankManager SellerBankManager;
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> listById(@PathVariable("id") long id) {
		try {
			
			SellerBankExample example = new SellerBankExample();
			SellerBankExample.Criteria criteria = example.createCriteria();
			criteria.andSellerIdEqualTo(id);
			criteria.andDeleteFlagEqualTo(0);
			
			List<SellerBank> records = SellerBankManager.selectByExample(example);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(records), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<RestResultResponse> createBank ( @RequestBody SellerBank sellerBank){
		try {
			Object ob = SellerBankManager.insert(sellerBank);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }
	
	

    @RequestMapping(value ="/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<RestResultResponse> editBank (@RequestBody SellerBank sellerBank ){
		try {
			Object ob = SellerBankManager.updateByPrimaryKey(sellerBank);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
		}
    }  
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<RestResultResponse> logicDel(@PathVariable("id") long id) {
		try {

			SellerBank record = SellerBankManager.findById(id);
			Object ob = SellerBankManager.logicDelBank(record);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
    
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> list(@ModelAttribute SellerBank searcher) {
		try {

			List<SellerBank> ob = SellerBankManager.selectByExample1(searcher);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}