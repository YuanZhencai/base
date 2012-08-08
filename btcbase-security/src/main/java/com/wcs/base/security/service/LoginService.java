package com.wcs.base.security.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.security.model.Resource;
import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.RoleResource;
import com.wcs.base.security.model.User;
import com.wcs.base.service.StatelessEntityService;
import com.wcs.base.util.CollectionUtils;

/**
 * <p>Project: btcbase-security</p> 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author guanjianghuai
 */
@Stateless
public class LoginService implements Serializable {
    private static final long serialVersionUID = 1L;
    final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    private StatelessEntityService entityService;

    /**
     * Find Sys all resource
     * @return
     */
    public List<Resource> findAllSysResource() {
        String sql = "SELECT rs FROM Resource rs  ORDER BY rs.number";
        List<Resource> resList = this.entityService.findList(sql);
        return resList;
    }

    /**
     * Find user by loginName
     * @param loginName
     * @return
     */
    public User findUser(String loginName) {
        String jpql = "SELECT u FROM User u WHERE u.loginName=?1";
        List<User> userList = this.entityService.findList(jpql, loginName);
        if (CollectionUtils.isEmpty(userList)) { return null; }
        return userList.iterator().next();
    }

    /**
     * Find permission list by role
     * @param role
     * @return
     */
    public List<RoleResource> findPermissions(Role role) {
        String jpql = "SELECT p FROM RoleResource p WHERE p.role.id = ?1";
        List<RoleResource> permissions = entityService.findList(jpql, role.getId());
        return permissions;
    }
}
