package com.wcs.common.controller.permissions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.conf.Constants;
import com.wcs.base.util.BaseUtils;
import com.wcs.base.util.JSFUtils;
import com.wcs.common.model.Resource;
import com.wcs.common.model.Role;
import com.wcs.common.model.User;
import com.wcs.common.service.permissions.LoginService;
import com.wcs.common.service.permissions.ResourceService;
import com.wcs.common.service.permissions.UserService;
import com.wcs.common.util.MessageUtils;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: LoginBean.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2005-2011.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class LoginBean implements Serializable {
	final Logger logger = LoggerFactory.getLogger(LoginBean.class);

	@EJB
	private UserService userService;
	@Inject
	private ResourceBean resouceBean;
	@EJB
	private ResourceService resourceService;
	@EJB
	private LoginService loginService;

	private User user;
	private String userName; 												// 当前用户账号
	private List<Role> rolelist; 											// 用户角色集合
	private CopyOnWriteArrayList<Resource> allUserResouce; 				// 用户资源
	private ConcurrentHashMap<Resource, List<Resource>> leftMenuResouce; 	// 导航资源
	private List<Resource> topResource; 									// 一级菜单资源
	private Long selectId; 												// 选中的Top资源ID
	private boolean isManager; 											// 是否是管理员

	private Map<String, List<Resource>> compomentMap; // 页面组件Map

	private static final String LOGIN_SUCCESS = "/template/template.xhtml";

	public LoginBean() {
		this.rolelist = new ArrayList<Role>();
		this.allUserResouce = new CopyOnWriteArrayList<Resource>();
		this.leftMenuResouce = new ConcurrentHashMap<Resource, List<Resource>>();
		this.topResource = new ArrayList<Resource>();
		this.isManager = false;
	}

	/**
	 * 用户登录验证
	 * @return
	 */
	public String userLogin() {
		this.user = userService.findUniqueUser(this.userName);
		if (user == null) {
			MessageUtils.addErrorMessage("longmessgeId", "用户无效，请检查！");
			return Constants.FAILURED;
		}

		// 用户资源初始化
		try {
			initUserResource(user);
			if (allUserResouce.isEmpty()) {
				MessageUtils.addErrorMessage("longmessgeId", "用户没有权限，请检查！");
				return Constants.FAILURED;
			} else if (leftMenuResouce.isEmpty()) {
				MessageUtils.addErrorMessage("longmessgeId", "用户角色被冻结，请检查！");
				return Constants.FAILURED;
			}
		} catch (Exception e) {
			logger.info("用户资源初始化失败！");
			e.printStackTrace();
		}

		return Constants.SUCCESS;
	}
	
	/**
	 * 注销用户
	 */
	public String doLogout() {
		// 清除用户登录信息
		this.user = null;
		
		// 关闭Session
		HttpSession session = BaseUtils.getSession();
		session.invalidate();
		
		return Constants.SUCCESS;
	}

	/**
	 * 菜单初始化
	 * @param user
	 */
	private void initUserResource(User user) {
		// 初始化当前用户的所有Role
		try {
			if (this.rolelist == null) {
				this.rolelist = new ArrayList<Role>();
			}
			rolelist = this.userService.findAllRoleOfUser(user);
			
			// 得到用户资源
			allUserResouce.clear();
			allUserResouce.addAll(userService.findAllResouceOfRoleList(rolelist));
			if (allUserResouce.isEmpty()) {
				this.topResource.clear();
				this.leftMenuResouce.clear();
				
				return;
			}
			
			// 系统全部资源
			List<Resource> sysResouceList = resouceBean.getSysResouce();
			
			// 初始化top导航菜单
			topResource.clear();
			this.topResource.addAll(loginService.findTopResourceByUser(allUserResouce, sysResouceList));
			// 设置默认选中
			if (!topResource.isEmpty()) {
				this.selectId = topResource.get(0).getId();
				this.leftMenuResouce.clear();
				
				// 初始化左边导航
				loginService.intitLeftMenu(selectId, leftMenuResouce, allUserResouce, sysResouceList);
			}
		} catch (Exception e) {
			logger.info("角色初始化失败！");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <p>
	 * Description: 判断用户所有角色中是否有管理员角色
	 * </p>
	 * 
	 * @param rlist
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isAdmin(List<Role> rlist) {
		boolean flag = false;
		if (!rlist.isEmpty()) {
			for (Role r : rlist) {
				if (r.isAdmin()) {
					flag = true;
					return flag;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * <p>
	 * Description:改变导航菜单
	 * </p>
	 * 
	 * @param selectID
	 * @return
	 */
	public String changeMenu(Long selectID) {
		this.selectId = selectID;
		leftMenuResouce.clear();
		// intitLeftMenu(selectId);
		// 系统全部资源
		List<Resource> sysResouceList = resouceBean.getSysResouce();
		loginService.intitLeftMenu(selectId, leftMenuResouce, allUserResouce, sysResouceList);
		return LOGIN_SUCCESS;
	}

	/**
	 * 菜单资源Key封装
	 * 
	 * @return
	 */
	public List<Resource> getLeftMenuResouceKeys() {
		List<Resource> keyResources = new ArrayList<Resource>();
		if (!leftMenuResouce.isEmpty()) {
			for (Resource res : this.leftMenuResouce.keySet()) {
				keyResources.add(res);
			}
			// 菜单排序
			Collections.sort(keyResources);
		}
		return keyResources;
	}

	/**
	 * 
	 * <p>
	 * Description: 资源权限判断
	 * </p>
	 * 
	 * @param url
	 * @return
	 */
	public boolean contains(String id) {
		String uri = JSFUtils.getViewId();
		initResouce(uri);
		return estimate(id, uri);
	}

	/**
	 * 
	 * <p>
	 * Description: 资源权限判断 URL带参数
	 * </p>
	 * 
	 * @param id
	 * @param url
	 * @return
	 */
	public boolean contains(String url, String id) {
		initResouce(url);
		return estimate(id, url);
	}

	/**
	 * 得到当前请求资源的路径
	 * @return
	 */
	@SuppressWarnings("unused")
	private String resourceUrl() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		String url = viewRoot.getViewId();

		return url;
	}

	/**
	 * 
	 * <p>
	 * Description:是否有该资源判断
	 * </p>
	 * 
	 * @param id
	 * @param uri
	 * @return
	 */
	private boolean estimate(String id, String uri) {
		boolean flag = false;
		List<Resource> componentResource = this.compomentMap.get(uri);
		try {
			Resource currentResource = this.resourceService.findResourceByCompenet(getAllUserResouce(), id, uri);
			if (currentResource == null || componentResource == null) {
				return flag;
			} else {
				for (Resource resource : componentResource) {
					if (resource.getId().equals(currentResource.getId())) {
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	private void initResouce(String url) {
		List<Resource> componentResource = new ArrayList<Resource>();
		/*
		 * if(compomentMap.get(url) != null){ return; }
		 */
		compomentMap = new HashMap<String, List<Resource>>();
		List<Resource> allResource = getAllUserResouce();
		Resource menuResource;
		try {
			menuResource = this.resourceService.findResourceByUrl(allResource, url);
			List<Resource> compoentList = this.resourceService.findMenuComponentResouce(allResource, menuResource);
			List<Resource> userCompoentResource = this.resourceService.findUserCompoentResource(allResource, compoentList);
			componentResource.addAll(userCompoentResource);
			compomentMap.put(url, userCompoentResource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ----------------------------- set & get -------------------------------//

	/**
	 * @return the selectId
	 */
	public Long getSelectId() {
		return selectId;
	}

	/**
	 * @param selectId
	 *            the selectId to set
	 */
	public void setSelectId(Long selectId) {
		this.selectId = selectId;
	}

	/**
	 * @return the allUserResouce
	 */
	public List<Resource> getAllUserResouce() {
		return allUserResouce;
	}

	/**
	 * @param allUserResouce
	 *            the allUserResouce to set
	 */
	public void setAllUserResouce(CopyOnWriteArrayList<Resource> allUserResouce) {
		this.allUserResouce = allUserResouce;
	}

	/**
	 * @return the leftMenuResouce
	 */
	public Map<Resource, List<Resource>> getLeftMenuResouce() {
		return leftMenuResouce;
	}

	/**
	 * @param leftMenuResouce
	 *            the leftMenuResouce to set
	 */
	public void setLeftMenuResouce(ConcurrentHashMap<Resource, List<Resource>> leftMenuResouce) {
		this.leftMenuResouce = leftMenuResouce;
	}

	/**
	 * @return the rolelist
	 */
	public List<Role> getRolelist() {
		return rolelist;
	}

	/**
	 * @param rolelist
	 *            the rolelist to set
	 */
	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}

	/**
	 * @return the isManager
	 */
	public boolean isManager() {
		return isManager;
	}

	public boolean isQueryDeleted() {
		return false;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Resource> getTopResource() {
		return topResource;
	}

	public void setTopResource(List<Resource> topResource) {
		this.topResource = topResource;
	}

}
