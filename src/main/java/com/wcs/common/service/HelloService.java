package com.wcs.common.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HelloService {

    @PersistenceContext(unitName = "pu")
    EntityManager entityManager;

	public String hello(String name){
		return "Hello,"+name;
	}
}
