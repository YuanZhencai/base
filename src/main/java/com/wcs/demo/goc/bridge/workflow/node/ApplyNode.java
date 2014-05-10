package com.wcs.demo.goc.bridge.workflow.node;

public class ApplyNode extends Node {
	
	public ApplyNode(String name, String seqNo) {
		setType("APPLY");
		setName(name);
		setSeqNo(seqNo);
	}

	@Override
	public void excute() {
		// save data to db
		// ...
		
		// find next seq no
		setSeqNo(findSeqNoBy());
	}
	
}
