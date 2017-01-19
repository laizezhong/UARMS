package com.ray.framework.service.common;

import java.io.Serializable;
import java.util.List;

/**
 *
 *
 * @author
 * @version $Id: GenericManager.java, v 0.1 2015-6-8 10:34:12 Exp $
 */
public interface GenericService<T, PK extends Serializable>  {

    /**
     * 查询所有对象
     *
     * @return
     */
    List<T> listAll();


    /**
     * 得到合法的非逻辑删除对象。
     *
     * @param id
     * @return
     */
    T getNoDeletedObj(PK id);

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    T get(final Long id);

    /**
     * 逻辑删除 :类型为 operatorType 的操作者逻辑删除对象id
     *
     * @param id
     * @param operatorType
     * @param operatorID
     * @return
     */
    int deleteLogically(final PK id,final String operatorType,final PK operatorID);

}
