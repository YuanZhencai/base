package com.wcs.common.model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ extends com.wcs.base.entity.BaseEntity_ {

	public static volatile SetAttribute<User, UserRole> userRoles;
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> cellPhone;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> telephone;
	public static volatile SingularAttribute<User, String> userPass;
	public static volatile SingularAttribute<User, String> loginName;

}

