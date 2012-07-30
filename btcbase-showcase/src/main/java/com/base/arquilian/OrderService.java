/** * SingletonOrderRepository.java 
* Created on 2012-4-28 上午9:57:23 
*/

package com.base.arquilian;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.service.StatelessEntityService;

/** 
* <p>Project: arquillian-tutorial</p> 
* <p>Title: SingletonOrderRepository.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

@Stateless
public class OrderService extends AbstractOrderService  {
	@PersistenceContext(unitName = "pu")
	EntityManager em;
	
	@Override
    public void hello() {
        System.out.println("Hello World!");
        
    }

}
