package com.wcs.base.camel;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;

import org.apache.camel.impl.SimpleRegistry;

import com.wcs.base.test.TestServiceOne;
import com.wcs.base.test.TestServiceTwo;
import com.wcs.came.CamelComponent;
import com.wcs.came.CamelComponentFactory;

@Stateless
public class JmsService {
	@Resource(name = "jms/ActiveMQConnectionFactory", mappedName = "jms/ActiveMQConnectionFactory", type = ConnectionFactory.class)
	private ConnectionFactory connectionFactory;
	private CamelComponent camelComponent = null;
	@EJB
	TestServiceOne testServiceOne;
	@EJB
	TestServiceTwo testServiceTwo;
	
	public void startService() {
		startFunctionOne();
//		startFunctionTwo();
	}

	public void stopService() {
		try {
			camelComponent.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void startFunctionOne(){
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("testServiceOne", testServiceOne);
		registry.put("testServiceTwo", testServiceTwo);
		registry.put("testDynamicRouterBean", new TestDynamicRouterBean());
//		Class clsBean = TestDynamicRouterBean.class;
		 CamelComponentFactory camelComponentFactory = new CamelComponentFactory();
		try {
			camelComponent = camelComponentFactory.createCamelComponent(registry,connectionFactory);
//			camelComponent.setRoute("jms:test","bean:testServiceOne?method=hello");
			camelComponent.setRoute("jms:test", "bean:testDynamicRouterBean");
//			camelComponent.setRoute("jms:test", clsBean, "");//was8使用此方法null异常。glassfish正常。
			camelComponent.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void startFunctionTwo(){
		String user = CamelComponentFactory.DEFAULT_USER;
		String password = CamelComponentFactory.DEFAULT_PASSWORD;
		String url = CamelComponentFactory.DEFAULT_BROKER_URL;
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("testServiceOne", testServiceOne);
		registry.put("testServiceTwo", testServiceTwo);
		registry.put("testDynamicRouterBean", new TestDynamicRouterBean());
//		Class clsBean = TestDynamicRouterBean.class;
		CamelComponentFactory camelComponentFactory = new CamelComponentFactory(
				user, password, url);
		try {
			camelComponent = camelComponentFactory
					.createCamelComponent(registry);
//			camelComponent.setRoute("jms:test","bean:testServiceOne?method=hello");
			camelComponent.setRoute("jms:test", "bean:testDynamicRouterBean");
//			camelComponent.setRoute("jms:test", clsBean, "");//was8使用此方法null异常。glassfish正常。
			camelComponent.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}