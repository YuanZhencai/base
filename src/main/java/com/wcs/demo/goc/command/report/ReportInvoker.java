/** * ReportInvoker.java 
* Created on 2014年5月9日 上午11:24:12 
*/

package com.wcs.demo.goc.command.report;

import com.wcs.demo.goc.command.Command;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: ReportInvoker.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class ReportInvoker {
	
	private Command command = null;
	
	public ReportInvoker(Command command) {
		System.out.println("ReportInvoker.ReportInvoker()");
		this.setCommand(command);
	}

	public void excute() {
		System.out.println("ReportInvoker.excute() " + getCommand().getClass().getSimpleName());
		getCommand().exe();
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
}
