package com.wcs.base.security.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

import com.wcs.base.security.model.Resource;
import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.RoleResource;
import com.wcs.base.service.EntityReader;
import com.wcs.base.service.EntityWriter;
import com.wcs.base.util.ResourcesNode;

/**
 * <p>Project: btcbase-security</p> 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author guanjianghuai
 */
@Stateless
public class ResourceService implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB(beanName="EntityReader")
	private EntityReader entityReader;
	
	@EJB
	private EntityWriter entityWriter;

	/**
	 * Description: 得到选中节点资源对象集合
	 * @param selectedNodes
	 * @return
	 */
	public List<Resource> getSelectResource(TreeNode[] selectedNodes) {
		List<Resource> list = new ArrayList<Resource>();
		if (selectedNodes != null) {
			int size = selectedNodes.length;
			if (size != 0) {
				for (int i = 0; i < size; i++) {
					ResourcesNode node = (ResourcesNode) selectedNodes[i];
					Resource rs = this.entityReader.findUnique(Resource.class, node.getId());
					list.add(rs);
				}
				return list;
			} else {
				return list;
			}
		} else {
			return list;
		}
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
	 * Description: 查询角色的资源
	 */
	public List<RoleResource> findResouceByRole(Role role) throws Exception {
		List<RoleResource> permissionList = this.getResourceListByRole(role);
		
		return permissionList;
	}

	/**
	 * 查找所有资源
	 * @return
	 */
	@SuppressWarnings("unused")
	public LazyDataModel<Resource> searchAllResource() {
		String sql = "SELECT r FROM Resource r WHERE r.id > 1";
		Query query = this.entityReader.createQuery(sql);
		List<?> queryList = query.getResultList();
		List<Resource> rsList = getResourceList(queryList);

		// 转换成LazyModel
		// LazyDataModel<Resource> lazyMode =
		// LazyModelUtil.getLazyResourceDataModel(rsList);

		return null; // lazyMode;
	}

	/**
	 * 根据条件查询资源
	 * @param searchResourceName
	 * @return
	 */
	@SuppressWarnings("unused")
	public LazyDataModel<Resource> searchResourceByName(String searchResourceName) {
		List<Resource> rsList = findResourceByName(searchResourceName);

		// 转换成LazyModel
		// LazyDataModel<Resource> lazyMode =
		// LazyModelUtil.getLazyResourceDataModel(rsList);

		return null; // lazyMode;
	}

	/**
	 * 根据资源名查找资源列表
	 * @param searchResourceName
	 * @return
	 */
	private List<Resource> findResourceByName(String searchResourceName) {
		String sql = "SELECT r FROM Resource r WHERE r.id > 1 and r.name LIKE :name";
		Query query = this.entityReader.createQuery(sql);
		query.setParameter("name", "%" + searchResourceName + "%");
		List<?> queryList = query.getResultList();

		return getResourceList(queryList);
	}

	/**
	 * 无参查找资源列表
	 * @return
	 */
	public List<Resource> findAllResource() {
		String sql = "SELECT r FROM Resource r";
		List<Resource> queryList = this.entityReader.findList(sql);

		return getResourceList(queryList);
	}

	private List<Resource> getResourceList(List<?> queryList) {
		List<Resource> resourceList = new ArrayList<Resource>();
		for (int i = 0; i < queryList.size(); i++) {
			Resource resource = (Resource) queryList.get(i);

			/*
			 * // 是否有子菜单 if (resource.getIsLeaf()) {
			 * resource.setIsLeafLang("无"); } else {
			 * resource.setIsLeafLang("有"); }
			 * 
			 * // 是否为菜单 if (resource.getMenu()) { resource.setIsMenuLang("是"); }
			 * else { resource.setIsMenuLang("否"); }
			 * 
			 * // 上级菜单名 String parentName = this.findParentName(resource); if
			 * (parentName != null) { resource.setParentName(parentName); }
			 */
			resourceList.add(resource);
		}

		return resourceList;
	}

	/**
	 * 根据parentId查找上级菜单名
	 * @param resource
	 * @return
	 */
	@SuppressWarnings("unused")
	private String findParentName(Resource resource) {
		String sql = "SELECT r FROM Resource r WHERE r.id = :id";
		Query query = this.entityReader.createQuery(sql);
		query.setParameter("id", resource.getParentId());
		List<?> resourceList = query.getResultList();
		if (resourceList.isEmpty()) {
			return null;
		}

		// 得到上级菜单名
		resource = (Resource) resourceList.get(0);
		String parentName = resource.getName();

		return parentName;
	}

	/**
	 * 删除角色授权
	 * @param role
	 */
	public void deleteRolePermission(Role role) {
		String sql = "DELETE FROM Permission p WHERE p.roleid = ?1";
		this.entityWriter.batchExecute(sql, role.getId());
	}

	/**
	 * 删除当前选中资源
	 * @param currentResource
	 */
	public void deleteResource(Resource res) {
		String sql = "DELETE FROM Resource r WHERE r.id=?1";
		this.entityWriter.batchExecute(sql, res.getId());
	}

	/**
	 * 修改当前选中资源
	 * @param currentResource
	 */
	public void modResource(Resource res) {
		this.entityWriter.update(res);
	}

	/**
	 * 更新当前资源有子菜单:isLeaf:0
	 * @param selectedResource
	 */
	public void updateCurrentResource(Resource selectedResource) {
		String sql = "Update Resource SET IS_LEAF= :IsLeaf WHERE id= :id";
		Query query = this.entityReader.createQuery(sql);
		if ("MENU".equals(selectedResource.getType()) 
				&& StringUtils.isNotEmpty(selectedResource.getUri())) {
			query.setParameter("IsLeaf", 1);
		} else {
			query.setParameter("IsLeaf", 0);
		}
		query.setParameter("id", selectedResource.getId());
		query.executeUpdate();
	}

	/**
	 * 判断关键字是否唯一
	 * @param keyName
	 * @return
	 */
	public Boolean judgeKeyNameUnique(String keyName) {
		String sql = "SELECT COUNT(*) FROM Resource r WHERE r.keyName = :keyName";
		Query query = this.entityReader.createQuery(sql);
		query.setParameter("keyName", keyName);
		Long count = (Long) query.getSingleResult();
		if (count > 0) {
			return false;
		}

		return true;
	}

	/**
	 * 检查更新前资源上级菜单是否应该为叶子节点
	 * @param parentId
	 * @return
	 */
	public Boolean checkCurrentResIsLeaf(Long parentId) {
		String sql = "SELECT COUNT(*) FROM Resource r WHERE r.parentId = :parentId";
		Query query = this.entityReader.createQuery(sql);
		query.setParameter("parentId", parentId);
		Long count = (Long) query.getSingleResult();
		if (count > 0) {
			return false;
		}

		return true;
	}

	/**
	 * 判断keyName对象是否为同一资源
	 * @param keyName
	 * @return
	 */
	public Resource findResourceByKeyName(String keyName) {
		String sql = "SELECT r FROM Resource r WHERE r.keyName = :keyName";
		Query query = entityReader.createQuery(sql);
		query.setParameter("keyName", keyName);
		Resource resource = (Resource) query.getSingleResult();

		return resource;
	}
}
