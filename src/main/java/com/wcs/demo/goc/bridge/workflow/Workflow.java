package com.wcs.demo.goc.bridge.workflow;

public class Workflow {

	private String type;
	
	private Route route;
	
	public void dispatch() {
		getRoute().excute();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	
}
