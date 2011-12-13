package com.wcs.common.controller.permissions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.wcs.base.service.StatelessEntityService;
import org.primefaces.model.LazyDataModel;

import com.google.common.collect.Lists;
import com.wcs.base.conf.SystemConfiguration;
import com.wcs.base.util.JSFUtils;
import com.wcs.common.model.Role;
import com.wcs.common.model.User;
import com.wcs.common.model.UserRole;
import com.wcs.common.service.permissions.RoleService;
import com.wcs.common.service.permissions.UserService;
import com.wcs.common.util.MessageUtils;

/**
 * 
 * <p>Project: cmdpms</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chenlong@wcs-global.com">Chen Long</a>
 */
@Named
@ConversationScoped
public class UserBean extends PerimissionsConversationManager implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @Inject
    private UserService userService;
    @Inject
    private StatelessEntityService entityService;
    @Inject
    private RoleService roleService;
//    @Inject
//    private PurchaseOrgUtil purchaseOrgUtil;
    @Inject
    private LoginBean loginBean;

    /** 数据模型 */
    private LazyDataModel<User> lazyModel;
    /** 角色Id */
    private Long roleId;
    /** 角色下拉列表 */
    private List<SelectItem> rolelist = new ArrayList<SelectItem>();
    /** 账户 */
    private String account;
    /** 电子邮件地址 */
    private String email;
    /** 角色选中数组 */
    private Long[] urole;
    /** 用于查询账号字段 */
    private String userAccount;
    /** 查询条件Map封装 */
    private Map<String, Object> queryMap = new HashMap<String, Object>();
    /** 当前用户 */
    private User currentUser;

    /** 页面路径 */
    private static final String LIST_PAGE = "/faces/permissions/user/list.xhtml";
    private static final String USER_ROLE_PAGE = "/faces/permissions/user/user-role.xhtml";

    /*
     * 构造函数
     */
    public UserBean() {

    }

    @PostConstruct
    private void initLazyModel() {
        String sql = "from User";
        lazyModel = this.entityService.findPage(sql);
    }

    /**
     * 
     * <p>
     * Description: 角色分配保存
     * </p>
     * 
     * @return
     */
    public String saveUserRole() {
        try {

            List<Role> listrole = this.roleService.getSelectRoleById(urole);
            if (listrole != null) {
                this.roleService.deleteUserRole(this.currentUser.getId());
                for (Role role : listrole) {
                    // UserRole ur =
                    // this.roleService.findByURId(this.getInstance().getId(),
                    // role.getId());
                    UserRole userrole = new UserRole();
                    userrole.setUser(currentUser);
                    userrole.setRole(role);
                    this.entityService.create(userrole);
                }
                MessageUtils.addSuccessMessage("usermessgeId", "用户角色分配成功");
            }

        } catch (Exception e) {
            MessageUtils.addErrorMessage("usermessgeId", "用户角色分配失败");
            e.printStackTrace();
            return JSFUtils.getViewId();
        }
        return LIST_PAGE;
    }

    /**
     * 
     * <p>
     * Description: 账户查询
     * </p>
     */
    public String search() {

        StringBuilder sql = new StringBuilder("from User u where 1=1");
        sql.append(" /~ and u.name like  {name}~/ ");
        this.lazyModel = this.entityService.findPageByFilter(sql.toString(), this.queryMap);
        return LIST_PAGE;
    }

  

    /**
     * 
     * <p>
     * Description: 角色设置跳转
     * </p>
     * 
     * @return
     */
    public String roleSet() {
        this.urole = this.userService.getRoleIdByUser(this.currentUser);
        return USER_ROLE_PAGE;
    }

    /**
     * 
     * <p>
     * Description: 管理界面跳转
     * </p>
     * 
     * @return
     */
    public String goBack() {
        return LIST_PAGE;
    }

    /**
     * 
     * <p>
     * Description: 初始化界面一级下拉菜单
     * </p>
     */
    public void initSelect() {
        // 角色
        if (rolelist.size() == 0) {
            String sql = "select id,roleName from Role r where r.state =1";
            //this.rolelist = this.purchaseOrgUtil.findSqlWithSelectItem(sql);
        }

    }

    /**
     * 
     * <p>
     * Description: 输入匹配账号
     * </p>
     * 
     * @param queryStr
     * @return
     */
    public List<String> complete(String queryStr) {

        if (queryStr != null && !"".equals(queryStr)) {
            return this.userService.getUserAccountByInput(account);
        } else {
            return Lists.newArrayList();
        }

    }

    // -------------------------------------Set & Get---------------------------------------------------//
    
    /**
     * @return the roleId
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the rolelist
     */
    public List<SelectItem> getRolelist() {

        return rolelist;
    }

    /**
     * @param rolelist
     *            the rolelist to set
     */
    public void setRolelist(List<SelectItem> rolelist) {
        this.rolelist = rolelist;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the urole
     */
    public Long[] getUrole() {
        return urole;
    }

    /**
     * @param urole
     *            the urole to set
     */
    public void setUrole(Long[] urole) {
        this.urole = urole;
    }

    /**
     * @return the userAccount
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * @param userAccount
     *            the userAccount to set
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the lazyModel
     */
    public LazyDataModel<User> getLazyModel() {

        return lazyModel;
    }

    /**
     * @param lazyModel
     *            the lazyModel to set
     */
    public void setLazyModel(LazyDataModel<User> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public String getRowsPerPageTemplate() {
        return SystemConfiguration.ROWS_PER_PAGE_TEMPLATE;
    }
}
