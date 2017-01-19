package com.ray.framework.service.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseService<T, E> {

     int countAll();
    
     List<T> listAll();
	
	 int countByExample(E example);

	 int deleteByExample(E example);

	 int deleteByPrimaryKey(Long id);

	 int insert(T record);

	 int insertSelective(T record);

	 List<T> selectByExample(E example);

	 T selectByPrimaryKey(Long id);

	 int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

	 int updateByExample(@Param("record") T record, @Param("example") E example);

	 int updateByPrimaryKeySelective(T record);

	 int updateByPrimaryKey(T record);
	 
	 void batchInsert(List<T> records);
	
}
