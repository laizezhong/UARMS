package com.ray.framework.webapp.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ray.framework.manager.cache.SpringContextHolder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p/> File Name             : SpringContextListener
 * <p/> Author                : watson
 * <p/> Create Date           : 2015/6/7 20:55
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
public class SpringContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(SpringContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        SpringContextHolder.getInstance().setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()));

        setupCurrentAppServer(servletContextEvent.getServletContext()); //设置当前应用服务器是哪台
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // ignore
    }

    /**
     * 用于定位当前页面是哪台应用服务器
     *
     * @param servletContext
     */
    private void setupCurrentAppServer(ServletContext servletContext){
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            if(inetAddress!=null){
                String serverIP = inetAddress.getHostAddress();
                String serverName = inetAddress.getHostName();
                servletContext.setAttribute("appServerIPName", serverName+serverIP.substring(serverIP.lastIndexOf(".")));
            }
        } catch (UnknownHostException e) {
            logger.error("SpringContextListener.setupCurrentAppServer",e);
        }
    }
}