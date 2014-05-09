/** * SummeryCommand.java 
* Created on 2014年5月9日 上午11:25:40 
*/

package com.wcs.demo.goc.command.report;

import com.wcs.demo.goc.command.Command;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: SummeryCommand.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class SummeryCommand implements Command {
	
	private Report report = null;
	
	public SummeryCommand(Report report) {
		System.out.println("SummeryCommand.SummeryCommand()");
		this.report = report;
	}

	@Override
	public void exe() {
		report.summery();
	}

}
