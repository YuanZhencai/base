package com.wcs.commons.security.service;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.wcs.base.service.EntityReader;
import com.wcs.base.service.EntityWriter;
import com.wcs.base.util.CollectionUtils;
import com.wcs.commons.security.model.Resource;
import com.wcs.commons.security.model.Role;
import com.wcs.commons.security.model.RoleResource;
import com.wcs.commons.security.model.User;

/**
 * 
 * @author Chris Guan
 */
@Stateless
public class RoleService implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB(beanName="EntityReader")
	private EntityReader entityReader;
	
	@EJB
	private EntityWriter entityWriter;
	
	public List<Role> findAllRoles() {
	    return entityReader.findList("select r from Role r WHERE r.defunctInd='N'");
	}

	/**
	 * 得到用户的角色列表
	 */
	public List<Role> findAllRoles(User user) {
		user = entityReader.findUnique(User.class, user.getId());  //得到持久化的user
		return user.getRoleList();
	}
	
	public void updateRole(Role role)  {
		entityWriter.update(role);
	}

	public void deleteRole(Role role){
		role.setDefunctInd('Y');
		entityWriter.update(role);
	}

	public void activateRole(Role role){
		role.setDefunctInd('Y');
		entityWriter.update(role);
	}
	
	public void createRole(Role role){
		entityWriter.create(role);
	}
	


}
