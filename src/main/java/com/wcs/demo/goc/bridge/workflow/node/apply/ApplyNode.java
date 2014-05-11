package com.wcs.demo.goc.bridge.workflow.node.apply;

import com.wcs.demo.goc.bridge.workflow.node.Node;

public class ApplyNode extends Node {
	
	public ApplyNode(String name, String seqNo) {
		setType("Apply");
		setName(name);
		setSeqNo(seqNo);
	}

	@Override
	public void excute() {
		// save data to db
		// ...
		
		// find next seq no
		String nextSeqNo = findSeqNoBy();
		setSeqNo(nextSeqNo);
	}

	@Override
	public void showButtons() {
		super.showButtons();
		// 显示关闭按钮
		addButton("CloseButton");
	}
	
	

}
