package com.wcs.common.model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public abstract class Role_ extends com.wcs.base.entity.IdEntity_ {

	public static volatile SetAttribute<Role, UserRole> userRoles;
	public static volatile SingularAttribute<Role, String> roleScope;
	public static volatile SingularAttribute<Role, Boolean> admin;
	public static volatile SingularAttribute<Role, String> description;
	public static volatile SetAttribute<Role, Permission> permissions;
	public static volatile SingularAttribute<Role, Integer> state;
	public static volatile SingularAttribute<Role, String> roleName;
	public static volatile SetAttribute<Role, RoleResource> roleResources;

}

