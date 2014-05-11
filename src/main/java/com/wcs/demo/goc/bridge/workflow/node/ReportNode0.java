package com.wcs.demo.goc.bridge.workflow.node;

import java.util.ArrayList;
import java.util.List;

import com.wcs.common.model.Resourcemstr;
import com.wcs.demo.goc.command.Command;
import com.wcs.demo.goc.command.report.Report;
import com.wcs.demo.goc.command.report.ReportInvoker;
import com.wcs.demo.goc.command.report.ResourceReport;
import com.wcs.demo.goc.command.report.SingletonReportInvoker;
import com.wcs.demo.goc.command.report.SummeryCommand;

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
		super.excute();
		System.out.println(getType() + " 创建");
		List<Resourcemstr> data = new ArrayList<Resourcemstr>();
		Resourcemstr r = null;
		for (int i = 0; i < 10; i++) {
			r = new Resourcemstr();
			r.setName("prant" + i);
			r.setSeqNo(i + "");
			r.setUri("uri" + i);
			data.add(r);
		}
		// 报表
		Report report = new ResourceReport(data);
		// 汇总命令
		Command command = new SummeryCommand(report);
		ReportInvoker reportInvoker = SingletonReportInvoker.getInstance();
		// 发送汇总
		reportInvoker.setCommand(command);
		// 执行汇总
		reportInvoker.excute();
		String absolutePath = report.getAbsolutePath();
		System.out.println("汇总了一张报表 ："+absolutePath);
		setReport(report);
	}

	@Override
	public void showButtons() {
		super.showButtons();
		addButton("CreateButton");
		// 汇总按钮
		addButton("SummeryButton");
	}

}
