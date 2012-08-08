package com.wcs.base.security.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.collect.Lists;
import com.wcs.base.entity.IdEntity;

/**
 * <p>Project: btcbase-security</p> 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2011-2020.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author guanjianghuai
 */
@Entity
@Table(name = "role")
public class Role extends IdEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false, length=50)
	private String code;

	@Column(nullable=false, length=20)
	private String name;

	@Column(name="DESC", length=50)
	private String desc;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "role")
	private List<RoleResource> roleResources = Lists.newArrayList();

//	@ManyToMany
//	@JoinTable(name = "role_resource", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "resource_id") })
//	private List<Resource> resourceList = Lists.newArrayList();

	//--------------------- setter & getter -------------------//
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<RoleResource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(List<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}
	
//	public List<Resource> getResourceList() {
//		return resourceList;
//	}
//
//	public void setResourceList(List<Resource> resourceList) {
//		this.resourceList = resourceList;
//	}
	
}