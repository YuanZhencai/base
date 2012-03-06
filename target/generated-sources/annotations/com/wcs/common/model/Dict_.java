package com.wcs.common.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Dict.class)
public abstract class Dict_ extends com.wcs.base.entity.IdEntity_ {

	public static volatile SingularAttribute<Dict, Boolean> defunctInd;
	public static volatile SingularAttribute<Dict, String> name;
	public static volatile SingularAttribute<Dict, String> value;
	public static volatile SingularAttribute<Dict, String> code;
	public static volatile SingularAttribute<Dict, String> parentCode;

}

