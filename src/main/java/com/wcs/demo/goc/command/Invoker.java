/** * Invoker.java 
 * Created on 2014年5月8日 下午1:59:52 
 */

package com.wcs.demo.goc.command;


/** 
* <p>Project: btcbase</p> 
* <p>Title: Invoker.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class Invoker {

	private Command command;

	public Invoker(Command command) {
		System.out.println("Invoker.Invoker()");
		this.command = command;
	}

	public void action() {
		System.out.println("Invoker.action() " + command.getClass().getSimpleName());
		command.exe();
	}

}
