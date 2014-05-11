package com.wcs.demo.goc.bridge.workflow.route;

import com.wcs.demo.goc.bridge.workflow.node.Node;


public class RouteFactory {
	
	private RouteFactory() {
	}

	private static class SingletonFactory {
		private static RouteFactory instance = new RouteFactory();
	}
	
	public static RouteFactory getInstance() {
		return SingletonFactory.instance;
	}
	
	public Route create(Node node) {
		String f_s_s = node.getType() + "_" + node.getSeqNo() + "_" + node.getStatus();
		return Enum.valueOf(Routes.class, f_s_s).getValue();
	}
}
