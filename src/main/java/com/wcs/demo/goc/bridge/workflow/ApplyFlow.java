package com.wcs.demo.goc.bridge.workflow;


public class ApplyFlow extends Workflow {
	
	public ApplyFlow() {
		setType("申请流程");
	}

	@Override
	public void dispatch() {
		System.out.println(getType() + " dispatch " + getResult());
	}

	
}
