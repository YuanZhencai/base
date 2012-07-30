/** * OrderRepository.java 
* Created on 2012-4-28 上午9:50:55 
*/

package com.base.arquilian;

import java.util.List;

import javax.ejb.Local;

/** 
* <p>Project: arquillian-tutorial</p> 
* <p>Title: OrderRepository.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

@Local
public interface OrderRepository {
    void addOrder(List<String> order);
    List<List<String>> getOrders();
    int getOrderCount();
}
