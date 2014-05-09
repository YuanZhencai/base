/** * Report.java 
* Created on 2014年5月9日 上午11:23:43 
*/

package com.wcs.demo.goc.command.report;

import java.util.ArrayList;
import java.util.List;

import com.wcs.common.model.Resourcemstr;
import com.wcs.demo.goc.strategy.report.jasper.ResourceSummery;
import com.wcs.demo.goc.strategy.report.jasper.SummeryInterface;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: Report.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class Report {
	private String absolutePath = null;

	public Report() {
		System.out.println("Report.Report()");
	}
	
	public void summery() {
		System.out.println("Report.summery()");
		List<Resourcemstr> data = new ArrayList<Resourcemstr>();
		Resourcemstr r = null;
		for (int i = 0; i < 10; i++) {
			r = new Resourcemstr();
			r.setName("prant" + i);
			r.setSeqNo(i + "");
			r.setUri("uri" + i);
			data.add(r);
		}
		SummeryInterface summery = new ResourceSummery(data);
		this.absolutePath = summery.summery();
	}
	
	public void upload() {
		System.out.println("Report.upload()");
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
}
