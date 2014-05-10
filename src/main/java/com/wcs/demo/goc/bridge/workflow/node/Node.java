package com.wcs.demo.goc.bridge.workflow.node;


public abstract class Node implements INode {

	private String name;
	private String status;
	private String seqNo;
	
	private String type;
	
	@Override
	public void excute() {
		
	}
	
	public enum SeqNo {
		DOC_0_PASS("1"),
		DOC_1_PASS("2"),
		REPORT_0_PASS("1"),
		REPORT_1_PASS("2"),
		APPLY_0_PASS("1"),
		APPLY_1_PASS("2"),
		CHECKDOC_0_PASS("1"),
		CHECKDOC_1_PASS("2");

		private String seqNo;

		SeqNo(String end) {
			this.seqNo = end;
		}
		
		public String getValue() {
			return seqNo;
		}

	}
	
	public String findSeqNoBy() {
		String f_s_s = type + "_" + seqNo + "_" + status;
		return Enum.valueOf(SeqNo.class, f_s_s).getValue();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	
}
