package com.wcs.demo.goc.bridge.workflow.route;

import java.util.ArrayList;
import java.util.List;

import com.wcs.common.model.Resourcemstr;
import com.wcs.demo.goc.command.Command;
import com.wcs.demo.goc.command.report.Report;
import com.wcs.demo.goc.command.report.ReportInvoker;
import com.wcs.demo.goc.command.report.ResourceReport;
import com.wcs.demo.goc.command.report.SingletonReportInvoker;
import com.wcs.demo.goc.command.report.SummeryCommand;

public class ReportRoute01 extends ReportRoute {
	
	public ReportRoute01() {
		super("路由：0 → 1","0","PASS");
	}

	public ReportRoute01(String start, String status) {
		super("路由：0 → 1",start,status);
	}
	
	@Override
	public void gateway() {
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

}
