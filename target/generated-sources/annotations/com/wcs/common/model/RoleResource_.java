package com.wcs.common.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RoleResource.class)
public abstract class RoleResource_ extends com.wcs.base.entity.IdEntity_ {

	public static volatile SingularAttribute<RoleResource, Resource> resource;
	public static volatile SingularAttribute<RoleResource, Role> role;

}

