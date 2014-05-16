package com.wcs.demo.goc.bridge.workflow;

import com.wcs.demo.goc.bridge.workflow.node.Node;
import com.wcs.demo.goc.bridge.workflow.node.report.ReportNode;
import com.wcs.demo.goc.command.report.Report;

public class ReportFlow extends Workflow {
	
	private Report report;

	public ReportFlow() {
		setType("报表流程");
	}

	@Override
	public void dispatch() {
		ReportNode reportNode = (ReportNode) getNode();
		reportNode.excute();
		Node nextNode = findNextNodeByCurrentNode(reportNode);
		reportNode.getStep().setCode(nextNode.getSeqNo());
		if(nextNode != null) {
			setNode(nextNode);
		}
		setReport(reportNode.getReport());
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}
