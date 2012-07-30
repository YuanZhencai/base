/** * Basket.java 
* Created on 2012-4-28 上午9:53:05 
*/

package com.base.arquilian;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/** 
* <p>Project: arquillian-tutorial</p> 
* <p>Title: Basket.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2012-2020.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yujingu@wcs-global.com">Yu JinGu</a> 
*/

@SessionScoped
public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> items;

    @EJB
    private OrderRepository repo;

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int getItemCount() {
        return items.size();
    }

    public void placeOrder() {
        repo.addOrder(items);
        items.clear();
    }

    @PostConstruct
    void initialize() {
        items = new ArrayList<String>();
    }
}
