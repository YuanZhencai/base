/** * SingletonOrderRepository.java 
* Created on 2012-4-28 上午9:57:23 
*/

package com.base.arquilian;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** 
* <p>Project: arquillian-tutorial</p> 
* <p>Title: SingletonOrderRepository.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

public abstract class AbstractOrderService   {
	@PersistenceContext(unitName = "pu")
	EntityManager em;
	
    public void hello(){};

}
