/** * Television.java 
 * Created on 2014年5月8日 下午2:22:38 
 */

package com.wcs.demo.goc.command.television;


/** 
* <p>Project: btcbase</p> 
* <p>Title: Television.java</p> 
* <p>Description: 电视机</p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class Television {

	public void open() {
		System.out.println("Television.open()");
	}

	public void close() {
		System.out.println("Television.close()");
	}

	public void changeChanel(String chanel) {
		System.out.println("Television.changeChanel() to " + chanel);
	}
}
