package com.wcs.demo.goc.bridge.workflow.node.report;

import com.wcs.demo.goc.bridge.workflow.route.ReportRoute;


public class ReportNode2 extends ReportNode {
	
	public ReportNode2() {
		super("完成报表节点", "2");
		this.showButtons();
	}
	
	public ReportNode2(String name) {
		super(name, "2");
		this.showButtons();
	}

	@Override
	public void excute() {
		ReportRoute route =  (ReportRoute) createRoute();
		route.gateway();
		System.out.println("发送消息：" + getName() + " 完成了");
	}

	@Override
	public void showButtons() {
		super.showButtons();
		// 上传按钮
		addButton("CompleteButton");
	}

}
