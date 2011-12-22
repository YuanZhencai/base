package com.wcs.common.controller.permissions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.conf.Constants;
import com.wcs.base.util.JSFUtils;
import com.wcs.base.util.MessageUtils;
import com.wcs.common.model.Resource;
import com.wcs.common.model.User;
import com.wcs.common.service.permissions.LoginService;
import com.wcs.demo.test.CustomAuthorizer;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: LoginBean.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2005-2011.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@Named
@SessionScoped
@SuppressWarnings("serial")
public class LoginBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(LoginBean.class);
    private static final String LOGIN_SUCCESS = "/template/template.xhtml";

    @Inject
    private LoginService loginService;
    
    private boolean isManager = false; // 是否是管理员

    public LoginBean() {}
    
	/**
	 * 用户登录验证
	 * @return
	 */
	public String userLogin() {
        String userName = JSFUtils.getRequestParam("userName");
        
        Subject currentUser = SecurityUtils.getSubject();

        // 当前用户是否已经登录
        if (currentUser.isAuthenticated()) {
            if (userName.equals(currentUser.getPrincipal())) { return Constants.FAILURED; }
        }

        // 用户认证
        User user = loginService.findUniqueUser(userName);
		if (user == null) {
			MessageUtils.addErrorMessage("longmessgeId", "用户无效，请检查！");
			return Constants.FAILURED;
		}        
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getUserPass());
        
        //token.setHost("localhost");
        token.setRememberMe(true);        

        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            log.info("There is no user with username of " + token.getPrincipal());
            return Constants.FAILURED;
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            return Constants.FAILURED;
        } catch (LockedAccountException lae) {
            log.info("The account for username " + token.getPrincipal() + " is locked.  " + "Please contact your administrator to unlock it.");
            return Constants.FAILURED;
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            return Constants.FAILURED;
        }

        this.isManager=  loginService.isAdmin(user.getId());
        JSFUtils.getSession().put("loginName",user.getUserName());
        JSFUtils.getSession().put("userName",user.getName());
        JSFUtils.getSession().put("selectId",1);  //选中的菜单

		return Constants.SUCCESS;
	}    
	
	/**
	 * 注销用户
	 */
	public String doLogout() {
		// 关闭Session
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		
		return Constants.SUCCESS;
	}
 
    /**
     * 权限检查
     * @param resource
     * @return
     */
    public String hasPermission(Resource resource) {
        String permissionString = "view:" + resource.getKeyName();
        
        return permissionString.trim().toString();
    }

    /**
     * 
     * <p>
     * Description:改变导航菜单
     * </p>
     * 
     * @param selectID
     * @return
     */
    public String changeMenu(Long selectId) {
    	JSFUtils.getSession().put("selectId",selectId);
//        leftMenuResouce.clear();
        // intitLeftMenu(selectId);
        // 系统全部资源
//        List<Resource> sysResouceList = null; // resouceBean.getSysResouce();
//        loginService.intitLeftMenu(selectId, leftMenuResouce, allUserResouce, sysResouceList);
        return LOGIN_SUCCESS;
    }



    // ----------------------------- set & get -------------------------------//



    /**
     * @return the isManager
     */
    public boolean isManager() {
        return isManager;
    }

    public boolean isQueryDeleted() {
        return false;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

}