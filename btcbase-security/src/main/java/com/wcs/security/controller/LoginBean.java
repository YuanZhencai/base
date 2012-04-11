package com.wcs.security.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wcs.security.model.Resource;
import com.wcs.security.service.ResourceService;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: LoginBean.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2005-2011.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class LoginBean implements Serializable {
	private static final Logger log = LoggerFactory.getLogger(LoginBean.class);
	@EJB
	private ResourceService resourceService;
	private List<List<Resource>> allResList = Lists.newArrayList(); // 系统的全部资源

	public LoginBean() {
	}


	/**
	 * 用户登录验证
	 * @return
	 */
	public List<List<Resource>> userLogin(String loginName, String password) {
		// 登陆认证
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			log.info("There is no user with username of " + token.getPrincipal());
		} catch (IncorrectCredentialsException ice) {
			log.info("Password for account " + token.getPrincipal() + " was incorrect!");
		} catch (LockedAccountException lae) {
			log.info("The account for username " + token.getPrincipal() + " is locked.  " + "Please contact your administrator to unlock it.");
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
		}
		
		// 初始系统资源
		List<List<Resource>> allResList = Lists.newArrayList();
		allResList = initAllResources();

		return allResList;
	}

	private List<List<Resource>> initAllResources() {
		log.info("ResourceBean => initAllResources()");
		List<Resource> resList = resourceService.findAllSysResource();
		Map<String, List<Resource>> allResMap = Maps.newHashMap();

		for (Resource r : resList) {
			String level = "level" + r.getLevel();

			List<Resource> list = allResMap.get(level);

			if (CollectionUtils.isEmpty(list)) {
				allResMap.put(level, new ArrayList<Resource>());
			}

			allResMap.get(level).add(r);
		}

		for (int i = 0; i < allResMap.size(); i++) {
			allResList.add(allResMap.get("level" + i));
		}
		
		return allResList;
	}

	/**
	 * 注销用户
	 */
	public void doLogout() {
		// 关闭Session
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
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
}
