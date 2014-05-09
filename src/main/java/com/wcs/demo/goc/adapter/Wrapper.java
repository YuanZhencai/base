/** * Wrapper.java 
* Created on 2014年5月9日 下午3:05:35 
*/

package com.wcs.demo.goc.adapter;


/** 
 * <p>Project: btcbase</p> 
 * <p>Title: Wrapper.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class Wrapper implements Targetable {
	
	private Source source;

	public Wrapper(Source source) {
		System.out.println("Wrapper.Wrapper() " + "对象的适配器模式");
		this.source = source;
	}
	
	@Override
	public void method1() {
		source.method1();
	}

	@Override
	public void method2() {
		System.out.println("Wrapper.method2()");
	}


}
