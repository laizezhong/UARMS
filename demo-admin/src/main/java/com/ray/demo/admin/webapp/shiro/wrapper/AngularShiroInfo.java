package com.ray.demo.admin.webapp.shiro.wrapper;

public class AngularShiroInfo {
	AngularShiroAuthc authc;
	AngularShiroAuthz authz;
	public AngularShiroAuthc getAuthc() {
		return authc;
	}
	public void setAuthc(AngularShiroAuthc authc) {
		this.authc = authc;
	}
	public AngularShiroAuthz getAuthz() {
		return authz;
	}
	public void setAuthz(AngularShiroAuthz authz) {
		this.authz = authz;
	}
}
