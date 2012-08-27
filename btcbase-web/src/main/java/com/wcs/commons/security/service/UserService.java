package com.wcs.commons.security.service;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.service.PagingEntityReader;
import com.wcs.commons.security.vo.UserVO;

/**
 * 
 * @author Chris Guan
 */
@Stateless
public class UserService extends AbstractUserService {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@EJB
	PagingEntityReader entityReader;   
	
    /**
     * 通过用户属性查询用户
     * @param filterMap 存放用户属性的过滤条件
     * @return 返回用户的分页数据
     */
	public LazyDataModel<UserVO> findUsers(Map<String, Object> filterMap) {
		StringBuilder xql = new StringBuilder();
		xql.append("SELECT new UserVO(u,p) FROM User u, Person p, PU pu ")
		.append(" WHERE p.id=pu.pernr and u.adAccount=pu.id and p.defunctInd='N' ")
		.append("/~ and u.adAccount LIKE {adAccount} ~/")
		.append("/~ and p.nachn LIKE {nachn} ~/")
		.append("/~ and p.telno LIKE {telno} ~/")
		.append(" order by u.adAccount");
		logger.debug("UserService=>findUsers(): "+xql.toString());
	    return entityReader.findXqlPage(xql.toString(), filterMap);
	}
	
}
