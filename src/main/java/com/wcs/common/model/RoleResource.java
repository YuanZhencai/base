package com.wcs.common.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wcs.base.entity.IdEntity;

/**
 * 
 * <p>Project: cmdpms</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chenlong@wcs-global.com">chenlong</a>
 */
@Entity
@Table(name = "role_resource")
public class RoleResource extends IdEntity  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Role role;
	private Resource resource;
	

	public RoleResource() {
	}

	public RoleResource(Role role, Resource resource) {
		this.role = role;
		this.resource = resource;
	}



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Resource_id")
	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}


}
