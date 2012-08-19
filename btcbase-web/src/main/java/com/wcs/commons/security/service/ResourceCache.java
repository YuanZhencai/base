package com.wcs.commons.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.*;

import com.google.common.collect.Lists;
import com.wcs.base.exception.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.commons.security.model.Resource;
import com.wcs.base.service.EntityReader;
import com.wcs.base.service.EntityWriter;
import com.wcs.base.util.CollectionUtils;

/**
 * <p>Project: btcbase-web</p> 
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
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void initResourceCache() {
		String sql = "SELECT r FROM Resource r";
		cache = this.entityReader.findList(sql);
	}
	
	/**
	 * 装载系统的资源
	 */
	public List<Resource> loadAllResource() {
		return cache;
	}

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Resource> loadSubResources(Long parentId){
        List<Resource> list = Lists.newArrayList();
        for (Resource r : cache){
            if (parentId.equals(r.getParentId())){
                list.add(r);
            }
        }
        return list;
    }

	/**
	 * 删除当前选中资源
	 */
	public void deleteResource(Resource resource) throws TransactionException{
        // 若为父节点，则不能删除
        if ( CollectionUtils.isNotEmpty(loadSubResources(resource.getId())) ){
            throw new TransactionException("父节点不能删除");
        }

        // 删除节点
        String jpql = "DELETE FROM Resource r WHERE r.id=?1";
		this.entityWriter.batchExecute(jpql, resource.getId());

		// 从缓存中删除这个Resource
		for (Resource r: cache){
			if (r.getId().equals(resource.getId())){
				cache.remove(r);
				break;
			}
		}
	}

	/**
	 * 设置当前资源为父菜单（有子菜单：type=LEAF_MENU）
     * @param resource
     */
	public void updateResourceType(Resource resource) {
		String sql = "UPDATE Resource SET type= ?1 WHERE id= ?2";
		entityWriter.batchExecute(sql, resource.getType(), resource.getId());
		
		// TODO: 更新缓存中的这个Resource
	}

    public List<Resource> findBrotherNode(Resource resource){
        return entityReader.findList("SELECT r FROM Resource r WHERE r.parentId=?1 ",resource.getParentId());
    }
    /**
     * check 同级中的其他节点的seqNo，是否有与给定Resouce相同的
     * @param resource 原有节点
     * @return
     */
    public boolean isUniqueSeqNo(Resource resource){
        // 获取同级节点
        List<Resource> brotherResList = this.findBrotherNode(resource);
        // check 同级中是否有相同的seqNo
        for (Resource r : brotherResList){
            // 和原seqNo不相同，且此节点不是本原点
            if (r.getSeqNo().equals(resource.getSeqNo()) && !r.getId().equals(resource.getId())){
                //System.out.println("节点SeqNo不唯一");
                return false;
            }
        }
        return true;
    }

    public boolean isUniqueCode(Resource resource){
        for (Resource r : cache){
            if (r.getCode().equals(resource.getCode()) && !r.getId().equals(resource.getId())){
                return false;
            }
        }
        return true;
    }
}
