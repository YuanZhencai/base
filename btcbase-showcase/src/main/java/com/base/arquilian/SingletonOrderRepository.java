/** * SingletonOrderRepository.java 
* Created on 2012-4-28 上午9:57:23 
*/

package com.base.arquilian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

/** 
* <p>Project: arquillian-tutorial</p> 
* <p>Title: SingletonOrderRepository.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

@Singleton
@Lock(LockType.READ)
public class SingletonOrderRepository implements OrderRepository {
    private List<List<String>> orders;

    @Override
    @Lock(LockType.WRITE)
    public void addOrder(List<String> order) { 
        orders.add(order);
    }

    @Override
    public List<List<String>> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    @Override
    public int getOrderCount() {
        return orders.size();
    }

    @PostConstruct
    void initialize() {
        orders = new ArrayList<List<String>>();
    }
}
