package com.wcs.base.security.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.security.model.Resource;
import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.RoleResource;
import com.wcs.base.security.model.User;
import com.wcs.base.service.EntityReader;
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
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class LoginService implements Serializable {
    private static final long serialVersionUID = 1L;
    final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @EJB(beanName="EntityReader")
    private EntityReader entityReader;

    /**
     * 查找系统所有资源
     * @return
     */
    public List<Resource> findAllSysResource() {
        String sql = "SELECT r FROM Resource r ORDER BY r.code";
        List<Resource> resList = this.entityReader.findList(sql);
        return resList;
    }

    /**
     * 通过用户的登录名来查找用户
     * @param adAccount
     * @return
     */
    public User findUser(String adAccount) {
        String jpql = "SELECT u FROM User u WHERE u.adAccount=?1";
        List<User> userList = this.entityReader.findList(jpql, adAccount);
        if (CollectionUtils.isEmpty(userList)) { return null; }
        return userList.get(0);
    }

    /**
     * 查找某一 Role 的所有可访问的 Resource
     * @param role
     * @return
     */
    public List<RoleResource> findPermissions(Role role) {
        String jpql = "SELECT p FROM RoleResource p WHERE p.role.id = ?1";
        List<RoleResource> permissions = entityReader.findList(jpql, role.getId());
        return permissions;
    }
}
