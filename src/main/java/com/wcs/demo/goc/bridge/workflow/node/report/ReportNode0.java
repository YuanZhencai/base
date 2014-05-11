package com.wcs.demo.goc.bridge.workflow.node.report;

import com.wcs.demo.goc.bridge.workflow.route.ReportRoute;
import com.wcs.demo.goc.bridge.workflow.route.Route;

public class ReportNode0 extends ReportNode {
	
	public ReportNode0() {
		super("汇总报表节点", "0");
		this.showButtons();
	}
	
	public ReportNode0(String name) {
		super(name, "0");
	}

	@Override
	public void excute() {
		ReportRoute route =  (ReportRoute) createRoute();
		route.gateway();
		setReport(route.getReport());
		System.out.println("发送消息：" + getName() + "创建了一张报表，" + getReport().getAbsolutePath());
	}

	@Override
	public void showButtons() {
		super.showButtons();
		addButton("CreateButton");
		// 汇总按钮
		addButton("SummeryButton");
	}

	public static void main(String[] args) {
		ReportNode0 reportNode0 = new ReportNode0();
		reportNode0.setStatus("PASS");
		reportNode0.excute();
	}
}
