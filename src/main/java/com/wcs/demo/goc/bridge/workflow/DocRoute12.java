package com.wcs.demo.goc.bridge.workflow;

public class DocRoute12 extends Route {
	
	public DocRoute12() {
		setName("路由：1 → 2");
	}
	
	@Override
	public void excute() {
		
		System.out.println(getName() + " excute");

	}

}
