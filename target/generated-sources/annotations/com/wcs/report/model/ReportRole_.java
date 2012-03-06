package com.wcs.report.model;

import com.wcs.common.model.Role;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ReportRole.class)
public abstract class ReportRole_ extends com.wcs.base.entity.BaseEntity_ {

	public static volatile SingularAttribute<ReportRole, ReportMstr> reportMstr;
	public static volatile SingularAttribute<ReportRole, Role> role;

}

