package com.wcs.commons.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.wcs.base.service.EntityReader;
import com.wcs.commons.security.model.Resource;

/**
 * revision 19085 可以恢复到没有Tree
 * 
 * @author Chris Guan
 */
@Singleton
@Startup
@Lock(LockType.READ)
public class ResourceCache {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<Resource> cache = new ArrayList<Resource>();

	@EJB(beanName="EntityReader")
	private EntityReader entityReader;
	
	@PostConstruct
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void initResourceCache() {
		String sql = "SELECT r FROM Resource r";
		cache = this.entityReader.findList(sql);
	}
	
	/**
	 * 装载系统的资源
	 */
	public List<Resource> loadAllResources() {
		return cache;
	}
	
	public Resource loadResource(String code){
		for (Resource r : cache){
			if (r.getCode().equals(code)){
				return r;
			}
		}
		return null;
	}
	
 
	/**
	 * 得到某一个父Id parentId 下的资源列表
	 * @param parentId
	 * @return
	 */
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
