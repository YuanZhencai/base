package com.wcs.demo.goc.bridge.workflow;

public class DocFlow extends Workflow {
	
	public DocFlow() {
		setType("文档流程");
	}

	@Override
	public void dispatch() {
		getRoute().gateway();
		System.out.println(getType() + " dispatch " + getRoute().getName());
	}

	
}
