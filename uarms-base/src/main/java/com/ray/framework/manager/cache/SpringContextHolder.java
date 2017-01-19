package com.ray.framework.manager.cache;

import org.springframework.context.ApplicationContext;

/**
 * <p/> File Name             : SpringContextHolder
 * <p/> Author                : watson
 * <p/> Create Date           : 2015/6/7 17:37
 * <p/> Description           :
 * <p/>
 * <p/>
 * <p/>
 * <p/> Reviewed By           :
 * <p/> Reviewed On           :
 * <p/> Version History       :
 * <p/> Modified By           :
 * <p/> Modified Date         :
 * <p/> Comments              :
 * <p/> CopyRight             :
 * <p/> *******************************************************************************************
 */
public class SpringContextHolder {
    private static SpringContextHolder springContextHolder = new SpringContextHolder();

    private ApplicationContext applicationContext;

    private SpringContextHolder(){}

    public static SpringContextHolder getInstance()
    {
        return springContextHolder;
    }

    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    /**
     * 根据beanName 获得内存bean
     *
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }
}
