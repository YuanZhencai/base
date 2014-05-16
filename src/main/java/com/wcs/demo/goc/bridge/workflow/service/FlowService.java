/** * FlowService.java 
* Created on 2014年5月16日 上午10:51:21 
*/

package com.wcs.demo.goc.bridge.workflow.service;

import java.util.Map;
import java.util.UUID;

import com.wcs.demo.goc.bridge.workflow.model.WfInstancemstr;
import com.wcs.demo.goc.bridge.workflow.model.WfStepmstr;
import com.wcs.demo.goc.bridge.workflow.node.Node;


/** 
 * <p>Project: btcbase</p> 
 * <p>Title: FlowService.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public abstract class FlowService implements IFlowService {

	@Override
	public WfInstancemstr createFlow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WfStepmstr doDispatch() {
		// TODO Auto-generated method stub
		return null;
	}

	public String startProcess(String workflowName, Map<String, Object> dataMap) {
		for (String key : dataMap.keySet()) {
			System.out.println("[key]" + dataMap.get(key));
		}
		String no = UUID.randomUUID().toString().toUpperCase();
		System.out.println(workflowName + " 创建成功，流程单号:" + no);
		return no;
	}
	
	
	public boolean completeWorkItem(String workObjectNumber, Map<String, Object> dataMap) {
		for (String key : dataMap.keySet()) {
			System.out.println("[key]" + dataMap.get(key));
		}
		System.out.println( "流程单号:" +workObjectNumber + "执行成功");
		return true;
	}
}
