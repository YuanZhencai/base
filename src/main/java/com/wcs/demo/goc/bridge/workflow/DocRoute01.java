package com.wcs.demo.goc.bridge.workflow;

public class DocRoute01 extends Route {
	
	public DocRoute01() {
		setName("路由：0 → 1");
	}

	@Override
	public void excute() {
		
		System.out.println(getName() + " excute");

	}

}
