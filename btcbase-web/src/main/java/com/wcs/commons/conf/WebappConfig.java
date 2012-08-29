package com.wcs.commons.conf;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.wcs.base.collections.DefaultTreeNode;
import com.wcs.base.collections.TreeNode;
import com.wcs.base.util.CollectionUtils;
import com.wcs.commons.security.model.Resource;
import com.wcs.commons.security.service.ResourceCache;

/**
 * 
 * @author Chris Guan
 *
 */
@ManagedBean(name="config", eager=true)
@ApplicationScoped
public class WebappConfig {

	private Map<String, Object> appMap;
	
	private TreeNode root = null;
	
	@EJB
	ResourceCache resourceCache;
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void init(){
		initResTree();
	}

	private void initResTree() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		this.appMap = ec.getApplicationMap();
		
		root = new DefaultTreeNode("root", null);
		this.buildTree(resourceCache.loadSubResources(0L), root);
		
		appMap.put("resTree", root);
	}
	
	/**
	 * 用来维护Resource，采用递归方式实现
	 * @param subResList 给定的父节点的儿子资源列表
	 * @param parentNode 父节点
	 */
    private void buildTree(List<Resource> subResList,TreeNode parentNode){
        for (Resource r : subResList){
            TreeNode node = new DefaultTreeNode(r, parentNode);
            List<Resource> subList = resourceCache.loadSubResources(r.getId());
            //System.out.printf("buildTreeTable:: parentId=%d, subList.size=%d", r.getId(), subList.size());
            if (CollectionUtils.isNotEmpty(subList))
                buildTree(subList,node);
        }
    }
    
	public Map<String, Object> getAppMap() {
		return appMap;
	}
	
}
