package com.wcs.demo.model;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Person.class)
public abstract class Person_ extends com.wcs.base.entity.BaseEntity_ {

	public static volatile SingularAttribute<Person, Boolean> vip;
	public static volatile SingularAttribute<Person, Date> birthday;
	public static volatile SingularAttribute<Person, String> sex;
	public static volatile SingularAttribute<Person, String> phone;
	public static volatile SingularAttribute<Person, String> nationality;
	public static volatile SingularAttribute<Person, String> email;
	public static volatile SingularAttribute<Person, String> address;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, String> remarks;

}

