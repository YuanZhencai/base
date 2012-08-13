package com.wcs.base.security.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.Query;

import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.RoleResource;
import com.wcs.base.service.EntityReader;
import com.wcs.base.service.EntityWriter;

@Singleton
@Startup
@Lock(LockType.READ)
public class PermissionCache {
	@EJB(beanName="EntityReader")
	private EntityReader entityReader;
	
	@EJB
	private EntityWriter entityWriter;	
	
	/**
	 * Description: 查询角色的资源
	 */
	public List<RoleResource> findResouceByRole(Role role) throws Exception {
		List<RoleResource> permissionList = this.getResourceListByRole(role);
		
		return permissionList;
	}
	
	/**
	 * Description: 查询角色的资源中间表
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RoleResource> getResourceListByRole(Role role) {
		try {
			String sql = "SELECT p FROM RoleResource p WHERE p.role.id = ?1 ";
			Query query = entityReader.createQuery(sql, role.getId());
			List<RoleResource> permissionList = query.getResultList();
			return permissionList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除角色授权
	 * @param role
	 */
	public void deleteRolePermission(Role role) {
		String sql = "DELETE FROM Permission p WHERE p.roleid = ?1";
		this.entityWriter.batchExecute(sql, role.getId());
	}	
}
