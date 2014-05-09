/** * UploadCommand.java 
* Created on 2014年5月9日 下午12:43:10 
*/

package com.wcs.demo.goc.command.report;

import com.wcs.demo.goc.command.Command;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: UploadCommand.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class UploadCommand implements Command {
	
	private Report report = null;
	
	public UploadCommand(Report report) {
		System.out.println("UploadCommand.UploadCommand()");
		this.report = report;
	}

	@Override
	public void exe() {
		System.out.println("UploadCommand.exe()");
		report.upload();
	}

}
