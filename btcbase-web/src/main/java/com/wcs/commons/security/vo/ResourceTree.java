package com.wcs.commons.security.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wcs.base.collections.GenericTree;
import com.wcs.base.collections.GenericTreeNode;
import com.wcs.commons.security.model.Resource;
/**
 * 
 * @author Chris Guan
 */
public class ResourceTree extends GenericTree<Resource>{
	
    public Resource findResource(Resource res){
    	GenericTreeNode<Resource> node = this.find(res);
    	return node.getData();
    }
    
    public GenericTreeNode<Resource> findByCode(String resCode) {
        GenericTreeNode<Resource> node = null;

        if(this.getRoot() != null) {
            node = findByCode(this.getRoot(), resCode);
        }

        return node;
    }
    
	private GenericTreeNode<Resource> findByCode(GenericTreeNode<Resource> currentNode, String resCode) {
		GenericTreeNode<Resource> returnNode = null;
		int i = 0;

		if (currentNode.getData().getCode().equals(resCode)) {
			returnNode = currentNode;
		}

		else if (currentNode.hasChildren()) {
			i = 0;
			while (returnNode == null && i < currentNode.getChildCount()) {
				returnNode = findByCode(currentNode.getChildAt(i),resCode);
				i++;
			}
		}

		return returnNode;
	}
	
    public GenericTreeNode<Resource> findByUri(String uri) {
        GenericTreeNode<Resource> node = null;

        if(this.getRoot() != null) {
            node = findByUri(this.getRoot(), uri);
        }

        return node;
    }
	
	private GenericTreeNode<Resource> findByUri(GenericTreeNode<Resource> currentNode, String uri) {
		GenericTreeNode<Resource> returnNode = null;
		int i = 0;

		if (uri.equals(currentNode.getData().getUri())) {
			returnNode = currentNode;
		}

		else if (currentNode.hasChildren()) {
			i = 0;
			while (returnNode == null && i < currentNode.getChildCount()) {
				returnNode = findByUri(currentNode.getChildAt(i),uri);
				i++;
			}
		}

		return returnNode;
	}
	
	/**
	 * 
	 * @param currentNode
	 * @return 按照从父到子顺序排列
	 */
	public List<String> findParentChain(GenericTreeNode<Resource> currentNode){
		List<String> chain = new ArrayList<String>();
		
		if (currentNode!=null){
			// 获得父节点列表
			this.findParentChain(chain,currentNode);
			chain.remove(chain.size()-1);  // 去掉 root （Resource(0,'root','root',null) )
			Collections.reverse(chain);
		}
		return chain;
	}
	/**
	 * 
	 * @param chain 输出参数,parent 按照从子到父倒序排列
	 * @param currentNode
	 */
	private void findParentChain(List<String> chain,GenericTreeNode<Resource> currentNode){
		GenericTreeNode<Resource> parent = currentNode.getParent();
		
		if (parent!=null && parent.getData() instanceof Resource){
			chain.add(((Resource)parent.getData()).getCode());
			findParentChain(chain,parent);
		}
	}
	
	/**
	 * 设置当前节点的所有父节点被选中（包括其本身）
	 * @param currentNode 
	 */
	public void setSelectedNode(GenericTreeNode<Resource> currentNode){
		currentNode.setSelected(true);
		GenericTreeNode<Resource> parent = currentNode.getParent();
		
		if (parent!=null){
			setSelectedNode(parent);
		}
	}
	
	public void clearAllSelected(){
		List<GenericTreeNode<Resource>> list = this.build();
		for (GenericTreeNode<Resource> node: list){
			node.setSelected(false);
		}
	}
}
