
package com.wcs.base.security.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.TreeNode;

import com.wcs.base.security.model.Resourcemstr;
import com.wcs.base.security.model.Rolemstr;
import com.wcs.base.security.model.Usermstr;
import com.wcs.base.security.service.LoginService;

/**
 * <p>
 * Project: btcbase
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright (c) 2012 Wilmar Consultancy Services
 * </p>
 * <p>
 * All Rights Reserved.
 * </p>
 * 
 * @author <a href="mailto:chengchao@wcs-global.com">ChengChao</a>
 */
@ManagedBean(name = "currentUser")
@SessionScoped
public class CurrentUserBean implements Serializable {

	private static final long serialVersionUID = 9157970199871052634L;

	@EJB
	private LoginService loginService;

	public String getCurrentUserName() {
		return loginService.getCurrentUserName();
	}

	public Usermstr getCurrentUsermstr() {
		return loginService.getCurrentUsermstr();
	}

	public List<Rolemstr> getCurrentRoles() {
		return loginService.getCurrentRoles();
	}

	public List<Resourcemstr> getCurrentResources() {
		return loginService.getCurrentResources();
	}

	public List<TreeNode> getCurrentMenus() {
		return loginService.getCurrentMenus();
	}

	private static final String LOGIN_SUCCESS = "/template/template.xhtml";

	// 当前用户的菜单树（1~3级）
	private List<TreeNode> menuTree;

	@PostConstruct
	void init() {
		menuTree = getCurrentMenus();
		topNavs = menuTree;
	}

	// 1级
	private List<TreeNode> topNavs;
	//
	private Long selectedTopNavId;
	// 2级
	private List<TreeNode> midNavs;

	public String refreshMidNavs(Long topNavId) {
		selectedTopNavId = null;
		midNavs = new ArrayList<TreeNode>();
		if (topNavId == null) {
			return LOGIN_SUCCESS;
		}
		if (menuTree == null || menuTree.isEmpty()) {
			return LOGIN_SUCCESS;
		}
		for (TreeNode menu : menuTree) {
			if (topNavId.equals(((Resourcemstr) (menu.getData())).getId())) {
				midNavs = menu.getChildren();
				selectedTopNavId = topNavId;
				for (TreeNode tn : midNavs) {
					List<TreeNode> threeMenu = tn.getChildren();
					for (TreeNode tm : threeMenu) {
						Resourcemstr r = (Resourcemstr) tm.getData();
						if (r.getUri() != null && !"".equals(r.getUri().trim())) {
							return r.getUri();
						}
					}
				}
			}
		}
		return LOGIN_SUCCESS;
	}

	public List<TreeNode> getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(List<TreeNode> menuTree) {
		this.menuTree = menuTree;
	}

	public List<TreeNode> getTopNavs() {
		return topNavs;
	}

	public void setTopNavs(List<TreeNode> topNavs) {
		this.topNavs = topNavs;
	}

	public Long getSelectedTopNavId() {
		return selectedTopNavId;
	}

	public void setSelectedTopNavId(Long selectedTopNavId) {
		this.selectedTopNavId = selectedTopNavId;
	}

	public List<TreeNode> getMidNavs() {
		return midNavs;
	}

	public void setMidNavs(List<TreeNode> midNavs) {
		this.midNavs = midNavs;
	}
	
}
