package com.ray.framework.service.common.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ray.framework.daomapper.BaseMapper;
import com.ray.framework.service.common.BaseService;


public class BaseServiceImpl<T, E> implements BaseService<T, E> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected BaseMapper<T, E> mapper;

    public BaseServiceImpl(){

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseServiceImpl(BaseMapper mapper) {
        this.mapper = mapper;
    }

    public int countAll() {
    	return mapper.countByExample(null);
    }
    
    public List<T> listAll() {
    	return mapper.selectByExample(null);
    }
    
	@Override
	public int countByExample(E example) {
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByExample(E example) {
		return mapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return mapper.insert(record);
	}

	@Override
	public List<T> selectByExample(E example) {
		return mapper.selectByExample(example);
	}

	@Override
	public T selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(T record, E example) {
		return mapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(T record, E example) {
		return mapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public void batchInsert(List<T> records) {
		mapper.batchInsert(records);
	}

  

}
