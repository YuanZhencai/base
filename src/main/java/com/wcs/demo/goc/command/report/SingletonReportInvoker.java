/** * SingletonReportInvoker.java 
* Created on 2014年5月9日 上午11:24:12 
*/

package com.wcs.demo.goc.command.report;



/** 
* <p>Project: btcbase</p> 
* <p>Title: SingletonReportInvoker.java</p> 
* <p>Description: </p> 
* <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
* <p>All Rights Reserved.</p>
* @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a> 
*/
public class SingletonReportInvoker {
	
	private static class SingletonFactory {
		private static ReportInvoker instance = new ReportInvoker();
	}
	
	private SingletonReportInvoker() {
		System.out.println("SingletonReportInvoker.SingletonReportInvoker()");
	}
	
	public static ReportInvoker getInstance() {
		return SingletonFactory.instance;
	}

}
