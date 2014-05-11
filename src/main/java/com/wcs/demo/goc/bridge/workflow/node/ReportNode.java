package com.wcs.demo.goc.bridge.workflow.node;

import com.wcs.demo.goc.command.report.Report;

public class ReportNode extends Node {
	
	private Report report;
	
	public ReportNode(String name, String seqNo) {
		setType("Report");
		setName(name);
		setSeqNo(seqNo);
	}

	@Override
	public void excute() {
		// save data to db
		// ...
		
		// find next seq no
		String nextSeqNo = findSeqNoBy();
		setSeqNo(nextSeqNo);
	}

	@Override
	public void showButtons() {
		super.showButtons();
		// 显示关闭按钮
		addButton("CloseButton");
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	

}
