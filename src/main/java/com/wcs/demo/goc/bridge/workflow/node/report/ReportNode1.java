package com.wcs.demo.goc.bridge.workflow.node.report;

import com.wcs.demo.goc.bridge.workflow.route.ReportRoute;

public class ReportNode1 extends ReportNode {
	
	public ReportNode1() {
		super("上传报表节点", "1");
		this.showButtons();
	}
	
	public ReportNode1(String name) {
		super(name, "1");
	}

	@Override
	public void excute() {
		ReportRoute route =  (ReportRoute) createRoute();
		route.setReport(getReport());
		route.gateway();
		System.out.println("发送消息：" + getName() + "上传了一张报表，" + getReport().getAbsolutePath());
	
	}

	@Override
	public void showButtons() {
		super.showButtons();
		addButton("PassButton");
		addButton("RejectButton");
		// 上传按钮
		addButton("UploadButton");
	}

}
