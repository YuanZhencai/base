/**
 * ResourceBean.java
 * Created: 2011-7-26 下午08:36:54
 */
package com.wcs.base.security.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import com.wcs.base.exception.TransactionException;
import com.wcs.base.util.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.security.model.Resource;
import com.wcs.base.security.service.ResourceCache;
import com.wcs.base.service.EntityWriter;
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
    private Resource newResource = new Resource(); // 节点操作资源
    private Resource selectedResource; // 节点操作资源

    private OpMode opMode;

	@PostConstruct
	public void initResourceTree() {
        root = new DefaultTreeNode("root", null);
        this.buildTreeTable(resourceCache.loadSubResources(0L), root);
	}

    private void buildTreeTable(List<Resource> subResList,TreeNode parentNode){
        for (Resource r : subResList){
            TreeNode node = new DefaultTreeNode(r, parentNode);
            List<Resource> subList = resourceCache.loadSubResources(r.getId());
            //System.out.printf("buildTreeTable:: parentId=%d, subList.size=%d", r.getId(), subList.size());
            if (CollectionUtils.isNotEmpty(subList))
                buildTreeTable(subList,node);
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
            //MessageUtils.addSuccessMessage("resMsg", "修改资源成功！");
        } catch (Exception e) {
            e.printStackTrace();
            //MessageUtils.addErrorMessage("resMsg", "修改资源失败！");
        }finally {
            // 更新Resource Cache 和 Resource-Tree
            resourceCache.initResourceCache();
            this.initResourceTree();
        }
    }

    /**
     * 删除资源
     */
    @SuppressWarnings("null")
    public void delete() {
        try {
            this.resourceCache.deleteResource(selectedResource);
            MessageUtils.addSuccessMessage("resMsg", "删除资源成功！");
        } catch(TransactionException te){
            MessageUtils.addErrorMessage("resMsg", "删除资源失败, 请检查！"+te.getMessage());
        } catch(Exception e) {
            //e.printStackTrace();
            MessageUtils.addErrorMessage("resMsg", "删除资源失败, 请检查！");
        }

        // 更新页面数据(Resource-Tree)
        initResourceTree();
    }

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

    public Resource getNewResource() {
        return newResource;
    }

    public void setNewResource(Resource newResource) {
        this.newResource = newResource;
    }

    public OpMode getOpMode() {
        return opMode;
    }

    public void setOpMode(OpMode opMode) {
        this.opMode = opMode;
    }
}
