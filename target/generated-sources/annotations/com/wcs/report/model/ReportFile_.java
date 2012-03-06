package com.wcs.report.model;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ReportFile.class)
public abstract class ReportFile_ extends com.wcs.base.entity.BaseEntity_ {

	public static volatile SingularAttribute<ReportFile, Date> upLoadedDatetime;
	public static volatile SingularAttribute<ReportFile, ReportMstr> reportMstr;
	public static volatile SingularAttribute<ReportFile, Boolean> useInd;
	public static volatile SingularAttribute<ReportFile, String> upLoadedBy;
	public static volatile SingularAttribute<ReportFile, Integer> versionNo;
	public static volatile SingularAttribute<ReportFile, String> fileName;
	public static volatile SingularAttribute<ReportFile, String> fileStoreLocation;
	public static volatile SingularAttribute<ReportFile, String> remarks;

}

