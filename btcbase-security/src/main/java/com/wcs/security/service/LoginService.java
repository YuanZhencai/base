package com.wcs.security.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.service.StatelessEntityService;
import com.wcs.base.util.CollectionUtils;
import com.wcs.security.model.Permission;
import com.wcs.security.model.Role;
import com.wcs.security.model.User;

@SuppressWarnings("serial")
@Stateless
public class LoginService implements Serializable{
    final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    private StatelessEntityService entityService;

    /**
     * find role by user ID
     * @param user
     * @return
     */
     public List<Role> findAllRoleOfUser(User user) {
         String jpql = "select r from UserRole ur join ur.user u join ur.role r where r.state=1 and u.id=" + user.getId();
         return entityService.findList(jpql);
     }
  
     /**
      * unique user by longName
      * @param loginName
      * @return
      */
    public User findUniqueUser(String loginName) {
        String sql = "SELECT u FROM User u WHERE u.loginName=?1";
        List<User> list  = this.entityService.findList(sql,loginName);
        if (!CollectionUtils.isEmpty(list)) {
            return list.iterator().next();
        }
        
        return  null;
    }
    
    /**
     * find permission by role
     * @param role
     * @return
     */
    public List<Permission> findAllPermissionByRole(Role role) {
        String jpql = "select p from Permission p where p.role.id=?1";
        return this.entityService.findList(jpql, role.getId());
    }
}
