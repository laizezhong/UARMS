package com.ray.demo.admin.manager.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ray.demo.admin.manager.BaseManager;
import com.ray.demo.admin.model.system.Permission;
import com.ray.demo.admin.model.system.PermissionExample;
import com.ray.demo.admin.model.system.RolePermission;
import com.ray.demo.admin.model.system.RolePermissionExample;
import com.ray.demo.admin.model.system.SysRole;
import com.ray.demo.admin.model.system.SysRoleExample;
import com.ray.demo.admin.model.system.SysUser;
import com.ray.demo.admin.model.system.SysUserExample;
import com.ray.demo.admin.model.system.SysUserroleExample;
import com.ray.demo.admin.model.system.SysUserroleKey;
import com.ray.demo.admin.service.system.PermissionService;
import com.ray.demo.admin.service.system.RolePermissionService;
import com.ray.demo.admin.service.system.RoleService;
import com.ray.demo.admin.service.system.UserRoleService;
import com.ray.demo.admin.service.system.UserService;
import com.ray.framework.util.SecurityUtil;

@Component
public class SystemManager extends BaseManager{
	
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userroleService;
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePerService;
    
    @Autowired
    private PermissionService permissionService;
	
	
	Logger logger = this.getLoggerObject();
	//private final SessionObject session = this.getCurrentSessionObject();
	
	private final String INIT_PWD = "123456";
    
	public List<SysUser> listUsersByName(String userName){
		SysUserExample userExample = new SysUserExample();
		SysUserExample.Criteria criteria = userExample.createCriteria();
		SysUserExample.Criteria criteria2 = userExample.createCriteria();
		
		if (null!=userName && !userName.isEmpty()) {
			criteria.andUsernameLike("%" + userName + "%");
			//criteria.andActivestatusEqualTo((byte) 1);
			
			criteria2.andRealnameLike("%" + userName + "%");
			//criteria2.andActivestatusEqualTo((byte) 1);
			
			userExample.or(criteria2);		
			return userService.selectByExample(userExample);
			
		} else {
			//criteria.andActivestatusEqualTo((byte) 1);
			//return userService.selectByExample(userExample);
			return userService.listAll();
		}
	}
	
	public List<SysUser> getUsersByName(String userName){
		SysUserExample userExample = new SysUserExample();
		SysUserExample.Criteria criteria = userExample.createCriteria();
		
		if (null!=userName && !userName.isEmpty()) {
			criteria.andUsernameEqualTo(userName);
			//criteria.andActivestatusEqualTo((byte) 1);

			return userService.selectByExample(userExample);
			
		} else {
			//criteria.andActivestatusEqualTo((byte) 1);
			//return userService.selectByExample(userExample);
			return userService.listAll();
		}
	}
	
	public List<SysRole> listRolesByName(String roleName){
		SysRoleExample roleExample = new SysRoleExample();
		SysRoleExample.Criteria criteria = roleExample.createCriteria();
		SysRoleExample.Criteria criteria2 = roleExample.createCriteria();
		
		if (null!=roleName && !roleName.isEmpty()) {
			criteria.andRolenameLike("%" + roleName + "%");
			criteria2.andRolecodeLike("%" + roleName + "%");	
			roleExample.or(criteria2);		
			return roleService.selectByExample(roleExample);
			
		} else {

			return roleService.listAll();
		}
	}
	
	public List<RolePermission> listRoleAuthsByRoleId(Long roleId){
		RolePermissionExample example = new RolePermissionExample();
		RolePermissionExample.Criteria criteria = example.createCriteria();
		
		if (null!=roleId) {
			criteria.andRoleIdEqualTo(roleId);

			return rolePerService.selectByExample(example);
			
		} else {

			return rolePerService.listAll();
		}
	}
	
	public List<Permission> listAuthsByGroupAndName(String authGroup, String authName){
		PermissionExample permExample = new PermissionExample();
		PermissionExample.Criteria criteria = permExample.createCriteria();
		
		permExample.setOrderByClause(" group_name asc ");
		if (null!=authName && !authName.isEmpty()) {
			criteria.andNameLike("%" + authName + "%");		
		} 
		if (null!=authGroup && !authGroup.isEmpty()) {
			criteria.andGroupNameLike("%" + authGroup + "%");		
		} 
		
		return permissionService.selectByExample(permExample);
	}
	
	public int addAuth(Permission permission){
		return permissionService.insert(permission);
	}
	
	public int editAuth(Permission permission){
		return permissionService.updateByPrimaryKeySelective(permission);
	}
	
	public int deleteAuth(Long id){
		return permissionService.deleteByPrimaryKey(id);
	}
	
	public List<SysRole> getRolesByName(String roleName){
		SysRoleExample roleExample = new SysRoleExample();
		SysRoleExample.Criteria criteria = roleExample.createCriteria();
		
		if (null!=roleName && !roleName.isEmpty()) {
			criteria.andRolenameEqualTo(roleName);
			return roleService.selectByExample(roleExample);
			
		} else {
			return roleService.listAll();
		}
	}
	
	public List<SysRole> getRolesByCode(String roleCode){
		SysRoleExample roleExample = new SysRoleExample();
		SysRoleExample.Criteria criteria = roleExample.createCriteria();
		
		if (null!=roleCode && !roleCode.isEmpty()) {
			criteria.andRolecodeEqualTo(roleCode);
			return roleService.selectByExample(roleExample);
			
		} else {
			return roleService.listAll();
		}
	}
	
	public SysUser findByUserId (long userId){
		return userService.selectByPrimaryKey(userId);
	}

	@Transactional
	public int updateUserStatus (SysUser user){
		user.setUpdater(this.getCurrentSessionObject().getUser().getId());
		user.setUpdatetime(new Date());
		return userService.updateByPrimaryKeySelective(user);
	}

	@Transactional
	public int resetUserPwd (SysUser user){
		
		String encryptPwd = SecurityUtil.getEncryptedPassword(INIT_PWD,user.getUsername());
		
		user.setUpdater(this.getCurrentSessionObject().getUser().getId());
		user.setUpdatetime(new Date());
		user.setPassword(encryptPwd);
		
		return userService.updateByPrimaryKeySelective(user);
	}
	
	
	@Transactional
	public SysUser updateUserWithoutPWD (SysUser user){
		user.setUpdater(this.getCurrentSessionObject().getUser().getId());
		user.setUpdatetime(new Date());
		user.setUsername(null);
		user.setPassword(null); // 只允许用户自己可以修改密码
		userService.updateByPrimaryKeySelective(user);
		return userService.selectByPrimaryKey(user.getId());
	}

	@Transactional
	public SysUser updateUser (SysUser user){
		String password = user.getPassword();
		String encryptPwd = null;
		if (null != password && password.trim().length() > 0){
			encryptPwd = SecurityUtil.getEncryptedPassword(password.trim(),user.getUsername());
		}
		user.setUsername(null);
		user.setPassword(encryptPwd);
		user.setUpdater(this.getCurrentSessionObject().getUser().getId());
		user.setUpdatetime(new Date());
		userService.updateByPrimaryKeySelective(user);
		return userService.selectByPrimaryKey(user.getId());
	}
	
	@Transactional
	public SysUser addUser (SysUser user){
		
		String encryptPwd = SecurityUtil.getEncryptedPassword(user.getPassword().trim(),user.getUsername());
		
		user.setCreator(this.getCurrentSessionObject().getUser().getId());
		user.setCreatetime(new Date());
		user.setUpdater(this.getCurrentSessionObject().getUser().getId());
		user.setUpdatetime(new Date());
		user.setPassword(encryptPwd);
		user.setActivestatus((byte) 1);
		userService.insert(user);
		return user;
	}
	
	@Transactional
	public SysRole updateRole (SysRole role){
		role.setRolecode(null);
		role.setUpdater(this.getCurrentSessionObject().getUser().getId());
		role.setUpdatetime(new Date());
		roleService.updateByPrimaryKeySelective(role);
		return roleService.selectByPrimaryKey(role.getId());
	}
	
	@Transactional
	public SysRole addRole (SysRole role){
	
		role.setCreator(this.getCurrentSessionObject().getUser().getId());
		role.setCreatetime(new Date());
		role.setUpdater(this.getCurrentSessionObject().getUser().getId());
		role.setUpdatetime(new Date());
		roleService.insert(role);
		return role;
	}
	
	@Transactional
	public List<Permission> updateRoleAuths (long roleId, List<Permission> perms){
	
		RolePermissionExample example = new RolePermissionExample();
		RolePermissionExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);	
		rolePerService.deleteByExample(example);
		
		if (null != perms && perms.size() > 0) {
			List<RolePermission> rolePerms = new ArrayList<RolePermission>();
			for (Permission per : perms) {
				RolePermission rolePer = new RolePermission();
				rolePer.setRoleId(roleId);
				rolePer.setPermissionId(per.getId());
				rolePerms.add(rolePer);
			}
			rolePerService.batchInsert(rolePerms);
		}
		return perms;
	}
	

	@Transactional
	public List<SysRole> updateUserRoles (long userId, List<SysRole> roles){
	
		SysUserroleExample example = new SysUserroleExample();
		SysUserroleExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);	
		userroleService.deleteByExample(example);
		
		if (null != roles && roles.size() > 0) {
			List<SysUserroleKey> userRoles = new ArrayList<SysUserroleKey>();
			for (SysRole role : roles) {
				SysUserroleKey userRole = new SysUserroleKey();
				userRole.setUserId(userId);
				userRole.setRoleId(role.getId());
				userRoles.add(userRole);
			}
			userroleService.batchInsert(userRoles);
		}
		return roles;
	}
	
	public List<SysRole> getRolesByUserId(Long userId){
		List<Long> roleIds = getRoleIdsByUserId(userId);
		return getRolesByRoleIds(roleIds);
	}
	
	public List<Long> getRoleIdsByUserId (Long userId){
		
		List<SysUserroleKey> userroleLists = null;
		if (userId == null) {
			userroleLists = userroleService.listAll();
		} else {
			SysUserroleExample userRoleExample = new SysUserroleExample();
			userRoleExample.createCriteria().andUserIdEqualTo(userId);	
			userroleLists = userroleService.selectByExample(userRoleExample);
		}
		List<Long> roleIds = new ArrayList<Long>();
		for (SysUserroleKey userrole : userroleLists){
			roleIds.add(userrole.getRoleId());
		}
		
		return roleIds;
	}
	
	public List<SysRole> getRolesByRoleIds (List<Long> roleIds) {
		
		if (null == roleIds || roleIds.size() == 0 ) {
			return null;
		}
		
		SysRoleExample roleExample = new SysRoleExample();
		roleExample.createCriteria().andIdIn(roleIds);
		List<SysRole> roleLists = roleService.selectByExample(roleExample);
		
		return roleLists;
	}

	public Set<Long> getPermIdsByRoleIds (List<Long> roleIds) {
		
		if (null == roleIds || roleIds.size() == 0 ) {
			return null;
		}

		RolePermissionExample rolePerExample = new RolePermissionExample();
		rolePerExample.setDistinct(true);
		rolePerExample.createCriteria().andRoleIdIn(roleIds);
		List<RolePermission> rolePerLists = rolePerService.selectByExample(rolePerExample);
		
		Set<Long> permIds = new HashSet<Long>();
		for (RolePermission rolePer : rolePerLists) {
			permIds.add(rolePer.getPermissionId());
		}
		
		return permIds;
	}
	
	public Set<Permission> getPermsByPermIds (List<Long> permIds) {
		
		if (null == permIds || permIds.size() == 0 ) {
			return null;
		}

		PermissionExample perExample = new PermissionExample();
		perExample.setDistinct(true);
		perExample.createCriteria().andIdIn(permIds);
		List<Permission> perLists = permissionService.selectByExample(perExample);
		
		Set<Permission> perms = new HashSet<Permission>(perLists);

		return perms;
	}
	
}
