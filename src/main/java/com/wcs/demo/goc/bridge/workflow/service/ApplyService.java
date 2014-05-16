/** * ApplyService.java 
* Created on 2014年5月16日 上午11:00:38 
*/

package com.wcs.demo.goc.bridge.workflow.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.wcs.base.service.EntityService;
import com.wcs.demo.goc.bridge.workflow.ApplyFlow;
import com.wcs.demo.goc.bridge.workflow.model.WfInstancemstr;
import com.wcs.demo.goc.bridge.workflow.model.WfStepmstr;
import com.wcs.demo.goc.bridge.workflow.node.Node;
import com.wcs.demo.goc.bridge.workflow.node.apply.ApplyNode0;


/** 
 * <p>Project: btcbase</p> 
 * <p>Title: ApplyService.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */
@Stateless
public class ApplyService extends FlowService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private EntityService entityService;

	@Override
	public WfInstancemstr createFlow() {
		String username = UUID.randomUUID().toString();
		Date date = new Date();
		// init para
		ApplyFlow applyFlow = new ApplyFlow();
		Node applyNode0 = new ApplyNode0("提问流程创建节点");
		applyNode0.setStatus("CREATE");
		applyNode0.setChargedBy("yuanzhencai");
		applyFlow.setNode(applyNode0);
		
		// dispatch
		applyFlow.dispatch();
		Node node = applyFlow.getNode();
		
		node.getButtons();
		WfStepmstr step = node.getStep();
		step.setCreatedBy(username);
		step.setCreatedDatetime(date);
		step.setUpdatedBy(username);
		step.setUpdatedDatetime(date);
		step.setDefunctInd("N");
		
		step.setCompletedDatetime(date);
		step.put("remark", "提问流程创建。");
		step.put("attach", "{" + UUID.randomUUID().toString().toUpperCase() + "}");
		
		// save wf data
		WfInstancemstr wf = step.getWfInstancemstr();
		wf.setCreatedBy(username);
		wf.setCreatedDatetime(date);
		wf.setUpdatedBy(username);
		wf.setUpdatedDatetime(date);
		wf.setDefunctInd("N");
		
		wf.setRequestBy(username);
		wf.put("question", "如何写好代码");
		entityService.create(wf);
		
		//start Process 
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("status", step.getDealMethod());
		dataMap.put(node.getTracker(), step.getChargedBy());
		String no = startProcess(applyFlow.getType(), dataMap);
		
		// update wf data
		wf.setNo(no);
		entityService.update(wf);
		return wf;
	}

	@Override
	public WfStepmstr doDispatch() {
		return super.doDispatch();
	}

}
