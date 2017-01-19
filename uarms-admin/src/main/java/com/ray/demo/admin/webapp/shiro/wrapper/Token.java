package com.ray.demo.admin.webapp.shiro.wrapper;

public class Token {
	private String principal;
	private String credentials;

	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getCredentials() {
		return credentials;
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	
}