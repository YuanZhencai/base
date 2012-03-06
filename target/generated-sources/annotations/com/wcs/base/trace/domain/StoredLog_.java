package com.wcs.base.trace.domain;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(StoredLog.class)
public abstract class StoredLog_ {

	public static volatile SingularAttribute<StoredLog, Long> id;
	public static volatile SingularAttribute<StoredLog, String> throwAbleMessage;
	public static volatile SingularAttribute<StoredLog, String> logLevel;
	public static volatile SingularAttribute<StoredLog, String> userMessage;
	public static volatile SingularAttribute<StoredLog, Date> logdttm;
	public static volatile SingularAttribute<StoredLog, String> whereClass;

}

