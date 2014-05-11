package com.wcs.demo.goc.bridge.workflow.node.apply;

public class ApplyNode2 extends ApplyNode {
	
	public ApplyNode2() {
		super("节点2", "2");
		this.showButtons();
	}
	
	public ApplyNode2(String name) {
		super(name, "2");
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
		addButton("RejectButton");
	}

}
