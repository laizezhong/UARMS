package com.ray.demo.admin.webapp.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ray.demo.admin.manager.master.CodeRuleManager;
import com.ray.demo.admin.model.master.CodeRuleVo;
import com.ray.framework.controller.BaseController;
import com.ray.framework.webapp.rest.RestResultResponse; 



@Controller
@RequestMapping("/master/rerule")
public class ReCodeRuleController extends BaseController{


	@Autowired
	private CodeRuleManager manager; 
			@RequestMapping(method = RequestMethod.GET)
		    public ResponseEntity<RestResultResponse> list(@ModelAttribute CodeRuleVo searcher){
			try{
				Object ob = null;  
				 ob=manager.selectCode(searcher);
					return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
				} catch (Exception ex) {
					return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex), HttpStatus.EXPECTATION_FAILED);
				}

			}
}

