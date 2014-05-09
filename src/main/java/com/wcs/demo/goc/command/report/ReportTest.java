/** * ReportTest.java 
* Created on 2014年5月9日 上午11:35:39 
*/

package com.wcs.demo.goc.command.report;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wcs.common.model.Resourcemstr;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: ReportTest.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class ReportTest {

	@Test
	public void testSummery() {
		Report report = new Report();
		SummeryCommand command = new SummeryCommand(report);
		ReportInvoker reportInvoker = new ReportInvoker(command);
		reportInvoker.excute();
	}
	
	@Test
	public void testResourceSummery() {
		List<Resourcemstr> data = new ArrayList<Resourcemstr>();
		Resourcemstr r = null;
		for (int i = 0; i < 10; i++) {
			r = new Resourcemstr();
			r.setName("prant" + i);
			r.setSeqNo(i + "");
			r.setUri("uri" + i);
			data.add(r);
		}
		Report report = new ResourceReport(data);
		SummeryCommand command = new SummeryCommand(report);
		ReportInvoker reportInvoker = new ReportInvoker(command);
		reportInvoker.excute();
	}

}
