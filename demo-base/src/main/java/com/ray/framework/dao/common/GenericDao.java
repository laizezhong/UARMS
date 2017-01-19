package com.ray.framework.dao.common;

import org.apache.ibatis.annotations.Param;

import com.ray.framework.dao.vo.Page;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {
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
    T getNoDeletedObj(final PK id);

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    T get(final Long id);

    /**
     * 查询数据列表
     *
     * @param page
     * @return
     */
    List<T> searchPage(Page<T> page);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    int insert(T entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    int update(T entity);

    /**
     * 删除数据 :类型为 operatorType 的操作者逻辑删除对象id
     *
     * @param id
     * @param operatorType
     * @param operatorID
     * @return
     */
    int deleteLogically(@Param("bizObjID") final PK id,@Param("operatorType") final String operatorType,@Param("operatorID") final PK operatorID);

}
