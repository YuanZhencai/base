package com.wcs.base.camel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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