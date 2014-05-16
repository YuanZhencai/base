package com.wcs.demo.goc.bridge.workflow;

import com.wcs.demo.goc.bridge.workflow.node.Node;
import com.wcs.demo.goc.bridge.workflow.node.SeqNode;
import com.wcs.demo.goc.bridge.workflow.route.Route;

public abstract class Workflow {

	private String type;
	
	private Route route;
	
	private Node node;
	
	public void dispatch() {
	}
	
	public String doDispatch(){
		Node node = getNode();
		Node nextNode = findNextNodeByCurrentNode(node);
		String currentSeqNo = node.getSeqNo();
		node.excute();
		nextNode.setStep(node.getStep());
		String nextSeqNo = node.getSeqNo();
		if(nextNode != null) {
			setNode(nextNode);
		}
		return currentSeqNo + " → " + nextSeqNo;
	}
	
	public Node findNextNodeByCurrentNode(Node node) {
		String f_s_s = node.getType() + "_" + node.getSeqNo() + "_" + node.getStatus();
		return Enum.valueOf(SeqNode.class, f_s_s).getValue();
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
