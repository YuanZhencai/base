package com.wcs.commons.security.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wcs.commons.security.model.Resource;
import com.wcs.commons.security.service.LoginService;
import com.wcs.commons.security.service.ResourceCache;
import com.wcs.base.util.JSFUtils;
import com.wcs.base.util.MessageUtils;

/**
 * <p>Project: btcbase-web</p> 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author guanjianghuai
 */
@Named
@ConversationScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);

	@Inject
	private LoginService loginService;
	
	@EJB
	ResourceCache resourceCache;

	private final String LOGIN_SUCCESS = "/template/template.xhtml";
	private final String LOGIN_PAGE = "/login.xhtml";

	/**
	 * user login
	 */
	public String userLogin() {
		// 用户认证
		String adAccount = JSFUtils.getRequestParam("loginName");
		
		// 装入INI配置, 设置为VM静态Singleton
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(adAccount, "");  //?密码为空？
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			logger.info("There is no user with username of " + token.getPrincipal());
			MessageUtils.addErrorMessage("longmessgeId", "用户无效，请检查！");
			return LOGIN_PAGE;
		} catch (IncorrectCredentialsException ice) {
			logger.info("Password for account " + token.getPrincipal() + " was incorrect!");
			return LOGIN_PAGE;
		} catch (LockedAccountException lae) {
			logger.info("The account for username " + token.getPrincipal() + " is locked.  " + "Please contact your administrator to unlock it.");
			return LOGIN_PAGE;
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			return LOGIN_PAGE;
		}
		
		// 初始化系统资源
		List<List<Resource>> allResList = initAllResources();
		JSFUtils.getSession().put("user", adAccount);
		JSFUtils.getSession().put("allResList", allResList);
		JSFUtils.getSession().put("selectId", 2); // 选中的菜单

		return LOGIN_SUCCESS;
	}

	/**
	 * Init all sys resource
     * @return
     */
    private List<List<Resource>> initAllResources() {
        List<Resource> resList = resourceCache.loadAllResources();
        Map<String, List<Resource>> allResMap = Maps.newHashMap();
        
        for (Resource r : resList) {
            String level = "level" + r.getLevel();
            List<Resource> list = allResMap.get(level);
            if (CollectionUtils.isEmpty(list)) {
                allResMap.put(level, new ArrayList<Resource>());
            }
            allResMap.get(level).add(r);
        }
        
        List<List<Resource>> allResList = Lists.newArrayList();
        for (int i = 0; i < allResMap.size(); i++) {
            allResList.add(allResMap.get("level" + i));
        }
        return allResList;
    }

    /**
	 * 注销用户
	 */
	public String doLogout() {
		// 关闭Session
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return LOGIN_PAGE;
	}

	/**
	 * 改变导航菜单
	 * @param selectId
	 * @return
	 */
	public String changeMenu(Long selectId) {
		JSFUtils.getSession().put("selectId", selectId);
		return LOGIN_SUCCESS;
	}
}