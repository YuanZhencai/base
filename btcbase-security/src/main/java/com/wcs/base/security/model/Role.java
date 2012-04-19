package com.wcs.base.security.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.wcs.base.entity.IdEntity;

/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name = "role")
public class Role extends IdEntity {
	private static final long serialVersionUID = 1L;
	private String name;

	public Role() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}