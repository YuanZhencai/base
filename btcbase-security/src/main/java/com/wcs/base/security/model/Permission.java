package com.wcs.base.security.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wcs.base.entity.IdEntity;

/**
 * 资源
 * 
 * 注释见{@link Role}.
 * 
 * @author chris.guan
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "permission")
public class Permission extends IdEntity {
	private Long roleId;
    private String url; // 资源名称
    
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
