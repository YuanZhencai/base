package com.wcs.demo.goc.bridge.workflow.node;

import com.wcs.demo.goc.bridge.workflow.node.apply.ApplyNode1;
import com.wcs.demo.goc.bridge.workflow.node.apply.ApplyNode2;
import com.wcs.demo.goc.bridge.workflow.node.report.ReportNode1;
import com.wcs.demo.goc.bridge.workflow.node.report.ReportNode2;

public enum SeqNode {
	Apply_0_PASS(new ApplyNode1()),
	Apply_1_PASS(new ApplyNode2()),
	Report_0_PASS(new ReportNode1()),
	Report_1_PASS(new ReportNode2()),
	Report_2_COMPLETE(new ReportNode2());

	private Node seqNo;

	SeqNode(Node seqNo) {
		this.seqNo = seqNo;
	}
	
	public Node getValue() {
		return seqNo;
	}

}
