package com.wcs.demo.goc.bridge.workflow.node;

public enum SeqNode {
	Apply_0_PASS(new ApplyNode1()),
	Apply_1_PASS(new ApplyNode2());

	private Node seqNo;

	SeqNode(Node seqNo) {
		this.seqNo = seqNo;
	}
	
	public Node getValue() {
		return seqNo;
	}

}
