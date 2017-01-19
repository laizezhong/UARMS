package com.ray.framework.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

//import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

import com.ray.framework.webapp.rest.ResourceResponseSupport;

//import com.ray.framework.admin.webapp.shiro.SessionObject;

/**
 * <p/> File Name             : BaseController
 * <p/> Author                : watson
 * <p/> Create Date           : 2015/6/7 21:42
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
public class BaseController extends ResourceResponseSupport implements ServletContextAware {
    public final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public static final String MESSAGES_KEY = "successMsg";
    public static final String ERROR_KEY = "errorMsg";

    private ServletContext servletContext;

    /**
     * 这个方法的目的是为了减少库查询
     *
     * 得到当前缓存的回话用户，很多信息可能还是老的;          *
     * 如果要通过用户取最新信息，需要调用上面的getCurrentSessionObject()方法
     *
     * @return
     */
//    public SessionObject getCurrentSessionObject(){
//    	return (SessionObject) SecurityUtils.getSubject().getPrincipal();
//    }

    @SuppressWarnings("unchecked")
    public void saveMessage(HttpServletRequest request, String msg) {
        List<String> messages = (List<String>) request.getSession().getAttribute(MESSAGES_KEY);

        if (messages == null) {
            messages = new ArrayList<String>();
        }

        messages.add(msg);
        request.getSession().setAttribute(MESSAGES_KEY, messages);
    }

    @SuppressWarnings("unchecked")
    public void saveError(HttpServletRequest request, String error) {
        List<String> errors = (List<String>) request.getSession().getAttribute(ERROR_KEY);
        if (errors == null) {
            errors = new ArrayList<String>();
        }
        errors.add(error);
        request.getSession().setAttribute(ERROR_KEY, errors);
    }



    public ServletContext getServletContext() {
        return servletContext;
    }



    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
