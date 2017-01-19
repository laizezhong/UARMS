package com.ray.demo.admin.webapp.shiro;

import java.io.Serializable;
import java.util.Set;

import com.ray.demo.admin.model.system.Permission;
import com.ray.demo.admin.model.system.SysRole;
import com.ray.demo.admin.model.system.SysUser;

public class SessionObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Set<SysRole> roleList;
	private Set<Permission> permList;
	private Set<String> roleStrList;
	private Set<String> permStrList;
	private SysUser user;
	
	public Set<SysRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(Set<SysRole> roleList) {
		this.roleList = roleList;
	}
	public Set<Permission> getPermList() {
		return permList;
	}
	public void setPermList(Set<Permission> permList) {
		this.permList = permList;
	}
	public Set<String> getRoleStrList() {
		return roleStrList;
	}
	public void setRoleStrList(Set<String> roleStrList) {
		this.roleStrList = roleStrList;
	}
	public Set<String> getPermStrList() {
		return permStrList;
	}
	public void setPermStrList(Set<String> permStrList) {
		this.permStrList = permStrList;
	}
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}

}
