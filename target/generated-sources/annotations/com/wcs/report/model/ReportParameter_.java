package com.wcs.report.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ReportParameter.class)
public abstract class ReportParameter_ extends com.wcs.base.entity.BaseEntity_ {

	public static volatile SingularAttribute<ReportParameter, String> defaultValueType;
	public static volatile SingularAttribute<ReportParameter, String> reportParameterCode;
	public static volatile SingularAttribute<ReportParameter, String> remarks;
	public static volatile SingularAttribute<ReportParameter, Boolean> displayInd;
	public static volatile SingularAttribute<ReportParameter, String> javaDataType;
	public static volatile SingularAttribute<ReportParameter, ReportMstr> reportMstr;
	public static volatile SingularAttribute<ReportParameter, String> sqlStatement;
	public static volatile SingularAttribute<ReportParameter, String> uiLabelLang;
	public static volatile SingularAttribute<ReportParameter, String> uiLabel;
	public static volatile SingularAttribute<ReportParameter, String> uiType;
	public static volatile SingularAttribute<ReportParameter, String> defaultValue;
	public static volatile SingularAttribute<ReportParameter, Boolean> mandatoryInd;
	public static volatile SingularAttribute<ReportParameter, Boolean> editInd;

}

