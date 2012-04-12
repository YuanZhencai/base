package com.wcs.base.security.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Sets;
import com.wcs.base.entity.IdEntity;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
public class Role extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String name;
	//private Set<String> permissionSet = Sets.newHashSet();

    public Role() {
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	@ElementCollection
//	@CollectionTable(name = "permission", joinColumns = { @JoinColumn(name = "role_id") })
//	@Column(name = "url")
//	public Set<String> getPermissionSet() {
//		return permissionSet;
//	}
//
//	public void setPermissionSet(Set<String> permissionSet) {
//		this.permissionSet = permissionSet;
//	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}