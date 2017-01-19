package com.ray.demo.admin.dao.master;

import com.ray.framework.daomapper.BaseMapper;
import com.ray.demo.admin.model.master.CodeRule;
import com.ray.demo.admin.model.master.CodeRuleExample;
import com.ray.demo.admin.model.master.CodeRuleVo;


public interface CodeRuleMapper extends BaseMapper<CodeRule, CodeRuleExample> {

	public String selectCode(CodeRuleVo record);

}