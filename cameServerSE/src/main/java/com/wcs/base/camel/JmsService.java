package com.wcs.base.camel;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;

import org.apache.camel.impl.SimpleRegistry;

import com.wcs.came.CamelComponent;
import com.wcs.came.CamelComponentFactory;

//@Stateless
public class JmsService {
	@Resource(mappedName = "jms/ActiveMQConnectionFactory")
	private ConnectionFactory connectionFactory;
	private CamelComponent camelComponent = null;
//	@EJB
	TestServiceOne testServiceOne;
	
	public void startService() {
		testServiceOne = new TestServiceOne();
		
//		startFunctionOne();
		startFunctionTwo();
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
//		registry.put("testServiceTwo", testServiceTwo);
		registry.put("testDynamicRouterBean", new TestDynamicRouterBean());
//		Class clsBean = TestDynamicRouterBean.class;
		 CamelComponentFactory camelComponentFactory = new CamelComponentFactory();
		try {
//			String cfJNDI = "java:comp/env/jms/ActiveMQConnectionFactory";
//			InitialContext ic = new InitialContext();
//			connectionFactory= (ConnectionFactory) ic.lookup(cfJNDI);
			
			System.out.print("-----------------------"+connectionFactory);
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
		registry.put("testDynamicRouterBean", new TestDynamicRouterBean());
//		Class clsBean = TestDynamicRouterBean.class;
		CamelComponentFactory camelComponentFactory = new CamelComponentFactory(
				user, password, "failover://tcp://10.229.12.153:61616");
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