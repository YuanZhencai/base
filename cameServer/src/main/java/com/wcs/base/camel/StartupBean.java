package com.wcs.base.camel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * <p>Project: came</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yansong@wcs-global.com">Yan Song</a>
 * $Rev::               $:  Revision of last commit
 * $Author::            $:  Author of last commit
 * $Date::              $:  Date of last commit 
 */
@Singleton
@Startup
public class StartupBean{

	@EJB
	JmsService jmsService;
	@PostConstruct
	private void startup() {
		jmsService.startService();
	}

	@PreDestroy
	private void shutdown() {
		jmsService.stopService();
	}

}