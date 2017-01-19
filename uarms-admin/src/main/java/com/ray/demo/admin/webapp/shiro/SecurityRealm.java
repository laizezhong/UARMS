package com.ray.demo.admin.webapp.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ray.demo.admin.manager.system.SystemManager;
import com.ray.demo.admin.model.system.Permission;
import com.ray.demo.admin.model.system.SysRole;
import com.ray.demo.admin.model.system.SysUser;
import com.ray.framework.util.SecurityUtil;


/**
 * 用户身份验证,授权 Realm 组件
 * 
 * 
 * 
 **/
@Service
public class SecurityRealm extends AuthorizingRealm  {

    @Autowired
    private SystemManager manager;
    
	/**
     * 授权
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		SessionObject currentUser = (SessionObject) SecurityUtils.getSubject().getPrincipal();

		// 获取角色
		info.setRoles(currentUser.getRoleStrList());
		
		// 获取权限
		info.setStringPermissions(currentUser.getPermStrList());

		return info;
	}

	/**
     * 登录验证
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		
		SessionObject sessionObj = new SessionObject();
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		if(StringUtils.isBlank(token.getUsername()) || token.getPassword().length == 0){
			throw new AuthenticationException("用户名或密码为空!");
		}
		
		SysUser user = null;
		List<SysUser> users = manager.getUsersByName(token.getUsername());
		
		if (null == users || users.size() != 1) {
			 throw new AuthenticationException("用户名或密码错误!");
		} else {
			user = users.get(0);
		}  

		if(user == null){
			 throw new AuthenticationException("用户名或密码错误!");
		}else { 
			if(!SecurityUtil.getEncryptedPassword(String.valueOf(token.getPassword()),token.getUsername()).equals(user.getPassword())){
				//System.out.println(SecurityUtil.getEncryptedPassword(String.valueOf(token.getPassword()),token.getUsername()));
	            throw new AuthenticationException("用户名或密码错误!");
			}
            if(user.getActivestatus().equals((byte)0)){
            	throw new AuthenticationException("该用户已经被锁定，请联系管理员申请解锁！");
        	}
        }

		// 设置用户信息   
		sessionObj.setUser(user);

		// 获取该用户下所有角色设置到sessionObject中
		List<Long> roleIds = manager.getRoleIdsByUserId(user.getId());
		
		List<SysRole> roleLists = manager.getRolesByRoleIds(roleIds);
		if (roleLists != null && roleLists.size() > 0) {
			Set<SysRole> roleSets = new HashSet<SysRole>(roleLists);	
			sessionObj.setRoleList(roleSets);
			
			Set<String> roleStrSets = new HashSet<String>();
			for (SysRole role : roleLists ){
				roleStrSets.add(role.getRolecode());
			}
			sessionObj.setRoleStrList(roleStrSets);
		}
		// 获取该用户下所有权限设置到sessionObject中
		Set<Long> permIds = manager.getPermIdsByRoleIds(roleIds);
		if(permIds != null && permIds.size() > 0){
			Set<Permission> perms = manager.getPermsByPermIds(new ArrayList<Long>(permIds));
			if (perms != null && perms.size() >0 ) {
				sessionObj.setPermList(perms);
				
				Set<String> permStrSets = new HashSet<String>();
				for (Permission perm : perms) {
					permStrSets.add(perm.getPermission());
				}
				sessionObj.setPermStrList(permStrSets);
			}
		}
		
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(sessionObj, token.getPassword(),token.getUsername());
		
		//Subject subject = SecurityUtils.getSubject();
		//subject.getSession().setAttribute("currentUser", user);
		//System.out.println("Timeout: " + subject.getSession().getTimeout()); 
		
		return authcInfo;
	}

}
