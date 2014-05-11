package com.wcs.demo.goc.bridge.workflow.node.report;

import com.wcs.demo.goc.command.Command;
import com.wcs.demo.goc.command.report.Report;
import com.wcs.demo.goc.command.report.ReportInvoker;
import com.wcs.demo.goc.command.report.SingletonReportInvoker;
import com.wcs.demo.goc.command.report.UploadCommand;

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
		System.out.println(getType() + " 通过");
		// 报表
		Report report = getReport();
		ReportInvoker reportInvoker = SingletonReportInvoker.getInstance();
		// 上传命令
		Command command = new UploadCommand(report);
		// 发送上传命令
		reportInvoker.setCommand(command);
		// 执行上传命令
		reportInvoker.excute();
		String absolutePath = report.getAbsolutePath();
		System.out.println("上传了一张报表 ："+absolutePath);
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
