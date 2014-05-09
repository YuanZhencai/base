/** * Observer1.java 
* Created on 2014年5月9日 下午4:42:43 
*/

package com.wcs.demo.goc.observer;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: Observer1.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class Observer1 implements Observer {

	@Override
	public void update() {
		System.out.println("Observer1.update()");
	}

}
