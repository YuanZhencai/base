package com.wcs.showcase.test;

import javax.ejb.Local;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wcs.base.security.model.master.PersonMstr;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: UserRepositoryBean.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright .All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@Local(UserRepository.class)
@Named(value = "userRepositoryBean")
public class UserRepositoryBean implements UserRepository {
	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	@Override
	public String searchUser() {
		PersonMstr user = new PersonMstr();
		em.persist(user);

		return "yes";
	}

}
