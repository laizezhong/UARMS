package com.ray.demo.admin.manager;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ray.demo.admin.webapp.shiro.SessionObject;

public class BaseManager {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	public Logger getLoggerObject() {
		return logger;
	}

	public SessionObject getCurrentSessionObject() {
	
		try {
			
			return (SessionObject) SecurityUtils.getSubject().getPrincipal();
			
		} catch (Exception e) {
			return null;
		}

	}

}
