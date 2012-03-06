package com.wcs.common.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Permission.class)
public abstract class Permission_ extends com.wcs.base.entity.IdEntity_ {

	public static volatile SingularAttribute<Permission, Role> role;
	public static volatile SingularAttribute<Permission, String> permissionName;
	public static volatile SingularAttribute<Permission, String> permission;

}

