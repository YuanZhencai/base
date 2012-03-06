package com.wcs.common.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Resource.class)
public abstract class Resource_ extends com.wcs.base.entity.BaseEntity_ {

	public static volatile SingularAttribute<Resource, Long> parentId;
	public static volatile SingularAttribute<Resource, String> keyName;
	public static volatile SingularAttribute<Resource, Integer> level;
	public static volatile SingularAttribute<Resource, Boolean> isLeaf;
	public static volatile SingularAttribute<Resource, String> name;
	public static volatile SingularAttribute<Resource, String> number;
	public static volatile SingularAttribute<Resource, Boolean> ismenu;
	public static volatile SingularAttribute<Resource, String> url;

}

