package com.ray.demo.admin.webapp.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ray.framework.controller.BaseController;
import com.ray.framework.webapp.rest.RestResultResponse;
import com.ray.demo.admin.manager.system.SystemManager;
import com.ray.demo.admin.model.system.Permission;
import com.ray.demo.admin.model.system.SysRole;
import com.ray.demo.admin.model.system.SysUser;


@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	
	@Autowired
	private SystemManager manager;


	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> listUsers(@RequestParam(value = "userName") String userName) {
		try {

			Object ob = manager.listUsersByName(userName);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/users/exist", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> findExistUsers(@RequestParam(value = "userName") String userName) {
		try {

			Object ob = manager.getUsersByName(userName);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RestResultResponse> addUser(@RequestBody SysUser user) {
		try {

			Object ob = manager.addUser(user);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RestResultResponse> editUser(
			@PathVariable("id") long id, @RequestBody SysUser user) {
		try {

			Object ob = manager.updateUser(user);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<RestResultResponse> updateUserStatus(
			@PathVariable("id") long id,
			@RequestParam(value = "func") String func) {
		try {

			SysUser user = manager.findByUserId(id);
			if ("lock".equals(func)) {
				user.setActivestatus((byte) 0);
				manager.updateUserStatus(user);
			} else if ("unlock".equals(func)){
				user.setActivestatus((byte) 1);
				manager.updateUserStatus(user);
			} else if ("reset".equals(func)) {
				manager.resetUserPwd(user);
			}
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(user), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> listRoles(@RequestParam(value = "roleName") String roleName) {
		try {

			Object ob = manager.listRolesByName(roleName);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	
	@RequestMapping(value = "/userroles", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> getUserRoles(@RequestParam(value = "userId") long userId) {
		try {

			Object ob = manager.getRolesByUserId(userId);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/roleauths", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> listRoleAuths(@RequestParam(value = "roleId") Long roleId) {
		try {

			Object ob = manager.listRoleAuthsByRoleId(roleId);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/roles/existname", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> findExistRoleName(@RequestParam(value = "roleName") String roleName) {
		try {

			Object ob = manager.getRolesByName(roleName);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/roles/existcode", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> findExistRoleCode(@RequestParam(value = "roleCode") String roleCode) {
		try {

			Object ob = manager.getRolesByCode(roleCode);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RestResultResponse> addRole(@RequestBody SysRole role) {
		try {

			Object ob = manager.addRole(role);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@RequestMapping(value = "/roleauths/{roleId}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RestResultResponse> updateRoleAuths(
			@PathVariable("roleId") long roleId,
			@RequestBody List<Permission> permissions) {
		try {

			Object ob = manager.updateRoleAuths(roleId, permissions);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@RequestMapping(value = "/userroles/{userId}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RestResultResponse> updateUserRoles(
			@PathVariable("userId") long userId,
			@RequestBody List<SysRole> roles) {
		try {

			Object ob = manager.updateUserRoles(userId, roles);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RestResultResponse> editRole(
			@PathVariable("id") long id, @RequestBody SysRole role) {
		try {

			Object ob = manager.updateRole(role);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/auths", method = RequestMethod.GET)
	public ResponseEntity<RestResultResponse> listAuths(
			@RequestParam(value = "authGroup") String authGroup,
			@RequestParam(value = "authName") String authName) {
		try {

			Object ob = manager.listAuthsByGroupAndName(authGroup, authName);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/auths", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RestResultResponse> addAuth(@RequestBody Permission permisson) {
		try {

			Object ob = manager.addAuth(permisson);
			
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/auths/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<RestResultResponse> editAuth(@RequestBody Permission permisson){
		try {
			Object ob = manager.editAuth(permisson);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
		return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
				HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/auths/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<RestResultResponse> deleteAuth(@PathVariable("id") long id){
		try {
			Object ob = manager.deleteAuth(id);
			return new ResponseEntity<RestResultResponse>(this.buildSuccessRestResultResponse(ob), HttpStatus.OK);
		} catch (Exception ex) {
		return new ResponseEntity<RestResultResponse>(this.buildErrorRestResultResponse(ex),
				HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
