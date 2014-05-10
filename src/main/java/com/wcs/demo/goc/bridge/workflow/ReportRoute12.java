package com.wcs.demo.goc.bridge.workflow;

public class ReportRoute12 extends Route {
	
	public ReportRoute12() {
		setName("路由：1 → 2");
	}

	public ReportRoute12(String start, String status) {
		setName("路由：1 → 2");
		setStart(start);
		setStatus(status);
	}
	
	@Override
	public void excute() {
		
		System.out.println(getName() + " excute result " + getResult("REPORT"));

	}

}
