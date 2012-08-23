/**
 * ResourceBean.java
 * Created: 2011-7-26 下午08:36:54
 */
package com.wcs.commons.security.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.wcs.base.exception.TransactionException;
import com.wcs.base.service.EntityWriter;
import com.wcs.base.util.CollectionUtils;
import com.wcs.base.util.MessageUtils;
import com.wcs.commons.security.model.Resource;
import com.wcs.commons.security.model.Role;
import com.wcs.commons.security.model.RoleResource;
import com.wcs.commons.security.service.ResourceCache;
import com.wcs.commons.security.vo.ResourcesNode;

/**
 * 
 * @author Chris Guan
 */
@ManagedBean
@ViewScoped
public class ResourceBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public enum OpMode{
        ADD("新增"),EDIT("修改"),VIEW("查看");
        private final String displayText;
        OpMode(String displayText){ this.displayText = displayText; }
        @Override public String toString(){return displayText;}
    }

	@EJB
	private ResourceCache resourceCache;
	@EJB
	private EntityWriter entityWriter;

    private TreeNode root = null; // 资源树
	private TreeNode selectedNode; // 选中节点
	private TreeNode[] selectedNodes; // checkbox
    //private Resource newResource = new Resource(); // 节点操作资源
    private Resource selectedResource; // 节点操作资源

    private OpMode opMode;

	@PostConstruct
	public void initResourceTree() {
		logger.info("初始化资源树 Tree");
        root = new DefaultTreeNode("root", null);
        this.buildTree(resourceCache.loadSubResources(0L), root);
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
    
    public void setAllocatedResources(Role role){
    	List<Resource> resList = resourceCache.loadResource(role);
    	for (Resource res : resList){
    		setSelectedNode(res,root);
    	}
    	//return "/faces/auth/role-resource.xhtml";
    }
	/**
	 * 资源分配页面跳转
	 * @return
	 */
	public void allocResources(Role role) {
		List<Resource> selectedResource = Lists.newArrayList();
		for(TreeNode node : selectedNodes) {
			selectedResource.add((Resource)node.getData());
		}
		
		resourceCache.allocResources(role,selectedResource);
		
		//return "/faces/auth/role-list.xhtml";
	}
	
    private void setSelectedNode(Resource res,TreeNode parentNode){
    	Object obj = parentNode.getData();
    	if (obj instanceof Resource && res.equals((Resource)obj)){
    		parentNode.setSelected(true);
    	}
    	 List<TreeNode> children = parentNode.getChildren();
    	if (CollectionUtils.isNotEmpty(children)){
    		for (TreeNode node : children){
    			setSelectedNode(res,node);
    		}
    	}
    }

    public void toAdd(){
        Long parentId = selectedResource.getId();
        this.selectedResource = new Resource();
        this.selectedResource.setParentId(parentId);
        this.opMode = OpMode.ADD;
    }
    /**
     * 添加子资源
     */
    public void add() {
        // code 唯一判断 (暂不做)

        // seqNo 唯一判断 (暂不做)

        try {
            this.entityWriter.create(selectedResource);
            MessageUtils.addSuccessMessage("resMsg", "添加资源成功！");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtils.addErrorMessage("resMsg", "添加资源失败，请检查！");
        }

        // 更新页面数据
        resourceCache.initResourceCache();
        this.initResourceTree();
    }

    /**
     * 修改资源
     */
    public void edit() {
        try {
            // code 唯一判断
            if (!resourceCache.isUniqueCode(selectedResource)){
                MessageUtils.addSuccessMessage("resMsg", "code 不唯一！");
                return;
            }
            // seqNo 唯一判断
            if (!resourceCache.isUniqueSeqNo(selectedResource)){
                MessageUtils.addSuccessMessage("resMsg", "seqNo 不唯一！");
                return;
            }
            // 修改当前资源

            entityWriter.update(selectedResource);
            MessageUtils.addSuccessMessage("resMsg", "修改资源成功！");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtils.addErrorMessage("resMsg", "修改资源失败！");
        }finally {
            // 更新Resource Cache 和 Resource-Tree
            resourceCache.initResourceCache();
            this.initResourceTree();
        }
    }

    /**
     * 删除资源
     */
    public void delete() {
        try {
            this.resourceCache.deleteResource(selectedResource);
            MessageUtils.addSuccessMessage("resMsg", "删除资源成功！");
        } catch(TransactionException te){
            MessageUtils.addErrorMessage("resMsg", "删除资源失败, 请检查！"+te.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            MessageUtils.addErrorMessage("resMsg", "删除资源失败, 请检查！");
        }

        // 更新页面数据(Resource-Tree)
        initResourceTree();
    }

    /**
     * 初始化 SelectOneMenu 组件
     */
    public Resource.ResourceType[] getResourceTypeValues() {
        return Resource.ResourceType.values();
    }

    //-------------------------- setter & getter -----------------------//
    public TreeNode getRoot() {
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

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public OpMode getOpMode() {
        return opMode;
    }

    public void setOpMode(OpMode opMode) {
        this.opMode = opMode;
    }
}
