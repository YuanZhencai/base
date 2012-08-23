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
	
	/**
	 * 为给定的role分配资源
	 * @param role  给定某一个角色
	 * @param allocatedResources 选选定的resource
	 */
	public void allocResources(Role role,List<Resource> allocatedResources){
		//原有为A，新的为B；
		// 得到原有的 List<Resource>

		// 1. delete A-B （删除被取消掉的 Resource）
		ListIterator<RoleResource> rrList = role.getRoleResources().listIterator();  // 已有的RoleResource
		while(rrList.hasNext()) {
			RoleResource rr = rrList.next();
			if (!allocatedResources.contains(rr.getResource())){
				rrList.remove();
			}
		}
		
		// 2. add B-A（添加新增的 Resource）
		String jpql = new String("SELECT res FROM RoleResource rr JOIN rr.role r JOIN rr.resource res WHERE r.id=?1");
		List<Resource> oldResList = entityReader.findList(jpql, role.getId());
		
		List<Resource> list = (List<Resource>)CollectionUtils.subtract(allocatedResources, oldResList);
		for (Resource r : list){
			RoleResource rr = new RoleResource();
			rr.setRole(role);
			rr.setResource(r);
			rr.setCode(r.getCode());
			role.getRoleResources().add(rr);
		}
		entityWriter.update(role);
	}

}
