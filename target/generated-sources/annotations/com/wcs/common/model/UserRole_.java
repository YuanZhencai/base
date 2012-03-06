package com.wcs.common.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserRole.class)
public abstract class UserRole_ extends com.wcs.base.entity.IdEntity_ {

	public static volatile SingularAttribute<UserRole, Role> role;
	public static volatile SingularAttribute<UserRole, User> user;

}

