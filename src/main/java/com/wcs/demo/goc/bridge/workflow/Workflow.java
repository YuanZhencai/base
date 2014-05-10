package com.wcs.demo.goc.bridge.workflow;

import com.wcs.demo.goc.bridge.workflow.node.Node;
import com.wcs.demo.goc.bridge.workflow.route.Route;

public abstract class Workflow {

	private String type;
	
	private Route route;
	
	private Node node;
	
	public void dispatch() {
	}

	public String getResult(){
		Node node = getNode();
		String currentSeqNo = node.getSeqNo();
		node.excute();
		String nextSeqNo = node.getSeqNo();
		return currentSeqNo + " → " + nextSeqNo;
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

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	
}
