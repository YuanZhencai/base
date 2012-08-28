package com.wcs.commons.security.service;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.service.EntityWriter;
import com.wcs.base.service.PagingEntityReader;
import com.wcs.commons.security.model.User;
import com.wcs.commons.security.model.master.CasUsr;
import com.wcs.commons.security.model.master.Person;

/**
 * 
 * @author Chris Guan
 */
@Stateless
public class UserService extends AbstractUserService {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@EJB
	PagingEntityReader entityReader;   
	@EJB
	EntityWriter entityWriter;
    /**
     * 通过用户属性查询用户
     * @param filterMap 存放用户属性的过滤条件
     * @return 返回用户的分页数据
     */
	public LazyDataModel<User> findUsers(Map<String, Object> filterMap) {
		StringBuilder xql = new StringBuilder();
		xql.append("SELECT new User(u.id,u.adAccount,p) FROM User u, Person p ")
		.append(" WHERE p.id=u.pernr AND u.defunctInd='N' AND p.defunctInd='N' ")
		.append("/~ and u.adAccount LIKE {adAccount} ~/")
		.append("/~ and p.nachn LIKE {nachn} ~/")
		.append("/~ and p.telno LIKE {telno} ~/")
		.append(" order by u.adAccount");
		logger.debug("UserService=>findUsers(): "+xql.toString());
	    return entityReader.findXqlPage(xql.toString(), filterMap);
	}
	
	/**
	 * 删除User实体
	 * @param user
	 */
	public void deleteUser(User user){
		this.entityWriter.batchExecute("UPDATE User u SET u.defunctInd='Y' WHERE u.id=?1", user.getId());
		//this.entityWriter.batchExecute("DELETE FROM PU pu WHERE pu.id=?1", user.getAdAccount());
	}
	
	/**
	 * 通过给定的 adAccount 查询 Cas(TDS) 用户。查询方式为 LIKE
	 */
	public List<CasUsr> findCasUsrs(String adAccount){
		return this.entityReader.findList("SELECT cu FROM CasUsr cu WHERE cu.id=?1", adAccount);
	}
	
	/**
	 * CasUsr-->PU<--Person
	 * 查询用户 adAccount 在Person表中是否有对应的用户信息
	 * @param adAccount
	 * @return
	 */
	public Person findPerson(String adAccount){
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT p FROM CasUsr u, Person p, PU pu ")
		.append(" WHERE p.defunctInd='N' AND u.defunctInd='N' ")
		.append(" AND u.id=pu.id AND p.id=pu.pernr AND cu.id LIKE ?1");
		return this.entityReader.findUnique(jpql.toString(), "%"+adAccount+"%");
	}
	
	public User addUser(String adAccount, String pernr){
		return this.entityWriter.create( new User(adAccount,pernr) );
	}

}
