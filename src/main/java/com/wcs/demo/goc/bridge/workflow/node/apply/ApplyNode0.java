package com.wcs.demo.goc.bridge.workflow.node.apply;

public class ApplyNode0 extends ApplyNode {
	
	public ApplyNode0() {
		super("节点0", "0");
		this.showButtons();
	}
	
	public ApplyNode0(String name) {
		super(name, "0");
	}

	@Override
	public void excute() {
		super.excute();
		System.out.println(getType() + " 创建");
		// save the create wf data to db
		// ...
	}

	@Override
	public void showButtons() {
		super.showButtons();
		addButton("CreateButton");
	}

}
