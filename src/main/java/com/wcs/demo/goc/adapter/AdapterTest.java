/** * AdapterTest.java 
* Created on 2014年5月9日 下午2:58:36 
*/

package com.wcs.demo.goc.adapter;

import org.junit.Test;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: AdapterTest.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class AdapterTest {

	@Test
	public void testClassAdapter() {
		Targetable target = new Adapter();  
		target.method1();
		target.method2();
	}
	
	@Test
	public void testObjectAdapter() {
		Targetable target =  new Wrapper(new Source()); 
		target.method1();
		target.method2();
	}

}
