package com.ray.demo.admin.webapp.shiro.wrapper;

import com.ray.demo.admin.model.system.SysUser;

public class AngularShiroLoginResponse {
	private AngularShiroInfo info;
	private SysUser user;
	private boolean stillInitPwd = false;

	public AngularShiroInfo getInfo() {
		return info;
	}

	public void setInfo(AngularShiroInfo info) {
		this.info = info;
	}

	public boolean isStillInitPwd() {
		return stillInitPwd;
	}

	public void setStillInitPwd(boolean stillInitPwd) {
		this.stillInitPwd = stillInitPwd;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
	
	
	
}
