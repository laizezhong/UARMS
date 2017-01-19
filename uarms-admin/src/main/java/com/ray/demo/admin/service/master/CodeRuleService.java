package com.ray.demo.admin.service.master;


import com.ray.framework.service.common.BaseService;
import com.ray.demo.admin.model.master.CodeRule;
import com.ray.demo.admin.model.master.CodeRuleExample;
import com.ray.demo.admin.model.master.CodeRuleVo;

public interface CodeRuleService extends BaseService<CodeRule, CodeRuleExample>{
	public String selectCode(CodeRuleVo record);
}
