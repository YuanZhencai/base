package com.wcs.demo.goc.bridge.workflow;

public class ReportRoute01 extends Route {
	
	public ReportRoute01() {
		setName("路由：0 → 1");
	}

	@Override
	public void excute() {
		
		System.out.println(getName() + " excute");

	}

}
