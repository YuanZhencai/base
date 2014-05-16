package com.wcs.demo.goc.bridge.workflow;


public class CheckDocFlow extends Workflow {
	
	public CheckDocFlow() {
		setType("检入文档流程");
	}

	@Override
	public void dispatch() {
		System.out.println(getType() + " dispatch " + doDispatch());
	}

	
}
