package com.wcs.demo.goc.bridge.workflow;

public class ReportFlow extends Workflow {

	public ReportFlow() {
		setType("报表流程");
	}

	@Override
	public void dispatch() {
		getRoute().excute();
		System.out.println(getType() + " dispatch " + getRoute().getName());
	}

}
