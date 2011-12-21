package com.wcs.service;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

//@RunWith(Arquillian.class)
public class TemperatureConverterTest {
	   //@Inject
	   private TemperatureConverter converter;

//	   @Deployment
//	   public static JavaArchive createTestArchive() {
//	      return ShrinkWrap.create(JavaArchive.class, "test.jar")
//	         .addClasses(TemperatureConverter.class)
//	         .addAsManifestResource(
//	            EmptyAsset.INSTANCE, 
//	            ArchivePaths.create("beans.xml")); 
//	   }
//
//	   @Test
//	   public void testConvertToCelsius() {
//	      Assert.assertEquals(converter.convertToCelsius(32d), 0d);
//	      Assert.assertEquals(converter.convertToCelsius(212d), 100d);
//	   }
//
//	   @Test
//	   public void testConvertToFarenheit() {
//	      Assert.assertEquals(converter.convertToFarenheit(0d), 32d);
//	      Assert.assertEquals(converter.convertToFarenheit(100d), 212d);
//	   }
}
