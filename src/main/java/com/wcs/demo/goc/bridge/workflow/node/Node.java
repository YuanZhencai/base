package com.wcs.demo.goc.bridge.workflow.node;

import java.util.HashSet;
import java.util.Set;


public abstract class Node implements INode {

	private String name;
	private String status;
	private String seqNo;
	
	private String type;
	
	private Set<String> buttons = new HashSet<String>();
	
	public void addButton(String button) {
		buttons.add(button);
	}
	
	public void removeButton(String button) {
		buttons.remove(button);
	}
	
	public void hasButton(String button) {
		buttons.contains(button);
	}
	
	public void clearButtons() {
		buttons.clear();
	}
	
	public void getButtons() {
		for (String button : buttons) {
			System.out.println(name + " 显示 " + button);
		}
	}
	
	@Override
	public void excute() {
		
	}
	
	
	@Override
	public void showButtons() {
		// 清空 按钮
		clearButtons();
	}
	
	public String findSeqNoBy() {
		String f_s_s = type + "_" + seqNo + "_" + status;
		return Enum.valueOf(SeqNo.class, f_s_s).getValue();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	
}
