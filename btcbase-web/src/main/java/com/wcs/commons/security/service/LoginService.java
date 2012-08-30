package com.wcs.commons.security.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.commons.security.model.RoleResource;
import com.wcs.base.service.EntityReader;

/**
 * 
 * @author Chris Guan
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class LoginService extends AbstractUserService {
    final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @EJB(beanName="EntityReader")
    private EntityReader entityReader;
     
    /**
     * 查找某一 Role 的所有可访问的 Resource
     */
    public List<RoleResource> findPermissions(Long roleId) {
        String jpql = "SELECT p FROM RoleResource p WHERE p.role.id = ?1";
        List<RoleResource> permissions = entityReader.findList(jpql, roleId);
        return permissions;
    }
    
//    /**
//     * 通过 adAccount 查询该用户的授权资源列表
//     */
//    public List<String> findPermissions(String adAccount) {
//        String jpql = "SELECT rr.uri FROM User u join u.roleList r join r.roleResources rr WHERE u.adAccount=?1";
//        List<String> permissionCodeList = entityReader.findList(jpql, adAccount);
//        return permissionCodeList;
//    }
}