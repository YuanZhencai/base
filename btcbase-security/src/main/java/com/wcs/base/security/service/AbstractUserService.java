package com.wcs.base.security.service;

import java.util.List;

import javax.ejb.EJB;

import com.wcs.base.security.model.Resource;
import com.wcs.base.security.model.Role;
import com.wcs.base.security.model.User;
import com.wcs.base.service.EntityReader;
import com.wcs.base.util.CollectionUtils;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: UserService.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright .All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
public abstract class AbstractUserService {
	
	@EJB(beanName="EntityReader")
	EntityReader entityReader;


	/**
	 * 通过用户id 查询唯一用户
	 */
	public User findUser(Long userId) {
		return entityReader.findUnique(User.class, userId);
	}
      
   /**
    * 通过用户名(adAccount)查询唯一用户
    */
    public User findUser(String adAccount) {
        String jpql = "SELECT u FROM User u WHERE u.adAccount = ?1";
        List<User> list = entityReader.findList(jpql, adAccount);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }
    
   /**
    * 根据用户id 查询所拥有的角色
    */
    public List<Role> findRoles(Long userId) {
        String jpql = "SELECT r FROM User u JOIN u.roleList r WHERE u.id= ?1";
        return entityReader.findList(jpql, userId);
    }
    
    /**
     * 根据用户adAccount 查询所拥有的角色
     */
     public List<Role> findRoles(String adAccount) {
         String jpql = "SELECT r FROM User u JOIN u.roleList r WHERE u.adAccount= ?1";
         return entityReader.findList(jpql, adAccount);
     }

   /**
    * 查询角色列表的所有资源
    */
	public List<Resource> findResources(List<Role> roleList) {
		String jpql = "select res from RoleResource rr join rr.resource res join rr.role role where role in (?1)";
		List<Resource> resourceList = entityReader.findList(jpql, roleList);
		return resourceList;
	}

}
