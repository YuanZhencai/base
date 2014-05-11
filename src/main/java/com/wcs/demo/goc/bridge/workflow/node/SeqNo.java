package com.wcs.demo.goc.bridge.workflow.node;

public enum SeqNo {
	DOC_0_PASS("1"),
	DOC_1_PASS("2"),
	REPORT_0_PASS("1"),
	REPORT_1_PASS("2"),
	Apply_0_PASS("1"),
	Apply_1_PASS("2"),
	CheckDoc_0_PASS("1"),
	CheckDoc_1_PASS("2"),
	Report_0_PASS("1"),
	Report_1_PASS("2");

	private String seqNode;

	SeqNo(String seqNo) {
		this.seqNode = seqNo;
	}
	
	public String getValue() {
		return seqNode;
	}

}