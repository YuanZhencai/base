/** * MySubject.java 
* Created on 2014年5月9日 下午4:50:51 
*/

package com.wcs.demo.goc.observer;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: MySubject.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class MySubject extends AbstractSubject {

	@Override
	public void operation() {
		System.out.println("MySubject.operation()");
		notifyObservers();
	}

}
