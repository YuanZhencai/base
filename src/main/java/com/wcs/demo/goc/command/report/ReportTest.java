/** * ReportTest.java 
* Created on 2014年5月9日 上午11:35:39 
*/

package com.wcs.demo.goc.command.report;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wcs.common.model.Resourcemstr;
import com.wcs.demo.goc.command.Command;

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
		resourceSummery();
	}
	
	@Test
	public void testResourceUpload() {
		// summery
		String resourceName = resourceSummery();
		// upload resource report
		resourceUpload(resourceName);
	}


	private String resourceSummery() {
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
		Command command = new SummeryCommand(report);
		ReportInvoker reportInvoker = new ReportInvoker(command);
		reportInvoker.excute();
		String absolutePath = report.getAbsolutePath();
		System.out.println("[Report Path]"+absolutePath);
		return absolutePath;
	}
	
	
	private void resourceUpload(String filePath) {
		FileInputStream is = null;
		try {
			is = new FileInputStream(filePath);
			Report report = new ResourceReport(is);
			Command command = new UploadCommand(report);
			ReportInvoker reportInvoker = new ReportInvoker(command);
			reportInvoker.excute();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
