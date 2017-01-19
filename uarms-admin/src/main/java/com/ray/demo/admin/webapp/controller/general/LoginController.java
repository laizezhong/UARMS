package com.ray.demo.admin.webapp.controller.general;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ray.demo.admin.webapp.shiro.SessionObject;
import com.ray.demo.admin.webapp.shiro.wrapper.AngularShiroAuthc;
import com.ray.demo.admin.webapp.shiro.wrapper.AngularShiroAuthz;
import com.ray.demo.admin.webapp.shiro.wrapper.AngularShiroCredentials;
import com.ray.demo.admin.webapp.shiro.wrapper.AngularShiroInfo;
import com.ray.demo.admin.webapp.shiro.wrapper.AngularShiroLoginResponse;
import com.ray.demo.admin.webapp.shiro.wrapper.AngularShiroPrincipal;
import com.ray.demo.admin.webapp.shiro.wrapper.TokenWrapper;
import com.ray.framework.controller.BaseController;
import com.ray.framework.util.SecurityUtil;

@Controller
public class LoginController extends BaseController{

	private final String INIT_PWD = "123456";
	

//    @RequestMapping("/login")
//    public String login(Model model) {
//    	model.addAttribute("username", "admin");
//    	model.addAttribute("password", "admin");
//        return "/general/login";
//    }
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){

		ModelAndView modelAndView = new ModelAndView("general/login");
		return modelAndView;
    }
   
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginCheck(String username,String password){
    	
    	ModelAndView modelAndView = new ModelAndView("redirect:/");
    	//ModelAndView modelAndView = new ModelAndView("general/index");
    	
    	Subject subject = SecurityUtils.getSubject();
		//判断是否已登录
		if(subject.isAuthenticated()){
			subject.logout();
		}

		modelAndView.addObject("username", username);
//		modelAndView.addObject("password", password);
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			modelAndView.addObject("loginError", "用户名或密码为空！");
			modelAndView.setViewName("general/login");
			return modelAndView;
		}

		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		// 调用权限框架登录认证
		try{
		subject.login(token);
		//subject.isPermitted("admin");
		subject.hasRole("admin");
		
		}catch(AuthenticationException ex){
			String errMsg = ex.getMessage();
			if (null != ex.getCause()) {
				errMsg = ex.getCause().getMessage();
			}
			modelAndView.addObject("loginError", errMsg);
			modelAndView.setViewName("general/login");
		}catch(Exception e){
			e.printStackTrace();
			modelAndView.addObject("loginError", "系统异常！");
			modelAndView.setViewName("general/login");
		}

        return modelAndView;
    }
    
    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public AngularShiroLoginResponse getAuth(@RequestBody TokenWrapper command){
    	
    	//SecurityUtil.getEncryptedPassword(String.valueOf(token.getPassword()),token.getUsername()).equals(user.getPassword())
    	
    	SessionObject currentUser = (SessionObject) SecurityUtils.getSubject().getPrincipal();
    	
    	AngularShiroPrincipal principal = new AngularShiroPrincipal();
    	principal.setName(currentUser.getUser().getRealname());
    	principal.setLogin(currentUser.getUser().getUsername());
    	principal.setEmail(currentUser.getUser().getEmail());

    	AngularShiroCredentials credentials = new AngularShiroCredentials();
    	credentials.setName(currentUser.getUser().getRealname());
    	
    	AngularShiroAuthc authc = new AngularShiroAuthc();
    	authc.setPrincipal(principal);
    	authc.setCredentials(credentials);
    	
    	AngularShiroAuthz authz = new AngularShiroAuthz();
    	authz.setRoles(currentUser.getRoleStrList());
    	authz.setPermissions(currentUser.getPermStrList());
    	
    	AngularShiroInfo info = new AngularShiroInfo();
    	info.setAuthc(authc);
    	info.setAuthz(authz);
    	
    	AngularShiroLoginResponse response = new AngularShiroLoginResponse();
    	response.setInfo(info);
    	
    	response.setStillInitPwd(false);
    	if (SecurityUtil.getEncryptedPassword(INIT_PWD,currentUser.getUser().getUsername())
    		.equals(currentUser.getUser().getPassword())){
    		response.setStillInitPwd(true);
    		currentUser.getUser().setPassword(null);
    	}
    	
    	response.setUser(currentUser.getUser());
    	return response;
    
    }
    
}
