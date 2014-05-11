package com.wcs.demo.goc.bridge.workflow.route;

public class DocRoute01 extends Route {
	
	public DocRoute01() {
		setName("路由：0 → 1");
	}
	
	public DocRoute01(String start, String status) {
		setName("路由：0 → 1");
		setStart(start);
		setStatus(status);
	}

	@Override
	public void gateway() {
		
		System.out.println(getName() + " excute result " + getResult("DOC"));

	}

}
