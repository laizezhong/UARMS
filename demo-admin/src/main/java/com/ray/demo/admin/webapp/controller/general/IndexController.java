package com.ray.demo.admin.webapp.controller.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ray.framework.controller.BaseController;

/**
 * <p/> File Name             : IndexController
 * <p/> Author                : watson
 * <p/> Create Date           : 2015/6/7 21:42
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
@Controller
@RequestMapping("/")
public class IndexController extends BaseController{

    /**
     * 后台管理系统首页
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showAdminIndex(){
    	return new ModelAndView("general/index");
    }

    
}

