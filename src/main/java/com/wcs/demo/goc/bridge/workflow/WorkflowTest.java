package com.wcs.demo.goc.bridge.workflow;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.wcs.common.consts.DictConsts;
import com.wcs.demo.goc.bridge.workflow.model.WfInstancemstr;
import com.wcs.demo.goc.bridge.workflow.model.WfStepmstr;
import com.wcs.demo.goc.bridge.workflow.node.Node;
import com.wcs.demo.goc.bridge.workflow.node.apply.ApplyNode0;
import com.wcs.demo.goc.bridge.workflow.node.checkdoc.CheckDocNode;
import com.wcs.demo.goc.bridge.workflow.node.report.ReportNode;
import com.wcs.demo.goc.bridge.workflow.node.report.ReportNode0;
import com.wcs.demo.goc.bridge.workflow.route.DocRoute01;
import com.wcs.demo.goc.bridge.workflow.route.DocRoute12;
import com.wcs.demo.goc.bridge.workflow.route.Route;
import com.wcs.demo.goc.command.report.Report;
import com.wcs.demo.goc.singleton.DBConnection;

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

		// 路由：节点1 → 节点2 即 节点 1 通过
		Route docRoute12 = new DocRoute12("1", "PASS");
		// 流程将要从 节点 1 执行到 节点 2
		docFlow.setRoute(docRoute12);
		// 流程执行
		docFlow.dispatch();

	}

	@Test
	public void testReportflow() {
		// 报表流程
		ReportFlow reportFlow = new ReportFlow();
		// 汇总报表节点
		Node node = new ReportNode0();
		// 流程 在 汇总报表节点
		reportFlow.setNode(node);
		// 当前节点 汇总报表节点
		ReportNode currentNode = (ReportNode) reportFlow.getNode();
		// 当前节点 即 汇总报表节点 显示的 按钮
		currentNode.getButtons();
		// 当前节点 即 汇总报表节点 通过
		currentNode.setStatus("PASS");
		// 流程执行
		reportFlow.dispatch();
		// 通过后会 汇总一张报表
		Report report = reportFlow.getReport();
		// 当前节点为 上传报表节点
		currentNode = (ReportNode) reportFlow.getNode();

		// 当前节点 即 上传报表节点 显示的 按钮
		currentNode.getButtons();
		// 当前节点 即 上传报表节点通过
		currentNode.setStatus("PASS");
		// 通过后上传 汇总的报表
		currentNode.setReport(report);
		// 流程执行
		reportFlow.dispatch();

		// 当前节点为 完成报表节点
		currentNode = (ReportNode) reportFlow.getNode();
		// 当前节点 即 完成报表节点 显示的 按钮
		currentNode.getButtons();
		currentNode.setStatus("COMPLETE");
		// 流程完成
		reportFlow.dispatch();

	}

	@Test
	public void testCheckDocFlow() {
		// 文档检入流程
		Workflow checkDocFlow = new CheckDocFlow();
		// 节点0
		Node node = new CheckDocNode("节点0", "0");
		// 节点0 通过
		node.setStatus("PASS");

		// 流程将要从 节点 0 执行到 节点 1
		checkDocFlow.setNode(node);
		// 流程执行
		checkDocFlow.dispatch();

		// 节点1 通过
		node.setStatus("PASS");
		// 流程执行
		checkDocFlow.dispatch();

	}

	@Test
	public void testApplyFlow() {
		// 申请流程
		Workflow applyFlow = new ApplyFlow();
		// 节点0
		Node node = new ApplyNode0("节点0");
		// 流程 在 节点 0
		applyFlow.setNode(node);
		// 当前节点 节点0
		Node currentNode = applyFlow.getNode();
		// 当前节点 即节点0 显示的 按钮
		currentNode.getButtons();
		// 当前节点 即 节点0 通过
		currentNode.setStatus("PASS");

		// 流程执行
		applyFlow.dispatch();
		// 当前节点为 节点1
		currentNode = applyFlow.getNode();

		// 当前节点 即节点1 显示的 按钮
		currentNode.getButtons();
		// 当前节点 即 节点1 通过
		currentNode.setStatus("PASS");
		// 流程执行
		applyFlow.dispatch();

		// 当前节点为 节点2
		currentNode = applyFlow.getNode();
		// 当前节点 即节点2 显示的 按钮
		currentNode.getButtons();

	}
	
	public WfInstancemstr getWfInstancemstr() {
		WfInstancemstr wf = new WfInstancemstr();
		String username = UUID.randomUUID().toString();
		Date date = new Date();
		wf.setCreatedBy(username);
		wf.setCreatedDatetime(date);
		wf.setDefunctInd("N");
		wf.setRequestBy(username);
		wf.setUpdatedBy(username);
		wf.setUpdatedDatetime(date);
		wf.setSubmitDatetime(date);
		return wf;
	}
	
	public WfStepmstr getWfStepmstr() {
		WfStepmstr step = new WfStepmstr();
		String username = UUID.randomUUID().toString();
		Date date = new Date();
		step.setCreatedBy(username);
		step.setCreatedDatetime(date);
		step.setDefunctInd("N");
		step.setUpdatedBy(username);
		step.setUpdatedDatetime(date);
		step.setCompletedDatetime(date);
		return step;
	}
	
	public WfStepmstr saveReportflow() {
		WfInstancemstr wf = getWfInstancemstr();
		wf.setType("报送报表流程");
		wf.setStatus("新建");
		wf.setNo("未启动");
		wf.put(DictConsts.TIH_TAX_WORKFLOWIMPORTANCE, "重要");
		wf.put(DictConsts.TIH_TAX_WORKFLOWURGENCY, "紧急");
		wf.put("company", "wcs");
		WfStepmstr step = getWfStepmstr();
		step.setName("报表流程创建节点");
		step.setDealMethod("保存");
		step.setChargedBy("");
		step.setCode("");
		step.setFromStepId(0);
		step.put("remark", "报表说明......");
		wf.addWfStepmstr(step);
		EntityManager em = DBConnection.getInstance().getEntityManager("sample_db2");
		em.getTransaction().begin();
		em.persist(wf);
		em.getTransaction().commit();
		return step;
	}
	
	@Test
	public void testReportflow_save() {
		saveReportflow();
	}
	
	public void createReportflow(WfStepmstr step) {
		EntityManager em = DBConnection.getInstance().getEntityManager("sample_db2");
		em.getTransaction().begin();
		em.merge(step.getWfInstancemstr());
		em.getTransaction().commit();
	}
	
	@Test
	public void testReportflow_1() {
		WfStepmstr step = saveReportflow();
		step.setDealMethod("CREATE");
		step.setChargedBy("汇总报表人");
		step.getWfInstancemstr().setNo("WREWR97RWE8R7E897R8EW6R7EWR09E8");
		createReportflow(step);
	}
	
	@Test
	public void testReportflow_create() {
		// 报表流程
		ReportFlow reportFlow = new ReportFlow();
		Node node = new ReportNode0("报表流程创建节点");
		reportFlow.setNode(node);
		ReportNode currentNode = (ReportNode) reportFlow.getNode();
		currentNode.getButtons();
		
		// 先保存业务数据
		WfStepmstr step = saveReportflow();
		step.setChargedBy("汇总报表人");
		// 创建流程节点的业务数据
		currentNode.setStep(step);
		// 创建节点 CREATE
		currentNode.setStatus("CREATE");
		// 流程执行
		reportFlow.dispatch();
		// 创建流程数据
		createReportflow(step);
		
		// 当前节点为 上传报表节点
		currentNode = (ReportNode) reportFlow.getNode();

		// 当前节点 即 上传报表节点 显示的 按钮
		currentNode.getButtons();

	}

}