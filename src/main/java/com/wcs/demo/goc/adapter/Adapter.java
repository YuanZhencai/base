/** * Adapter.java 
* Created on 2014年5月9日 下午2:56:59 
*/

package com.wcs.demo.goc.adapter;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: Adapter.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class Adapter extends Source implements Targetable {

	@Override
	public void method2() {
		System.out.println("Adapter.method2()");
	}

}
