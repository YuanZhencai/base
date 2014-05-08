/** * SampleFactoryTest.java 
* Created on 2014年5月8日 下午1:12:16 
*/

package com.wcs.demo.goc.factory;

import org.junit.Test;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: SampleFactoryTest.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class FactoryTest {

	@Test
	public void testSampleFactory() {
		SampleFactory factory = new SampleFactory();
		factory.produce("sms").send();
		factory.produce("mail").send();
	}
	
	@Test
	public void testMethodFactory() {
		MethodFactory factory = new MethodFactory();
		factory.produceSms().send();
		factory.produceMail().send();
	}

	@Test
	public void testStaticMethodFactory() {
		StaticMethodFactory.produceSms().send();
		StaticMethodFactory.produceMail().send();
	}
}
