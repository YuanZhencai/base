package com.wcs.base.camel;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;

import com.wcs.came.CamelComponent;
import com.wcs.came.CamelComponentFactory;



/**
 * 
 * <p>Project: ncp</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yourname@wcs-global.com">Yan Song</a>
 */
@ManagedBean(name = "testSendMessage") 
@RequestScoped

public class TestSendMessage   implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	//Test[Asynchronous]/[Synchronization-PARA]
	public void sendMessageOne(ActionEvent actionEvent) {
	    String user = CamelComponentFactory.DEFAULT_USER;
	    String password = CamelComponentFactory.DEFAULT_PASSWORD;
	    String url = CamelComponentFactory.DEFAULT_BROKER_URL;
	    
		CamelComponentFactory camelComponentFactory = new CamelComponentFactory(user, password, "failover://tcp://10.229.15.125:61616");
		CamelComponent camelComponent;
		try {
			camelComponent = camelComponentFactory.createCamelComponent();

		
			//test
			TestBean testBean = new TestBean();
			testBean.setName(name + "********************1");
			System.out.println("********************sendMessageBean:"+name+"********************");
	
			//TODO Asynchronous
//			camelComponent.sendAsyncRequestBodyAndHeader("jms:test?requestTimeout=2000000", testBean, "service", "bean:testServiceOne?method=hello");
			
			//Synchronization
			ProducerTemplate producerTemplate= camelComponent.getProducerTemplate();
			List a = (List)producerTemplate.sendBodyAndHeader("jms:test",ExchangePattern.InOut, testBean, "service", "bean:testServiceOne?method=hello");
			a.get(0);
			System.out.println("********************test-return:"+a.get(0)+"********************");
			System.out.println("********************test-finish********************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//TestJPA
	public void sendMessageTwo(ActionEvent actionEvent) {
	    String user = CamelComponentFactory.DEFAULT_USER;
	    String password = CamelComponentFactory.DEFAULT_PASSWORD;
	    String url = CamelComponentFactory.DEFAULT_BROKER_URL;
	    
		CamelComponentFactory camelComponentFactory = new CamelComponentFactory(user, password, "failover://tcp://10.229.15.125:61616");
		CamelComponent camelComponent;
		try {
			camelComponent = camelComponentFactory.createCamelComponent();
			
			String strBean="bean:testServiceTwo?method=testDatasourceService";
			System.out.println("********************sendMessageTestJPA:"+strBean+"********************");
			camelComponent.sendBodyAndHeader("jms:test", null, "service", strBean);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}

