package com.wcs.base.camel;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class StartupListener implements ServletContextListener {

	JmsService jmsService = new JmsService();
	
    public void contextDestroyed(ServletContextEvent sce) {
    	jmsService.stopService();
    	
    }

    public void contextInitialized(ServletContextEvent sce) {
    	jmsService.startService();
    }

}