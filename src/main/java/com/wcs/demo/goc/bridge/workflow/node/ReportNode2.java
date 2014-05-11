package com.wcs.demo.goc.bridge.workflow.node;


public class ReportNode2 extends ReportNode {
	
	public ReportNode2() {
		super("完成报表节点", "2");
		this.showButtons();
	}
	
	public ReportNode2(String name) {
		super(name, "2");
	}

	@Override
	public void excute() {
		System.out.println(getType() + " 完成");
	}

	@Override
	public void showButtons() {
		super.showButtons();
		// 上传按钮
		addButton("CompleteButton");
	}

}
