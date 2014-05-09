/** * ObserverTest.java 
 * Created on 2014年5月9日 下午4:53:12 
 */

package com.wcs.demo.goc.bserver;

import org.junit.Test;


/** 
* <p>Project: btcbase</p> 
* <p>Title: ObserverTest.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class ObserverTest {

	@Test
	public void testOperate() {
		Subject sub = new MySubject();
		
		sub.add(new Observer1());
		sub.add(new Observer2());

		sub.operation();

	}

}
