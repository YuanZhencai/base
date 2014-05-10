package com.wcs.demo.goc.bridge.workflow.node;

public class CheckDocNode extends Node {
	
	public CheckDocNode(String name, String seqNo) {
		setType("CHECKDOC");
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
