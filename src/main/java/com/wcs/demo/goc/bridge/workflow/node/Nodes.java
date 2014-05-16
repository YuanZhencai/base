/** * Nodes.java 
* Created on 2014年5月16日 下午5:56:09 
*/

package com.wcs.demo.goc.bridge.workflow.node;

/** 
 * <p>Project: btcbase</p> 
 * <p>Title: Nodes.java</p> 
 * <p>Description: </p> 
 * <p>Copyright (c) 2014 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yuanzhencai@wcs-global.com">Yuan</a>
 */

public class Nodes {
	
	private String flowType;
	private String seqNo; 
	private String status;
	
	public Nodes(String flowType, String seqNo, String status) {
		this.flowType = flowType;
		this.seqNo = seqNo;
		this.status = status;
	}
	
	public Node getNode() {
		String f_s_s = flowType + "_" + seqNo + "_" + status;
		return Enum.valueOf(SeqNode.class, f_s_s).getValue();
	}
}
