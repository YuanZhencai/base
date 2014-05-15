/** * DBConnection.java 
* Created on 2014年5月15日 下午3:51:55 
*/

package com.wcs.demo.goc.singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: DBConnection.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class DBConnection {
	
	private EntityManager em;
	
	private DBConnection() {
	}
	
	private  static class SingletonFactory {
		private static DBConnection instance = new DBConnection();
	}
	
	public static DBConnection getInstance() {
		return SingletonFactory.instance;
	}
	
	public EntityManager getEntityManager(String nuitName) {
		if(em == null) {
			synchronized (DBConnection.class) {
				if (em == null) {
					EntityManagerFactory emf = Persistence.createEntityManagerFactory(nuitName);
					em = emf.createEntityManager();
				}
			}
		}
		return em;
	}
	
}
