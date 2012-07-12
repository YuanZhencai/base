package com.wcs.base.security.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.wcs.base.controller.ConversationBaseBean;
import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.User;
import com.wcs.base.security.service.UserService2;
import com.wcs.base.util.MessageUtils;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: UserBean.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright .All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */

@Named
@ConversationScoped
public class UserBean2 extends ConversationBaseBean<User> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(UserBean2.class);

    private Map<String, Object> queryMap = Maps.newHashMapWithExpectedSize(4);; // 查询条件Map封装
    private LazyDataModel<User> lazyModel;
    private DualListModel<Role> roles;

    @Inject
    private UserService2 userService;

    @PostConstruct
    public void initLazyModel() {
        searchUser();
        initRoles();
    }

    /**
     * 查询用户
     * @return
     */
    public void searchUser() {
        lazyModel = userService.findUsers(queryMap);
    }

    /**
     * 添加或者修改用户
     */
    public void saveUser() {
        try {
            this.save();
            MessageUtils.addErrorMessage("resMsg", "操作成功！");
        } catch (Exception e) {
            MessageUtils.addErrorMessage("resMsg", "操作失败，请检查！");
            logger.info("添加或者修改用户操作失败，原因:");
            e.printStackTrace();
        }
    }
    
    /**
     * Set current user roles
     * @return
     */
    public void saveCurrentUserRoles() {
        User currentUser = getInstance();
        // 删除当前用户角色
        userService.delUserRole(currentUser);

        // 新分配当前用户角色
        List<Role> roleList = roles.getTarget();
        userService.createUserRoles(currentUser, roleList);
    }
    
    /**
     * Init roles
     */
    private void initRoles() {
        findRoles();
    }

    /**
     * Find roles for dualList
     */
    public void findRoles() {
        List<Role> allRoles = userService.getRoles();
        List<Role> userRoles = getInstance().getRoleList();
        for (Role role : getInstance().getRoleList()) {
            allRoles.remove(role);
        }
        roles = new DualListModel<Role>(allRoles, userRoles);
    }

    /**
    * Clean instance
    */
    public void cleanInstance() {
        setInstance(new User());
    }

    public LazyDataModel<User> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<User> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Map<String, Object> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

    public DualListModel<Role> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<Role> roles) {
        this.roles = roles;
    }

    /**
     * 选中用户记录
     * @param event
     */
    /*
     * @SuppressWarnings("unused") public void onRowSelect(SelectEvent event) { User user = (User) event.getObject(); //
     * this.setInstance(user); }
     *//**
       * 初始化角色列表
       */
    /*
     * public void initRoleList() { if (this.roleList == null || this.roleList.size() > 0) { this.roleList = new
     * ArrayList<SelectItem>(); }
     * 
     * this.roleList = this.userService.initRoleList(); }
     *//**
       * 当前用户角色
       * @param user
       */
    /*
     * public void assignUserRole() { List<Role> allRoles = roleService.getRoleList(); List<Role> userRoles = new
     * ArrayList<Role>(); User user = null; // getInstance();
     * 
     * if (user != null) { userRoles = null; // userService.findRolesByUser(getInstance()); for (Role role : userRoles) {
     * allRoles.remove(role); } }
     * 
     * roles = new DualListModel<Role>(allRoles, userRoles); }
     *//**
       * 保存或者更新当前用户角色
       * @return
       */
    /*
     * @SuppressWarnings({ "rawtypes", "unused" }) public String saveUserRole() { User user = null; // getInstance(); try { //
     * roleService.deleteUserRole(user.getId()); List roleIdList = roles.getTarget();
     * 
     * for (int i = 0; i < roleIdList.size(); i++) { UserRole userrole = new UserRole(); Long roleId =
     * Long.valueOf(roleIdList.get(i).toString()); Role role = userService.findRoleById(roleId); if (role != null) {
     * userrole.setRole(role); } userrole.setUser(user); this.entityService.create(userrole); }
     * 
     * MessageUtils.addSuccessMessage("usermessgeId", "用户角色分配成功"); } catch (Exception e) {
     * MessageUtils.addErrorMessage("usermessgeId", "用户角色分配失败"); e.printStackTrace(); return JSFUtils.getViewId(); }
     * 
     * return LIST_PAGE; }
     *//**
       * 账户查询
       * @return
       */
    /*
     * public String search() { StringBuilder sql = new StringBuilder("from User u where 1=1");
     * sql.append(" /~ and u.name like  {name}~/ "); this.lazyModel = this.entityService.findXsqlPage(sql.toString(),
     * this.queryMap);
     * 
     * return LIST_PAGE; }
     *//**
       * 管理界面跳转
       * @return
       */
    /*
     * public String goBack() { return LIST_PAGE; }
     *//**
       *  输入匹配账号
       * @param queryStr
       * @return
       */
    /*
     * public List<String> complete(String queryStr) { if (queryStr != null && !"".equals(queryStr)) { return
     * this.userService.getUserAccountByInput(account); } else { return Lists.newArrayList(); }
     * 
     * }
     */

}
