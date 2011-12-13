/**
 * RoleBean.java Created: 2011-7-8 上午11:04:11
 */
package com.wcs.common.controller.permissions;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.wcs.base.conf.SystemConfiguration;
import com.wcs.base.service.StatelessEntityService;
import com.wcs.base.util.JSFUtils;
import com.wcs.base.util.ResourcesNode;
import com.wcs.common.controller.permissions.vo.RoleVO;
import com.wcs.common.model.Resource;
import com.wcs.common.model.Role;
import com.wcs.common.model.RoleResource;
import com.wcs.common.service.permissions.ResourceService;
import com.wcs.common.service.permissions.RoleService;
import com.wcs.common.util.MessageUtils;

/**
 * 
 * <p>Project: cmdpms</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yourname@wcs-global.com">Your Name</a>
 */
@Named
@ConversationScoped
public class RoleBean extends PerimissionsConversationManager implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Inject
    private StatelessEntityService entityService;
    @Inject
    private RoleService roleService;
    @Inject
    private ResourceService resourceService;
    @Inject
    private ResourceBean resourceBean;

    /** 资源树 */
    private TreeNode root;
    /** 节点数组 */
    private TreeNode[] selectedNodes;
    /** 数据模型 */
    private LazyDataModel<Role> lazyModel;
    /** 角色名字 */
    private String roName;
    /** 查询条件Map封装 */
    private Map<String, Object> queryMap = new HashMap<String, Object>();
    /** FormVo */
    private RoleVO roleVo = new RoleVO();
    /** 当前角色对象 */
    private Role currentRole = new Role();

    private static final String LIST_PAGE = "/faces/permissions/role/list.xhtml";
    private static final String ROLE_RESOURCE_PAGE = "/faces/permissions/role/resource-role.xhtml";
    final Logger logger = LoggerFactory.getLogger(RoleBean.class);

    /**
     * 构造函数
     */
    public RoleBean() {
        /*
         * lazyModel = new LazyDataModel<Role>() {
         * 
         * @Override public int getRowCount() {
         * 
         * return rolesrvice.getRowCount();
         * 
         * }
         * 
         * @Override public List<Role> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String, String>
         * filters) {
         * 
         * return rolesrvice.getResultByPage(first, pageSize, sortField, sortOrder, filters); }
         * 
         * };
         */
    }

    @PostConstruct
    private void initLazyModel() {
        String sql = "from Role role  ";
        this.lazyModel = this.entityService.findPage(sql);
    }

    /**
     * 
     * <p>
     * Description: 保存角色
     * </p>
     * 
     * @return
     */
    public void saveRole() {
        try {
            this.currentRole.setAdmin(false);
            this.entityService.create(this.currentRole);
            MessageUtils.addSuccessMessage("rolemessgeId", "角色添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtils.addErrorMessage("rolemessgeId", "角色添加失败");
        }

    }

    /**
     * 
     * <p>
     * Description: 角色资源保存
     * </p>
     * 
     * @return
     */
    public String saveRoleResource() {
        try {
            int flag = Integer.parseInt((JSFUtils.getRequestParam("flag")).toString());
            Role role = null;
            if (flag == 1) {
                role = this.currentRole;
            } else {
                role = this.roleService.getRoleByName(roName);
            }
            // 删掉角色已有的资源
            List<Resource> roleResourceList = this.resourceService.findResouceByRole(role);
            if (!roleResourceList.isEmpty()) {
                this.resourceService.deleteRoleResource(role);
            }
            // 得到选中的资源
            List<Resource> listresouce = this.resourceService.getSelectResource(selectedNodes);
            if (listresouce != null && listresouce.size() != 0) {
                for (Resource r : listresouce) {
                    RoleResource rs = new RoleResource();
                    rs.setResource(r);
                    rs.setRole(role);
                    this.entityService.create(rs);
                }
            }
            MessageUtils.addSuccessMessage("rolemessgeId", "角色资源分配成功");
            return LIST_PAGE;
        } catch (Exception e) {
            MessageUtils.addErrorMessage("rolemessgeId", "角色资源分配失败");
            e.printStackTrace();
            return JSFUtils.getViewId();
        }

    }

    /**
     * 
     * <p>
     * Description: 角色编辑初始化
     * </p>
     */
    public void editInit() {

        roleVo.setRoleName(this.currentRole.getRoleName());
        roleVo.setState(this.currentRole.getState());
        roleVo.setDescription(this.currentRole.getDescription());
    }

    /**
     * 
     * <p>
     * Description:角色编辑
     * </p>
     * 
     * @return
     */
    public void update() {
        try {
            this.currentRole.setRoleName(this.roleVo.getRoleName());
            this.currentRole.setDescription(this.roleVo.getDescription());
            this.currentRole.setState(this.roleVo.getState());
            this.entityService.update(currentRole);
            MessageUtils.addSuccessMessage("rolemessgeId", "角色更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtils.addErrorMessage("rolemessgeId", "角色更新失败");
        }

    }

    /**
     * 
     * <p>
     * Description: 角色查询方法
     * </p>
     */
    public String search() {
        StringBuilder sql = new StringBuilder("from Role role where 1=1 ");
        sql.append(" /~ and role.roleName like {roleName}~/ ");
        this.lazyModel = this.entityService.findPageByFilter(sql.toString(), this.queryMap);
        return LIST_PAGE;

    }

    /**
     * 
     * <p>
     * Description: 节点选中监听
     * </p>
     * 
     * @param eve
     */
    public void onTreeNodeClicked(NodeSelectEvent eve) {
        System.out.println("onTreeNodeClicked >>>>> into");
    }

    /**
     * 
     * <p>
     * Description: 角色名输入匹配
     * </p>
     * 
     * @param queryStr
     * @return
     */
    public List<String> complete(String queryStr) {
        if (queryStr != null && !"".equals(queryStr)) {
            return this.roleService.searchRole(roName);
        } else {
            return Lists.newArrayList("无配置项");
        }

    }

    /**
     * 
     * <p>
     * Description: 角色删除
     * </p>
     */
    public void deleteRole() {
        try {
            
            logger.debug("当前角色  --->> " + this.currentRole.getId());
            this.roleService.deleteRole(currentRole);
            MessageUtils.addSuccessMessage("rolemessgeId", "角色删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtils.addErrorMessage("rolemessgeId", "删除失败");
        }
    }

    public void clear() {
        this.roleVo = new RoleVO();
        this.currentRole = new Role();
    }

    /**
     * 
     * <p>
     * Description: 角色管理页面跳转
     * </p>
     * 
     * @return
     */
    public String roleList() {
        return LIST_PAGE;
    }

    /**
     * 
     * <p>
     * Description: 资源分配页面跳转
     * </p>
     * 
     * @return
     */
    public String resourceJump() {
        this.roleVo.setRoleName(this.currentRole.getRoleName());
        root = new ResourcesNode("系统资源", null);
        // 若该角色已经分配过资源则查询已有的资源 并设置选中
        try {
            this.roleService.isSelectedResourceByRole(root, resourceBean.getSysResouce(), this.currentRole);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ROLE_RESOURCE_PAGE;
    }

    public String goBack() {

        return LIST_PAGE;
    }

    /**
     * 
     * <p>
     * Description: 资源分配页面跳转 直接通过角色名查询角色对象之后
     * </p>
     * 
     * @return
     */
    public String rescourceJump() {

        return "/faces/permissions/role/resourceRoleInput.xhtml";
    }

    /**
     * @return the root
     */
    public TreeNode getRoot() {

        return root;
    }

    /**
     * @param root
     *            the root to set
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * @return the selectedNodes
     */
    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    /**
     * @param selectedNodes
     *            the selectedNodes to set
     */
    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    /**
     * @return the lazyModel
     */
    public LazyDataModel<Role> getLazyModel() {
        return lazyModel;
    }

    /**
     * @param lazyModel
     *            the lazyModel to set
     */
    public void setLazyModel(LazyDataModel<Role> lazyModel) {
        this.lazyModel = lazyModel;
    }

    /**
     * @return the roName
     */
    public String getRoName() {
        return roName;
    }

    /**
     * @param roName
     *            the roName to set
     */
    public void setRoName(String roName) {
        this.roName = roName;
    }

    /**
     * @return the queryMap
     */
    public Map<String, Object> getQueryMap() {
        return queryMap;
    }

    /**
     * @param queryMap
     *            the queryMap to set
     */
    public void setQueryMap(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

    /**
     * @return the roleVo
     */
    public RoleVO getRoleVo() {
        return roleVo;
    }

    /**
     * @param roleVo
     *            the roleVo to set
     */
    public void setRoleVo(RoleVO roleVo) {
        this.roleVo = roleVo;
    }

    public Role getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Role currentRole) {
        this.currentRole = currentRole;
    }

    public String getRowsPerPageTemplate() {
        return SystemConfiguration.ROWS_PER_PAGE_TEMPLATE;
    }
}
