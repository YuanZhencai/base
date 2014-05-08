/** * ComputerTest.java 
 * Created on 2014年5月8日 下午1:44:38 
 */

package com.wcs.demo.goc.facade;

import org.junit.Test;

/** 
* <p>Project: btcbase</p> 
* <p>Title: ComputerTest.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class ComputerTest {

	@Test
	public void testStartup() {
		Computer computer = new Computer();
		computer.startup();

	}

	@Test
	public void testShutdown() {
		Computer computer = new Computer();
		computer.shutdown();
	}

}
