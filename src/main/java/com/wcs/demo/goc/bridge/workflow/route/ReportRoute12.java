package com.wcs.demo.goc.bridge.workflow.route;

import com.wcs.demo.goc.command.Command;
import com.wcs.demo.goc.command.report.Report;
import com.wcs.demo.goc.command.report.ReportInvoker;
import com.wcs.demo.goc.command.report.SingletonReportInvoker;
import com.wcs.demo.goc.command.report.UploadCommand;

public class ReportRoute12 extends ReportRoute {

	public ReportRoute12() {
		super("路由：1 → 2", "1", "PASS");
	}

	public ReportRoute12(String start, String status) {
		super("路由：1 → 2", start, status);
	}

	@Override
	public void gateway() {
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
		System.out.println("上传了一张报表 ：" + absolutePath);
	}

}
