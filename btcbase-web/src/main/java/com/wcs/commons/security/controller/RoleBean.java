/**
 * RoleBean.java Created: 2011-7-8 上午11:04:11
 */
package com.wcs.commons.security.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.conf.SystemConfiguration;
import com.wcs.base.service.EntityReader;
import com.wcs.base.util.MessageUtils;
import com.wcs.commons.security.model.Resource;
import com.wcs.commons.security.model.Role;
import com.wcs.commons.security.service.ResourceCache;
import com.wcs.commons.security.service.RoleService;
import com.wcs.commons.security.vo.ResourcesNode;

/**
 * 
 * @author Chris Guan
 */
@ManagedBean
@ViewScoped
public class RoleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(RoleBean.class);

    public enum OpMode{
        ADD("新增"),EDIT("修改"),VIEW("查看");
        private final String displayText;
        OpMode(String displayText){ this.displayText = displayText; }
        @Override public String toString(){return displayText;}
    }

	@EJB(beanName="EntityReader")
	private EntityReader entityReader;
	@Inject
	private RoleService roleService;
	@Inject
	private ResourceCache resourceCache;
	
	private OpMode opMode;		// 操作模式
	private Role instance = new Role(); // 当前角色对象
	private List<Role> roles;// 数据模型

	private TreeNode root;// 资源树
	private TreeNode[] selectedNodes;// 节点数组

	private static final String LIST_PAGE = "/faces/permissions/role/list.xhtml";
	private static final String ROLE_RESOURCE_PAGE = "/faces/permissions/role/resource-role.xhtml";
	
	
	@PostConstruct @SuppressWarnings("unused")
	private void init() {
		this.list();
	}
	
	
	public void list() {
		this.roles = roleService.findAllRoles();
	}
	
	
	public void toAdd(){
		this.instance = new Role();
		this.opMode = OpMode.ADD;
	}
	

	public void add() {
		logger.info("创建Role");
		try {
			roleService.createRole(this.instance);
			roles.add(0, instance);	// 供页面table刷新之用
			MessageUtils.addSuccessMessage("msgMain", "角色添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.addErrorMessage("msgMain", "角色添加失败");
		}
	}
	
	
	public void edit() {
		try {
			roleService.updateRole(instance);
			MessageUtils.addSuccessMessage("msgMain", "角色更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.addErrorMessage("msgMain", "角色更新失败");
		}

	}	

	
	public void delete() {
		try {
			this.roleService.deleteRole(instance);
			roles.remove(instance);   // 供页面table刷新之用
			MessageUtils.addSuccessMessage("msgMain", "角色删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtils.addErrorMessage("msgMain", "删除失败");
		}
	}
	
	/**
	 * 资源分配页面跳转
	 * @return
	 */
	public String allocResources() {
		// this.roleVo.setRoleName(this.instance.getRoleName());
		root = new ResourcesNode("系统资源", null);
		// 若该角色已经分配过资源则查询已有的资源 并设置选中
		List<Resource> allResource = resourceCache.loadAllResource();
		try {
			//this.roleService.isSelectedResourceByRole(root, allResource, this.instance);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ROLE_RESOURCE_PAGE;
	}

	
	/**
	 * Description: 得到选中节点资源对象集合
	 * @param selectedNodes
	 * @return
	 */
	private List<Resource> loadSelectResource(TreeNode[] selectedNodes) {
		List<Resource> list = new ArrayList<Resource>();
		if (selectedNodes != null) {
			for (TreeNode node : selectedNodes ){
				Resource rs = entityReader.findUnique(Resource.class, ((ResourcesNode) node).getId());
				list.add(rs);
			}
		}

		return list;
	}
	
	//-------------------------------- setter & getter -----------------------------//
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public OpMode getOpMode() {
		return opMode;
	}

	public void setOpMode(OpMode opMode) {
		this.opMode = opMode;
	}

	public TreeNode getRoot() {
		return root;
	}
	
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public Role getInstance() {
		return instance;
	}

	public void setInstance(Role instance) {
		this.instance = instance;
	}

	public String getRowsPerPageTemplate() {
		return SystemConfiguration.ROWS_PER_PAGE_TEMPLATE;
	}
}
