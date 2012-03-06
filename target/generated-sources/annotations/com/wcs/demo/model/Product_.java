package com.wcs.demo.model;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public abstract class Product_ extends com.wcs.base.entity.BaseEntity_ {

	public static volatile SingularAttribute<Product, String> category;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Boolean> available;
	public static volatile SingularAttribute<Product, String> code;
	public static volatile SingularAttribute<Product, Date> productingDate;

}

