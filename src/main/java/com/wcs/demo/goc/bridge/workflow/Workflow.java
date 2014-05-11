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
		Node nextNode = findNextNode();
		if(nextNode != null) {
			setNode(nextNode);
		}
		return currentSeqNo + " → " + nextSeqNo;
	}
	
	public Node findNextNode() {
		String nextNode = node.getType() + "Node" + node.getSeqNo();
		String className = node.getClass().getPackage().getName()+ "." + nextNode;
		try {
			return (Node) Class.forName(className).newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null; 
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
