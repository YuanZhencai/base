package com.wcs.report.model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ReportMstr.class)
public abstract class ReportMstr_ extends com.wcs.base.entity.BaseEntity_ {

	public static volatile SingularAttribute<ReportMstr, String> reportCategory;
	public static volatile SetAttribute<ReportMstr, ReportFile> reportFiles;
	public static volatile SetAttribute<ReportMstr, ReportRole> reportRoles;
	public static volatile SingularAttribute<ReportMstr, String> reportNameLang;
	public static volatile SingularAttribute<ReportMstr, String> reportConfig;
	public static volatile SingularAttribute<ReportMstr, String> reportMode;
	public static volatile SingularAttribute<ReportMstr, String> remarks;
	public static volatile SetAttribute<ReportMstr, ReportParameter> reportParameters;
	public static volatile SingularAttribute<ReportMstr, String> reportCode;
	public static volatile SingularAttribute<ReportMstr, String> reportName;

}

