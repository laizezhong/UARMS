package com.ray.framework.service.common.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ray.framework.dao.common.GenericDao;
import com.ray.framework.service.common.GenericService;

import java.io.Serializable;
import java.util.List;

/**
 *
 *
 * @author
 * @version $Id: GenericManagerImpl.java, v 0.1 2015-6-8 10:34:12 Exp $
 */
public class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected GenericDao<T, PK> dao;

    public GenericServiceImpl(){

    }

    public GenericServiceImpl(GenericDao<T, PK> genericDao) {
        this.dao = genericDao;
    }

    @Override
    public List<T> listAll() {
        return dao.listAll();
    }

    @Override
    public T getNoDeletedObj(PK id) {
        return dao.getNoDeletedObj(id);
    }

    @Override
    public T get(Long id) {
        return dao.get(id);
    }

    @Override
    public int deleteLogically(PK id, String operatorType, PK operatorID) {
        return dao.deleteLogically(id,operatorType,operatorID);
    }


}
