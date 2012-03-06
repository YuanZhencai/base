package com.wcs.demo.test;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TestUser.class)
public abstract class TestUser_ {

	public static volatile SingularAttribute<TestUser, String> id;
	public static volatile SingularAttribute<TestUser, String> lastName;
	public static volatile SingularAttribute<TestUser, String> firstName;

}

