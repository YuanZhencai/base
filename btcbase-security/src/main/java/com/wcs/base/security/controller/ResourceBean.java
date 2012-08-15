/**
 * ResourceBean.java
 * Created: 2011-7-26 下午08:36:54
 */
package com.wcs.base.security.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.controller.ConversationBaseBean;
import com.wcs.base.security.model.Resource;
import com.wcs.base.security.model.Resource.ResourceType;
import com.wcs.base.security.service.ResourceCache;
import com.wcs.base.service.EntityWriter;
import com.wcs.base.util.JSFUtils;
import com.wcs.base.util.MessageUtils;

/**
 * <p>Project: btcbase-security</p> 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author guanjianghuai
 */

@ManagedBean
@ViewScoped
public class ResourceBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(ResourceBean.class);

	@EJB
	private ResourceCache resourceCache;
	@EJB
	private EntityWriter entityWriter;

    private TreeNode root = new DefaultTreeNode("root", null); // 资源树

    private Resource selectedResource; // 节点操作资源
	private TreeNode selectedNode; // 选中节点
	List<Resource> resList; // 所有菜单资源

    public ResourceBean(){
        System.out.println("-------------ResourceBean");
    }
	@PostConstruct
	public void initResource() {
        System.out.println("-------------initResource");
        this.buildTreeTable(resourceCache.loadSubResources(0L), root);
	}

    private void buildTreeTable(List<Resource> resList,TreeNode parentNode){
        System.out.println("-------------buildTreeTable");

        for (Resource r : resList){
            System.out.println(r.getCode());
            TreeNode node = new DefaultTreeNode(r, parentNode);
            List<Resource> subResList = resourceCache.loadSubResources(r.getParentId());
            buildTreeTable(subResList,node);
        }
    }


    //-------------------------- setter & getter -----------------------//
    public TreeNode getRoot() {
        System.out.println("-------------getRoot");
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public Resource getSelectedResource() {
        return selectedResource;
    }

    public void setSelectedResource(Resource selectedResource) {
        this.selectedResource = selectedResource;
    }
    // --------------------------- 以下未整理 ----------------------------//
	/**
	 * 根据条件查询资源
	 */
	public void searchResource() {
		log.info("Search Resource start.");
		String searchResourceName = JSFUtils.getRequestParam("resName");
	}

	/**
	 * 清除
	 */
	public void clean() {
		this.selectedResource = null;
		// this.setInstance(null);

		// 刷新
		refresh();
	}

	/**
	 * 添加资源
	 */
	public void addResource() {
		// 判断上级菜单是否为空
		if (selectedResource == null) {
			MessageUtils.addSuccessMessage("resMsg", "上级菜单不能为空，请选择上级菜单！");
			return;
		}

		// 判断选择菜单是否为叶子节点，是则更新为包节点
		if ("MENU".equals(selectedResource.getType()) 
				&& StringUtils.isNotEmpty(selectedResource.getUri())) {
			//this.selectedResource.setIsLeaf(false);
			resourceCache.updateCurrentResource(this.selectedResource);
		}

//		// 判断关键字是否唯一
//		String keyName = JSFUtils.getRequestParam("keyName");
//		Boolean isUnique = this.resourceService.judgeKeyNameUnique(keyName);
//		if (!isUnique) {
//			MessageUtils.addSuccessMessage("resMsg", "关键字已经存在,请从新输入！");
//			return;
//		}

		// 构建新建资源
		buildNewResource(selectedResource);
		try {
			// this.save();
			MessageUtils.addSuccessMessage("resMsg", "添加资源成功！");
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.addErrorMessage("resMsg", "添加资源失败，请检查！");
		}

		// 更新页面数据
		refresh();
	}

	/**
	 * 构建新建资源
	 * @param selectedResource
	 */
	private void buildNewResource(Resource selectedResource) {
		// 上级菜单ID
		// getInstance().setParentId(selectedResource.getId());

		// 菜单级别
		//int level = selectedResource.getLevel() + 1;
		// getInstance().setLevel(level);

		// 设置默认isLeaf
		// getInstance().setIsLeaf(true);
	}

	/**
	 * 删除资源
	 */
	@SuppressWarnings("null")
	public void deleteResource() {
		Resource resource = null; // getInstance();
		if (resource.getId() < 7) {
			MessageUtils.addSuccessMessage("resMsg", "初始保护资源不能删除,请选择序号大于6的资源！");
			return;
		}
		try {
			this.resourceCache.deleteResource(resource);
			MessageUtils.addSuccessMessage("resMsg", "删除资源成功！");
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.addErrorMessage("resMsg", "删除资源失败, 请检查！");
		}

		// 更新页面数据
		refresh();
	}

	@SuppressWarnings("unused")
	public void onRowSelect(SelectEvent event) {
		if (this.selectedResource == null) {
			this.selectedResource = null; // new Resource();
		}

		// 设置上级菜单
		Resource resource = (Resource) event.getObject();
		// this.setInstance(resource);
		// this.selectedResource.setName(getInstance().getParentName());
		// this.selectedResource.setId(getInstance().getParentId());
	}

	/**
	 * 修改资源
	 */
	@SuppressWarnings("null")
	public void modResource() {
		Resource resource = null; // getInstance();
		if (resource.getId() < 7) {
			MessageUtils.addSuccessMessage("resMsg", "初始保护资源不能修改,请选择序号大于6的资源！");
			return;
		}

		// 判断上级菜单是否为空
		if (selectedResource == null) {
			MessageUtils.addSuccessMessage("resMsg", "上级菜单不能为空，请选择上级菜单！");
			return;
		}

//		// 判断关键字是否唯一
//		String keyName = JSFUtils.getRequestParam("keyName");
//		Boolean isUnique = this.resourceService.judgeKeyNameUnique(keyName);
//		if (!isUnique) {
//			// 判断keyName对象是否为同一资源
//			Resource resByKeyName = resourceService.findResourceByKeyName(keyName);
//			if (resByKeyName.getId().longValue() != resource.getId().longValue()) {
//				MessageUtils.addSuccessMessage("resMsg", "关键字已经存在,请从新输入！");
//				return;
//			}
//		}

		// 更新上级菜单不是叶子节点
		if ("MENU".equals(selectedResource.getType()) 
				&& StringUtils.isNotEmpty(selectedResource.getUri())) {
			//this.selectedResource.setIsLeaf(false);
			resourceCache.updateCurrentResource(this.selectedResource);
		}

		// 修改当前资源
		try {
			Long parentId = resource.getParentId();
			Long selectedParentId = selectedResource.getId();
			if (selectedParentId != parentId) {
				resource.setParentId(selectedParentId);
			}
			entityWriter.update(resource);

			// 检查旧的上级菜单是否应该为叶子节点
			Boolean checkIsLeaf = resourceCache.checkCurrentResIsLeaf(parentId);
			if (checkIsLeaf) {
				Resource res = null; // new Resource();
				res.setId(parentId);
				res.setType(ResourceType.LEAF_MENU);
				resourceCache.updateCurrentResource(res);
			}

			MessageUtils.addSuccessMessage("resMsg", "修改资源成功！");
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.addErrorMessage("resMsg", "修改资源失败！");
		}

		// 更新页面数据
		refresh();
	}

	/**
	 * 树节点点击事件
	 */
	public void selectedNodeResource() {
		if (selectedNode != null) {
			this.selectedResource = (Resource) this.selectedNode.getData();
		}
	}

	/**
	 * 初始化树资源
	 */
	private void initResourceTree() {
		// 查找所有资源
		if (this.resList == null) {
			this.resList = new ArrayList<Resource>();
		}
		this.resList = resourceCache.loadAllResource();

		// 构建资源树
		createResourceTree();
	}

	/**
	 * 构建资源树
	 */
	private void createResourceTree() {
		root = new DefaultTreeNode("Root", null);
		for (int i = 0; i < resList.size(); i++) {
			Resource resource = resList.get(i);
			if (resource.getParentId() == 0) {
				TreeNode node = new DefaultTreeNode(resource, root);
				if ( ResourceType.LEAF_MENU.equals(resource.getType()) ) {
					findChildResource(resource, node);
				}
			}
		}
	}

	/**
	 * 查找子菜单资源 
	 * @param resource
	 * @param node
	 */
	private void findChildResource(Resource resource, TreeNode node) {
		// 得到当前菜单的子菜单
		Long currentId = resource.getId();
		for (Resource r : resList) {
			if (r.getParentId() == currentId) {
				TreeNode childNode = new DefaultTreeNode(r, node);
				if (! ResourceType.LEAF_MENU.equals(r.getType()) ) {
					findChildResource(r, childNode);
				}
			}
		}
	}

	/**
	 * 刷新
	 */
	private void refresh() {
		initResourceTree();
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}
}
