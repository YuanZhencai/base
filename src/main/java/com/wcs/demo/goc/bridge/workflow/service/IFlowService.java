/** * IWorkflow.java 
* Created on 2014年5月16日 上午10:49:03 
*/

package com.wcs.demo.goc.bridge.workflow.service;

import java.util.Map;

import javax.ejb.Local;

import com.wcs.demo.goc.bridge.workflow.Workflow;
import com.wcs.demo.goc.bridge.workflow.model.WfInstancemstr;
import com.wcs.demo.goc.bridge.workflow.model.WfStepmstr;
import com.wcs.demo.goc.bridge.workflow.node.Node;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: IWorkflow.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

@Local
public interface IFlowService {
	public WfInstancemstr createFlow();
	public WfStepmstr doDispatch();
}
