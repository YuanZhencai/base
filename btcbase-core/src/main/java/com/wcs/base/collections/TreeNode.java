package com.wcs.base.collections;


import java.util.List;

public interface TreeNode {
		
	public String getType();

	public Object getData();
	
	public List<TreeNode> getChildren();
	
	public TreeNode getParent();
	
	public void setParent(TreeNode treeNode);

	public boolean isExpanded();
	
	public void setExpanded(boolean expanded);

	public int getChildCount();
	
	public boolean isLeaf();

    public boolean isSelected();

    public void setSelected(boolean value);
    
    public boolean isSelectable();
	
	public void setSelectable(boolean selectable);
}