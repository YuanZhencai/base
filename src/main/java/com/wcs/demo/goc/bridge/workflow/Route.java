package com.wcs.demo.goc.bridge.workflow;

public abstract class Route implements IRoute {
	
	private String name;

	@Override
	public void excute() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
