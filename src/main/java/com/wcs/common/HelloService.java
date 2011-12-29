package com.wcs.common;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class HelloService {

    @PersistenceContext(unitName = "PBW")
    EntityManager em;

	public String hello(){
		System.out.println("HelloService => hello()");
		String name = "nooone";

//		List<Dict> list = em.createQuery("select d from Dict d").getResultList();
//		
//		if (list!=null && list.size()>0){
//			name = list.iterator().next().getName();
//		}
		System.out.println("HelloService => return");
		return "Hello,"+name;
	}
}
