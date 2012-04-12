package com.wcs.base.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wcs.base.entity.IdEntity;

/**
 * The persistent class for the permission database table.
 * 
 */
@Entity
@Table(name = "permission")
public class Permission extends IdEntity {
	private static final long serialVersionUID = 1L;
	private Long roleid;
	private String url;

	public Permission() {
	}

	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	@Column(length = 255)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}