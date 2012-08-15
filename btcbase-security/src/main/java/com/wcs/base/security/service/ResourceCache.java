package com.wcs.base.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.Query;

import com.google.common.collect.Lists;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.security.model.Resource;
import com.wcs.base.security.model.Resource.ResourceType;
import com.wcs.base.service.EntityReader;
import com.wcs.base.service.EntityWriter;
import com.wcs.base.util.CollectionUtils;
import com.wcs.base.util.ResourcesNode;

/**
 * <p>Project: btcbase-security</p> 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author guanjianghuai
 */
@Singleton
@Startup
@Lock(LockType.READ)
public class ResourceCache {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<Resource> cache = new ArrayList<Resource>();

	@EJB(beanName="EntityReader")
	private EntityReader entityReader;
	
	@EJB
	private EntityWriter entityWriter;
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void findAllResources() {
		String sql = "SELECT r FROM Resource r";
		cache = this.entityReader.findList(sql);
	}
	
	/**
	 * 装载系统的资源
	 */
	public List<Resource> loadAllResource() {
		return cache;
	}

    public List<Resource> loadSubResources(Long parentId){
        List<Resource> list = Lists.newArrayList();
        for (Resource r : cache){
            if (parentId.equals(r.getParentId())){
                list.add(r);
            }
        }
        return null;
    }
	
	public void clear() {
		if (CollectionUtils.isNotEmpty(cache))
			cache.clear();
	}

	/**
	 * 删除当前选中资源
	 */
	public void deleteResource(Resource res) {
		String sql = "DELETE FROM Resource r WHERE r.id=?1";
		this.entityWriter.batchExecute(sql, res.getId());
		
		// 从缓存中删除这个Resource
		for (Resource r: cache){
			if (r.getId().equals(res.getId())){
				cache.remove(r);
				break;
			}
		}
	}

	/**
	 * 设置当前资源为父菜单（有子菜单：type=LEAF_MENU）
	 */
	public void updateCurrentResource(Resource selectedResource) {
		String sql = "Update Resource SET type= ?1 WHERE id= ?2";
		entityWriter.batchExecute(sql, selectedResource.getType(), selectedResource.getId());
		
		// TODO: 更新缓存中的这个Resource
	}

	/**
	 * 检查资源上级菜单是否应该为叶子节点
	 */
	public Boolean checkCurrentResIsLeaf(Long parentId) {
		for (Resource res : cache){
			if (res.getParentId().equals(parentId)){
				return true;
			}
		}

		return false;
	}

}
