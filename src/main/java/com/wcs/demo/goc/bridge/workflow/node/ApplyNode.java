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

	@Override
	public void showButtonsBySeqNo() {
		switch (Integer.valueOf(getSeqNo())) {
		case 0:
			addNode0Buttons();
			break;
		case 1:
			addNode1Buttons();
			break;

		default:
			break;
		}
		
	}
	
	public void addNode0Buttons(){
		clearButtons();
		addButton("CreateButton");
		addButton("CloseButton");
	}
	
	public void addNode1Buttons(){
		clearButtons();
		addButton("PassButton");
		addButton("CloseButton");
	}
	
}
