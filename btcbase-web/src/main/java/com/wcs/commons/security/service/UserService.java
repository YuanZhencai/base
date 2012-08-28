package com.wcs.commons.security.service;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.wcs.base.ql.util.QueryUtils.*;
import com.wcs.base.exception.TransactionException;
import com.wcs.base.service.EntityWriter;
import com.wcs.base.service.PagingEntityReader;
import com.wcs.commons.security.model.User;
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
	 * CasUsr-->PU<--Person
	 * 查询用户 adAccount 在Person表中是否有对应的用户信息
	 * @param adAccount
	 */
	public List<Person> findPersons(String adAccount){
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT p FROM CasUsr u, Person p, PU pu ")
		.append(" WHERE p.defunctInd='N' AND u.defunctInd='N' ")
		.append(" AND u.id=pu.id AND p.id=pu.pernr ");
		if (StringUtils.isEmpty(adAccount)){
			jpql.append(" AND 1=0");
		} else {
			jpql.append(" AND u.id LIKE '%"+adAccount.trim()+"%'");
		}
		return this.entityReader.findList(jpql.toString());
	}
	
	/**
	 * 1.通过 pernr 在PU中查询出对应的 adAccount
	 * 2.检查 pernr，adAccount 联合确定的用户在 User 中是否已经存在
	 * 3.添加User
	 * @param pernr Person.id 即 User.pernr
	 * @return 被添加的User对象
	 */
	public User addUser(String pernr){
		// 通过给定的 pernr , 在PU中查询出 adAccount
		String adAccount = entityReader.findUnique("SELECT pu.id FROM PU pu WHERE pu.pernr=?1", pernr);
		User user = entityReader.findUnique("SELECT u FROM User u WHERE u.adAccount=?1 and u.pernr=?2");
		if (user != null){
			throw new TransactionException("用户（User）已经存在，请不要重复添加。");
		}
		return this.entityWriter.create( new User(adAccount,pernr) );
	}

}
