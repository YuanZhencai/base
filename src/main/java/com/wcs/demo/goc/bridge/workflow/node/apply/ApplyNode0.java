package com.wcs.demo.goc.bridge.workflow.node.apply;

import java.util.Date;

import com.wcs.demo.goc.bridge.workflow.model.WfInstancemstr;
import com.wcs.demo.goc.bridge.workflow.model.WfStepmstr;

public class ApplyNode0 extends ApplyNode {

	public ApplyNode0() {
		super("节点0", "0");
		this.showButtons();
	}

	public ApplyNode0(String name) {
		super(name, "0");
	}

	@Override
	public void excute() {

		System.out.println(getType() + " 创建");
		// save the create wf data to db
		WfInstancemstr wf = new WfInstancemstr();
		wf.setNo("未启动");
		wf.setStatus("新建");
		wf.setSubmitDatetime(new Date());
		wf.setType(getType());
		WfStepmstr step = new WfStepmstr();
		step.setName(getName());
		step.setChargedBy(getChargedBy());
		step.setFromStepId(Long.valueOf(getSeqNo()));
		step.setDealMethod(getStatus());
		step.setCode(getSeqNo());
		wf.addWfStepmstr(step);
		setStep(step);
	}

	@Override
	public void showButtons() {
		super.showButtons();
		addButton("CreateButton");
	}

}
