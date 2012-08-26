package com.wcs.commons.security.service;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.primefaces.model.LazyDataModel;

import com.wcs.base.service.EntityWriter;
import com.wcs.base.service.PagingEntityReader;
import com.wcs.base.util.CollectionUtils;
import com.wcs.commons.security.model.Role;
import com.wcs.commons.security.model.User;

/**
 * 
 * @author Chris Guan
 */
@Stateless
public class UserService extends AbstractUserService {

	@EJB
	PagingEntityReader entityReader;   
	
    /**
     * 通过用户属性查询用户
     * @param filterMap 存放用户属性的过滤条件
     * @return 返回用户的分页数据
     */
	public LazyDataModel<User> findUsers(Map<String, Object> filterMap) {
		StringBuilder xql = new StringBuilder();
		xql.append("SELECT u FROM User u WHERE u.defunctInd = false")
		.append("/~ and u.adAccount LIKE {adAccount} ~/");
	    return entityReader.findXqlPage(xql.toString(), filterMap);
	}
	
}
