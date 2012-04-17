package com.wcs.base.security.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
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
	private Set<Permission> permissionSet = Sets.newHashSet();

	public Role() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ElementCollection
	@CollectionTable(name = "permission", joinColumns = { @JoinColumn(name = "role_id") })
	@Column(name = "url")
	public Set<Permission> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<Permission> permissionSet) {
		this.permissionSet = permissionSet;
	}

	@Transient
	public String getPermissionNames() {
		List<String> permissionNameList = Lists.newArrayList();
		for (Permission permission : permissionSet) {
			permissionNameList.add(permission.getUrl());
		}
		return StringUtils.join(permissionNameList, ",");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}