/** * ResourceReport.java 
* Created on 2014年5月9日 上午11:41:10 
*/

package com.wcs.demo.goc.command.report;

import java.io.InputStream;
import java.util.List;

import com.wcs.common.model.Resourcemstr;
import com.wcs.demo.goc.strategy.report.jasper.ResourceSummery;
import com.wcs.demo.goc.strategy.report.jasper.SummeryInterface;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: ResourceReport.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class ResourceReport extends Report {
	
	private List<Resourcemstr> data = null;
	private InputStream is = null;
	
	public ResourceReport(List<Resourcemstr> data) {
		System.out.println("ResourceReport.ResourceReport()");
		this.data = data;
	}
	
	public ResourceReport(InputStream is) {
		System.out.println("ResourceReport.ResourceReport()");
		this.is = is;
	}

	@Override
	public void summery() {
		SummeryInterface summery = new ResourceSummery(data);
		summery.summery();
	}

	@Override
	public void upload() {
		System.out.println("ResourceReport.upload() " + is.toString());
	}

	
}
