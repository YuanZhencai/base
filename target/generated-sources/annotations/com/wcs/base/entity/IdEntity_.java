package com.wcs.base.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(IdEntity.class)
public abstract class IdEntity_ {

	public static volatile SingularAttribute<IdEntity, Long> id;
	public static volatile SingularAttribute<IdEntity, Integer> hashCode;

}

