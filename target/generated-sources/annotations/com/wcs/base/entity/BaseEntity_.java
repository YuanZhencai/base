package com.wcs.base.entity;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ extends com.wcs.base.entity.IdEntity_ {

	public static volatile SingularAttribute<BaseEntity, Boolean> defunctInd;
	public static volatile SingularAttribute<BaseEntity, String> createdBy;
	public static volatile SingularAttribute<BaseEntity, Date> createdDatetime;
	public static volatile SingularAttribute<BaseEntity, String> updatedBy;
	public static volatile SingularAttribute<BaseEntity, Date> updatedDatetime;

}

