package com.wcs.cr.controller.vo;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.wcs.cr.service.BaseCrServiceImp;

/**
 * @author chengchao
 *
 */
@ManagedBean(name="crTreeBean")
@ViewScoped
public class BaseCrTreeBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeNode root;  
    private TreeNode selectedNode;
    
    @EJB
    private BaseCrServiceImp baseCrServiceImp;
  
	public BaseCrTreeBean() {
		
	}
	
	
	/**
	 *  load tree nodes list
	 */
	//@PostConstruct
	public TreeNode loadTreeNodeAction() {
		root = new DefaultTreeNode("Root", null);

		// load rpt file list from crconfig? or datatsource?
		try {
			List<BaseCrVo> baseCrVoList = baseCrServiceImp.getBaseCrVoList();
			System.out.println("==================" + baseCrVoList);
			for (BaseCrVo baseCrVo : baseCrVoList) {
				new DefaultTreeNode(baseCrVo, root);
				System.out.println("==================" + baseCrVo.getName());
			}
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_FATAL, "loadTreeNodeAction fatal",
					e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return root;
	}
	
	/**
	 *  fetch report by selected node
	 */
	public void fetchSelectedNodeAction() {
		if (this.getSelectedNode() != null) {
			BaseCrVo baseCrVo = (BaseCrVo) this.getSelectedNode().getData();
			System.out.println("========fetchSelectedNodeAction:"+baseCrVo.getName());
			try {
				BaseCrVo baseCrVo_source = baseCrServiceImp.fetchBaseCrVo(baseCrVo
						.getName());
				((DefaultTreeNode) this.getSelectedNode())
						.setData(baseCrVo_source);
			} catch (Exception e) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_FATAL,
						"fetchSelectedNodeAction fatal", e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
				e.printStackTrace();
			}
		}
	}
  
    public TreeNode getRoot() {  
        return root;  
    }  
  
    public TreeNode getSelectedNode() {  
        return selectedNode;  
    }  
  
    public void setSelectedNode(TreeNode selectedNode) {  
        this.selectedNode = selectedNode;  
    }  
      
    public void displaySelectedSingle(ActionEvent event) {  
        if(selectedNode != null) {  
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", ((BaseCrVo)(selectedNode.getData())).getName());  
  
            FacesContext.getCurrentInstance().addMessage(null, message);  
        }  
    }  
    
	public String getSeletedRptName() {
		return selectedNode == null ? ""
				: ((BaseCrVo) (selectedNode.getData())).getName();
	}
	
	public void onNodeExpand(NodeExpandEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", ((BaseCrVo)(selectedNode.getData())).getName());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
  
    public void onNodeCollapse(NodeCollapseEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", ((BaseCrVo)(selectedNode.getData())).getName());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
  
    public void onNodeSelect(NodeSelectEvent event) {  
    	
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", ((BaseCrVo)(selectedNode.getData())).getName());  
  
        fetchSelectedNodeAction();
        
        System.out.println("=============onNodeSelect:"+this.getSeletedRptName());
        RequestContext context = RequestContext.getCurrentInstance();  
        context.addCallbackParam("selectedName", ((BaseCrVo)(selectedNode.getData())).getName());  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
  
    public void onNodeUnselect(NodeUnselectEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", ((BaseCrVo)(selectedNode.getData())).getName());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
}
