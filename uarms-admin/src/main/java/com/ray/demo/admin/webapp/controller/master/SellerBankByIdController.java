package com.ray.demo.admin.webapp.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ray.framework.controller.BaseController;
import com.ray.framework.webapp.rest.RestResultResponse;
import com.ray.demo.admin.manager.master.SellerBankManager;
import com.ray.demo.admin.model.master.SellerBank;


@Controller
@RequestMapping("/master/sellerBanksById")
public class SellerBankByIdController  extends BaseController{
	@Autowired
	private SellerBankManager SellerBankManager;
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> listById(@PathVariable("id") long id) {
		try {
			
			SellerBank records = SellerBankManager.selectByPrimaryKey(id);

			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(records), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}