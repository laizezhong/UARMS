package com.ray.demo.admin.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ray.framework.daomapper.BaseMapper;
import com.ray.demo.admin.model.master.SysElement;
import com.ray.demo.admin.model.master.SysElementExample; 

public interface SysElementMapper extends BaseMapper<SysElement, SysElementExample>{
	
	 void batchupdateByPrimaryKey(@Param("items") List<SysElement> items);

	
}
