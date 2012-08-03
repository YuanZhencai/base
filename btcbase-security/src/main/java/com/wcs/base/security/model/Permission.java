package com.wcs.base.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wcs.base.entity.IdEntity;

/**
 * <p>Project: btcbase-security</p> 
 * <p>Title: Permission.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@Entity
@Table(name = "permission")
public class Permission extends IdEntity {
	private static final long serialVersionUID = 1L;
	
	private Long roleid;

	@Column(length = 255)
	private String permission;

	public Permission() {
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}