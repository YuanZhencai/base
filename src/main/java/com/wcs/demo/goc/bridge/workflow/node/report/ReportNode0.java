package com.wcs.demo.goc.bridge.workflow.node.report;

import com.wcs.demo.goc.bridge.workflow.model.WfStepmstr;
import com.wcs.demo.goc.bridge.workflow.route.ReportRoute;

public class ReportNode0 extends ReportNode {
	
	public ReportNode0() {
		super("报表流程创建节点", "0");
		this.showButtons();
	}
	
	public ReportNode0(String name) {
		super(name, "0");
		this.showButtons();
	}

	@Override
	public void excute() {
		WfStepmstr step = getStep();
		step.setDealMethod(getStatus());
		step.setName(getName());
		ReportRoute route =  (ReportRoute) createRoute();
		route.setStep(step);
		route.gateway();
		System.out.println(getName() + "：执行成功");
	}

	@Override
	public void showButtons() {
		super.showButtons();
		addButton("CreateButton");
		addButton("SummeryButton");
	}

	public static void main(String[] args) {
		ReportNode0 reportNode0 = new ReportNode0();
		reportNode0.setStatus("PASS");
		reportNode0.excute();
	}
}
