package com.wcs.common.shiro;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class SecurityFacade {
	Logger logger = LoggerFactory.getLogger(SecurityFacade.class);

	ShiroDbRealm shiroDbRealm;
	
	@PostConstruct
	public void init() {
		SecurityManager securityManager = new DefaultWebSecurityManager(shiroDbRealm);

		//Make the SecurityManager instance available to the entire application via static memory:
		SecurityUtils.setSecurityManager(securityManager);

	}

}