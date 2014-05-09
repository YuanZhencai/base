/** * AbstractWrapper.java 
* Created on 2014年5月9日 下午3:19:35 
*/

package com.wcs.demo.goc.adapter;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: AbstractWrapper.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public abstract class AbstractWrapper implements SourceInterface {
	
	public AbstractWrapper() {
		System.out.println("AbstractWrapper.AbstractWrapper() " + "接口的适配器模式");
	}

	@Override
	public void method1() {

	}

	@Override
	public void method2() {

	}

}
