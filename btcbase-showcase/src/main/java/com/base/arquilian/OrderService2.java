/** * SingletonOrderRepository.java 
* Created on 2012-4-28 上午9:57:23 
*/

package com.base.arquilian;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* <p>Project: arquillian-tutorial</p> 
* <p>Title: SingletonOrderRepository.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

@Stateless
public class OrderService2  {
    @EJB
    OrderService orderService;
	
    public void hello() {
        
        orderService.hello();
        System.out.println("Hello World!2");
        
    }

}
