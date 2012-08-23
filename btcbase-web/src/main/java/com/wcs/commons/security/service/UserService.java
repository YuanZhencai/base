package com.wcs.commons.security.service;

import java.util.List;
import java.util.ListIterator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.primefaces.model.LazyDataModel;

import com.wcs.commons.security.model.Role;
import com.wcs.commons.security.model.User;
import com.wcs.base.service.EntityWriter;
import com.wcs.base.service.PagingEntityReader;
import com.wcs.base.util.CollectionUtils;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: UserService.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright .All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@Stateless
public class UserService extends AbstractUserService {

	@EJB
	PagingEntityReader entityReader;

	@EJB
	EntityWriter entityWriter;
   
    
    /**
     * 根据用户得到当前用户角色
     * @param user
     * @return
     */
    public List<Role> findRolesByUser(User user) {
        String sql = "select r from UserRole ur join ur.user u join ur.role r where r.state=1 and u.id=" + user.getId();
        return entityReader.findList(sql);
    }

    /**
     * 根据角色ID找到角色
     * @return
     */
    public Role findRoleById(Long roleId) {
        String sql = "SELECT r FROM Role r WHERE r.id = " + roleId;
        Query query = entityReader.createQuery(sql);
        Role role = (Role) query.getSingleResult();
        
        return role;
    }

    /**
     * Find users by loginName from queryMap
     */
	public LazyDataModel<User> findUsers(String adAccount) {
		String jpql = "SELECT u FROM User u WHERE u.defunctInd = false and u.adAccount LIKE ?1";
	    return entityReader.findPage(jpql, "%"+adAccount+"%");
	}
	
	/**
     * Find all role
     * @return
     */
    public List<Role> getRoles() {
        String jpql = "SELECT r FROM Role r ORDER BY r.name ASC";
        List<Role> allRoles = entityReader.findList(jpql);
        return allRoles;
    }
    
     /**
     * Delete user roles by userId
     */
    public void delUserRole(User user) {
        Query q = entityReader.createQuery("DELETE FROM UserRole ur where  ur.userid = :userId");
        q.setParameter("userId", user.getId());
        q.executeUpdate();
    }

    /**
     * Set current user roles
     * @param roleList
     */
    public void allocRoles(User user, List<Role> roleList) {
    	User u = entityReader.findUnique(User.class, user.getId());  // 得到持久化的User
    	List<Role> userRoles = u.getRoleList();
    	
    	// 删除未被选中的Role
    	ListIterator<Role> roles = userRoles.listIterator();
		while(roles.hasNext()) {
			Role r = roles.next();
    		if (!roleList.contains(r)){
    			userRoles.remove(r);
    		}
		}
    	
    	// 剔除已有的角色
    	List<Role> appendedRoles = (List<Role>) CollectionUtils.subtract(roleList, userRoles);
    	// 给User添加新的Role（这个appendedRoles是非持久化的，保存（更新）可能会出错）
    	userRoles.addAll(appendedRoles);
    	
    	entityWriter.update(u);
    }
}
