package com.wcs.demo.goc.bridge.workflow.route;

import com.wcs.demo.goc.command.report.Report;

public class ReportRoute extends Route {
	
	private Report report;
	
	public ReportRoute(String name, String start, String status) {
		setName(name);
		setStart(start);
		setStatus(status);
	}

	@Override
	public void gateway() {
		
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}
