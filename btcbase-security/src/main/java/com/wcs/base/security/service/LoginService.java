/**
 * LoginService.java
 * Created: 2012-1-12 上午10:58:58
 */
package com.wcs.base.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.wcs.base.security.model.Resourcemstr;
import com.wcs.base.security.model.Rolemstr;
import com.wcs.base.security.model.Usermstr;

/**
 * <p>Project: btcbase</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chengchao@wcs-global.com">ChengChao</a>
 */
@Stateless
public class LoginService {
	
	private Logger logger = Logger.getLogger(LoginService.class.getName());
	
	@PersistenceContext
	private EntityManager em;
	public static final String SESSION_KEY_CURRENTUSR = "CURRENT_USER";
	public static final String SESSION_KEY_USER = "USER";
	public static final String SESSION_KEY_ROLES = "ROLES";
	public static final String SESSION_KEY_RESOURCES = "RESOURCES";
	public static final String SESSION_KEY_MENUS = "MENUS";
	
	/**
	 * <p>Description: 返回当前用户</p>
	 * @return
	 */
	public String getCurrentUserName() {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser == null ? null : currentUser.getPrincipal().toString();
	}
	
	/**
	 * <p>Description: 获得当前Usermstr</p>
	 * @return
	 */
	public Usermstr getCurrentUsermstr() {
		Subject currentUser = SecurityUtils.getSubject();
		Object obj = currentUser.getSession().getAttribute(SESSION_KEY_USER);
		return obj == null ? null : (Usermstr) obj;
	}
	/**
	 * <p>Description: 获得当前用户的rolemstr列表</p>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Rolemstr> getCurrentRoles() {
		Subject currentUser = SecurityUtils.getSubject();
		Object obj = currentUser.getSession().getAttribute(SESSION_KEY_ROLES);
		return obj == null ? null : (List<Rolemstr>) obj;
	}
	/**
	 * <p>Description: 获得当前用户的资源列表（菜单、功能）</p>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Resourcemstr> getCurrentResources() {
		Subject currentUser = SecurityUtils.getSubject();
		Object obj = currentUser.getSession().getAttribute(SESSION_KEY_RESOURCES);
		return obj == null ? null : (List<Resourcemstr>) obj;
	}
	
	/**
	 * <p>Description: 获得当前用户的菜单</p>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TreeNode> getCurrentMenus() {
		Subject currentUser = SecurityUtils.getSubject();
		Object obj = currentUser.getSession().getAttribute(SESSION_KEY_MENUS);
		return obj == null ? null : (List<TreeNode>) obj;
	}

	/**
	 * <p>Description: 设置当前用户session</p>
	 * @param key
	 * @param value
	 */
	private void setCurrentUserSession(String key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.getSession().setAttribute(key, value);
	}
	
	/**
	 * <p>Description: 判定用户名是否属于本系统合法用户，不是则返回false；是则返回true,并将用户信息、角色信息、资源信息添加到session中</p>
	 * @return
	 */
	public boolean isAppUser(String principal) {
		Usermstr usermstr = null;
		logger.log(Level.INFO, "judging isAppUser, find user from app...param:" + principal);
		if (principal == null || principal.trim().isEmpty()) {
			return false;
		}
		Query q = em.createQuery("select u from Usermstr u where u.defunctInd <> 'Y' and u.adAccount=:principal", Usermstr.class);
		q.setParameter("principal", principal);
		List<Usermstr> userList = q.getResultList();
		logger.log(Level.INFO, "judging isAppUser, find user from app...result:" + (userList.isEmpty() ? null : userList.get(0).getAdAccount()));
		if (!userList.isEmpty()) {
			usermstr = userList.get(0);
			setCurrentUserSession(SESSION_KEY_USER, usermstr);
			List<Rolemstr> roles = getRoles(principal);
			setCurrentUserSession(SESSION_KEY_ROLES, roles);
			logger.log(Level.INFO, principal + "'s roles:" + roles.size());
			List<Resourcemstr> resources = getResources(principal);
			setCurrentUserSession(SESSION_KEY_RESOURCES, resources);
			logger.log(Level.INFO, principal + "'s resources:" + resources.size());
			List<TreeNode> menuTree = getMenuTree(resources);
			setCurrentUserSession(SESSION_KEY_MENUS, menuTree);
			logger.log(Level.INFO, principal + "'s menuTree:" + menuTree);
		}
		return (userList.isEmpty()) ? false : true;
	}
	
	/**
	 * <p>Description: 获得用户的所有角色</p>
	 * @param principal 用户名
	 * @return
	 */
	private List<Rolemstr> getRoles(String principal){
		if (principal == null || principal.trim().isEmpty()) {
			return null;
		}
		Query q = em.createQuery("select ur.rolemstr from Userrole ur where ur.defunctInd <> 'Y' and ur.rolemstr.defunctInd <> 'Y' and ur.usermstr.adAccount=:principal", Usermstr.class);
		q.setParameter("principal", principal);
		return q.getResultList();
	} 
	
	/**
	 * <p>Description: 获得用户的所有资源（菜单、功能）</p>
	 * @param principal 用户名
	 * @return
	 */
	private List<Resourcemstr> getResources(String principal) {
		if (principal == null || principal.trim().isEmpty()) {
			return null;
		}
		String qs = "select DISTINCT rsr.resourcemstr" +
				" from Userrole ur,Roleresource rsr" +
				" where ur.defunctInd <> 'Y' and ur.rolemstr=rsr.rolemstr and ur.usermstr.adAccount=:principal" +
				" and rsr.defunctInd <> 'Y' and rsr.resourcemstr.defunctInd <> 'Y'" +
				" and rsr.rolemstr.defunctInd <> 'Y'" +
				" order by rsr.resourcemstr.seqNo asc";
		Query q = em.createQuery(qs, Resourcemstr.class);
		q.setParameter("principal", principal);
		return q.getResultList();
	}
	
	
	/**
	 * <p>Description: 构建菜单树（仅菜单，需排序）</p>
	 * @param resources 
	 * @return
	 */
	private List<TreeNode> getMenuTree(List<Resourcemstr> resources) {
	    // filter all resources which the type='MENU'
	    List<Resourcemstr> res = new ArrayList<Resourcemstr>();
	    for(Resourcemstr r : resources) {
	        if("MENU".equals(r.getType())) {
	            res.add(r);
	        }
	    }
	    
		//just mock temply
		if(res==null||res.isEmpty()){
			return null;
		}

		List<TreeNode> list = new ArrayList<TreeNode>();
		
		for(Resourcemstr r : res) {
		    if(r.getParentId() == 0) {    // first level
		        list.add(new DefaultTreeNode(r, null));
		    }
		}
		
		for(TreeNode tn : list) {
		    recursive((Resourcemstr) tn.getData(), tn, res);
		}
		return list;
	}
	/**
	 * <p>Description: recursive tree constructor</p>
	 * @param r
	 * @param tn
	 * @param rs
	 */
	public void recursive(Resourcemstr r, TreeNode tn, List<Resourcemstr> rs) {
        for(Resourcemstr rm : rs) {
            if(rm.getParentId() == r.getId()) {
                TreeNode n = new DefaultTreeNode(rm, tn);
                recursive(rm, n, rs);
            }
        }
    }
	
}

