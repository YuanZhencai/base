package com.wcs.demo.goc.bridge.workflow.route;


public enum Routes {
	Report_0_PASS(new ReportRoute01()),
	Report_1_PASS(new ReportRoute12()),
	Report_2_COMPLETE(new ReportRouteEnd());

	private Route route;

	Routes(Route route) {
		this.route = route;
	}
	
	public Route getValue() {
		return route;
	}

}
