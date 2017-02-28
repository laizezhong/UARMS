package com.ray.demo.admin.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <p/> File Name             : AdminMonitorInterceptor
 * <p/> Author                : watson
 * <p/> Create Date           : 2015/6/7 20:54
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
public class AdminMonitorInterceptor extends HandlerInterceptorAdapter {
    private static Logger logger = LoggerFactory.getLogger(AdminMonitorInterceptor.class);

    private NamedThreadLocal<Long> startDatetimeMSThreadLocal = new NamedThreadLocal<>("adminMonitor.startMS");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        startDatetimeMSThreadLocal.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        long costMS = System.currentTimeMillis() - startDatetimeMSThreadLocal.get();
        saveAccessTrace(request, costMS);
    }

    private String getVisitorIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && !"".equals(ip)) {
            String[] ipArray = ip.split(",");
            ip = ipArray[0];
        }
        return ip;
    }

    /**
     * @param request
     * @return
     */
    private String getUserName(HttpServletRequest request) {
    	
    	return "DevPhase";
    }

    /**
     * access trace log record
     *
     * @param request
     */
    private void saveAccessTrace(HttpServletRequest request, long visitCostMS) {
        String queryStr = request.getQueryString();
        String requestUrl = request.getRequestURL().toString() + (queryStr != null ? "?" + queryStr : "");
        String userName = getUserName(request);
        String visitorIP = getVisitorIP(request);
        if (userName != null) {
            logger.info(new StringBuilder(userName).append("(后台管理平台) 从IP ").append(visitorIP).append(" 访问地址：").append(requestUrl).append(",耗时毫秒数：").append(visitCostMS).toString());
        } else {
            logger.info(new StringBuilder("(后台管理平台) 匿名用户从IP ").append(visitorIP).append(" 访问地址：").append(requestUrl).append(",耗时毫秒数：").append(visitCostMS).toString());
        }
    }


}
