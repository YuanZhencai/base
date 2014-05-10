package com.wcs.demo.goc.bridge.workflow;

import org.junit.Test;

import com.wcs.demo.goc.bridge.workflow.node.CheckDocNode;
import com.wcs.demo.goc.bridge.workflow.node.Node;
import com.wcs.demo.goc.bridge.workflow.route.DocRoute01;
import com.wcs.demo.goc.bridge.workflow.route.DocRoute12;
import com.wcs.demo.goc.bridge.workflow.route.ReportRoute01;
import com.wcs.demo.goc.bridge.workflow.route.ReportRoute12;
import com.wcs.demo.goc.bridge.workflow.route.Route;

public class WorkflowTest {

	@Test
	public void testDocflow() {
		// 文档流程
		Workflow docFlow = new DocFlow();
		// 路由：节点0 → 节点1 即 节点 0 通过
		Route docRoute01 = new DocRoute01("0", "PASS");
		// 流程将要从 节点 0 执行到 节点 1
		docFlow.setRoute(docRoute01);
		// 流程执行
		docFlow.dispatch();
		
		// 路由：节点1 → 节点2  即 节点 1 通过
		Route docRoute12 = new DocRoute12("1", "PASS");
		// 流程将要从 节点 1 执行到 节点 2
		docFlow.setRoute(docRoute12);
		// 流程执行
		docFlow.dispatch();
		
	}
	
	@Test
	public void testReportflow() {
		// 文档流程
		Workflow reportFlow = new ReportFlow();
		// 路由：节点0 → 节点1 即 节点 0 通过
		Route reportRoute01 = new ReportRoute01("0", "PASS");
		// 流程将要从 节点 0 执行到 节点 1
		reportFlow.setRoute(reportRoute01);
		// 流程执行
		reportFlow.dispatch();
		
		// 路由：节点1 → 节点2  即 节点 1 通过
		Route reportRoute12 = new ReportRoute12("1", "PASS");
		// 流程将要从 节点 1 执行到 节点 2
		reportFlow.setRoute(reportRoute12);
		// 流程执行
		reportFlow.dispatch();
		
	}
	
	@Test
	public void testCheckDocFlow() {
		// 文档流程
		Workflow checkDocFlow = new CheckDocFlow();
		// 节点0
		Node node = new CheckDocNode("节点0", "0");
		// 节点0  通过
		node.setStatus("PASS");
		
		// 流程将要从 节点 0 执行到 节点 1
		checkDocFlow.setNode(node);
		// 流程执行
		checkDocFlow.dispatch();
		
		// 节点1  通过
		node.setStatus("PASS");
		// 流程执行
		checkDocFlow.dispatch();
		
		
		
	}

}
