/** * Observer2.java 
* Created on 2014年5月9日 下午4:42:43 
*/

package com.wcs.demo.goc.bserver;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: Observer2.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class Observer2 implements Observer {

	@Override
	public void update() {
		System.out.println("Observer2.update()");
	}

}
