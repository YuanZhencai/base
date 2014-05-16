package com.wcs.demo.goc.bridge.workflow.node.apply;

public class ApplyNode1 extends ApplyNode {
	
	public ApplyNode1() {
		super("节点1", "1");
		setTracker("step1_Tracker");
		this.showButtons();
	}
	
	public ApplyNode1(String name) {
		super(name, "1");
		setTracker("step1_Tracker");
		this.showButtons();
	}

	@Override
	public void excute() {
		super.excute();
		System.out.println(getType() + " 通过");
		// save the create wf data to db
		// ...
	}

	@Override
	public void showButtons() {
		super.showButtons();
		addButton("PassButton");
	}

}
