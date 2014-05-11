package com.wcs.demo.goc.bridge.workflow.node.checkdoc;

import com.wcs.demo.goc.bridge.workflow.node.Node;

public class CheckDocNode extends Node {
	
	public CheckDocNode(String name, String seqNo) {
		setType("CheckDoc");
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

	@Override
	public void showButtons() {
		// TODO Auto-generated method stub
		
	}
	
}
