package com.wcs.demo.goc.bridge.workflow.route;

public class ReportRouteEnd extends ReportRoute {
	
	public ReportRouteEnd() {
		super("路由：2 → 完成", "2", "COMPLETE");
	}

	public ReportRouteEnd(String start, String status) {
		super("路由：2 → 完成", start, status);
	}
	
	@Override
	public void gateway() {
		
		System.out.println(getName());
		
	}

}
