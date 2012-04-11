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
 * 权限组.
 * 
 * 注释见{@link User}.
 * 
 * @author chris.guan
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "role")
public class Role extends IdEntity {

	private String name;

	private Set<String> permissionSet = Sets.newHashSet();
	
	public Role(Long id, String name) {
		this.setId(id);
		this.name = name;
	}

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ElementCollection
	@CollectionTable(name = "permission", joinColumns = { @JoinColumn(name = "role_id") })
	@Column(name = "url")
	public Set<String> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<String> permissionSet) {
		this.permissionSet = permissionSet;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
