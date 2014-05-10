package com.wcs.demo.goc.bridge.workflow;

public class ReportRoute12 extends Route {
	
	public ReportRoute12() {
		setName("路由：1 → 2");
	}

	@Override
	public void excute() {
		
		System.out.println(getName() + " excute");

	}

}
