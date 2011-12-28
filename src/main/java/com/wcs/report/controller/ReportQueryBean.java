/** * ReportQueryBean.java 
* Created on 2011-12-15 上午10:10:15 
*/

package com.wcs.report.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;
import com.wcs.base.util.JSFUtils;
import com.wcs.base.util.MessageUtils;
import com.wcs.common.model.Dict;
import com.wcs.common.service.DictService;
import com.wcs.report.model.ReportFile;
import com.wcs.report.model.ReportMstr;
import com.wcs.report.model.ReportParameter;
import com.wcs.report.service.ReportFileService;
import com.wcs.report.service.ReportManageService;
import com.wcs.report.service.ReportParameterService;
import com.wcs.report.service.ReportRoleService;

/** 
* <p>Project: btcbase</p> 
* <p>Title: ReportQueryBean.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2011.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yangshiyun@wcs-global.com">Yang Shiyun</a> 
*/

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ReportQueryBean extends ReportBase implements Serializable {
    @Inject
    ReportManageService reportManageService;
    @Inject
    ReportParameterService reportParameterService;
    @Inject
    ReportRoleService reportRoleService;
    @Inject
    ReportFileService reportFileService;
    @Inject
    DictService dictService;

    private TreeNode root; // 资源树
    private TreeNode selectedNode; // 选择的节点

    private Long reportMstrId; // 报表基本信息ID
    List<ReportParameter> reportParameterList = null; // 报表参数列表
    Map<String, Object> reportMap = new HashMap<String, Object>(); // 报表参数map
    
    public ReportQueryBean() {

    }

    @SuppressWarnings("unused")
    @PostConstruct
    private void initConstruct() {
        this.initTreeNode();
    }

    /**
     * 初始化树节点
     */
    private void initTreeNode() {
        root = new ReportTreeNode("Root", null);
        List<Dict> categoryList = reportManageService.getReportCategory();
        for (int i = 0; i < categoryList.size(); i++) {
            ReportTreeNode fnode = new ReportTreeNode(categoryList.get(i).getName(), root);
            // 加载子节点
            List<ReportMstr> reportList = reportManageService.getReportByCategory(categoryList.get(i).getValue());
            for (int j = 0; j < reportList.size(); j++) {
                ReportTreeNode childNode = new ReportTreeNode(reportList.get(j).getReportName(), fnode);
                childNode.setReportMstrId(reportList.get(j).getId());
            }
        }
    }

    /**
     * 树节点点击事件
     * @param event
     */
    public void onNodeSelect(NodeSelectEvent event) {
        ReportTreeNode treeNode = (ReportTreeNode) event.getTreeNode();
        Long rmId = treeNode.getReportMstrId();
        if (rmId == null) { return; }
        List<ReportParameter> parameterList = reportParameterService.findReportParameterList(rmId);
        this.setReportMstrId(rmId);
        this.setReportParameterList(parameterList);

    }

    /**
     * 判断控件类型是否为文本框
     * @param uiType
     * @return
     */
    public boolean uiTypeWenbk(String uiType) {
        if ("CONT001".equals(uiType)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断控件类型是否为时间控件
     * @param uiType
     * @return
     */
    public boolean uiTypeCalendar(String uiType) {
        if ("CONT002".equals(uiType)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 报表预览
     */
    public void preview() {
        /*
         * Map map1 = this.getReportMap(); Date date = (Date) map1.get("productdate"); map1.put("productdate", date);
         */
        ReportFile reportFile = reportFileService.getReportFile(this.getReportMstrId());
        if (reportFile == null) { return; }
        // String fileName = reportFile.getFileName();
        String fileStoreLocation = reportFile.getFileStoreLocation();
        if (fileStoreLocation == null || fileStoreLocation.equals("")) { return; }
        String jasper = fileStoreLocation.replace("jrxml", "jasper");
        String printFile = fileStoreLocation.replace("jrxml", "jrprint");
        super.setupPage(fileStoreLocation, jasper, printFile);
        try {
            // Map<String, Object> map = new HashMap<String, Object>();
            // map.put("productname", "");
            // map.put("productdate", "2011-12-19");
            // super.genneralReport(map);

            super.genneralReport(this.getReportMap());
            JSFUtils.getRequest().setAttribute("exportFlag", 1);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * <p>Description: 导出报表</p>
     */
    public void exportReport() {
        try {
            Object exportFlag = JSFUtils.getRequestParam("exportFlag");
            if(exportFlag != null && !"null".equals(exportFlag)){
                MessageUtils.addErrorMessage("exportMsg", "请先预览报表内容，再进行导出报表");
                return;
            }
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
                    .getResponse();
            this.exportHtml();
            String destFile = this.exportPdf(response);
            File file = new File(destFile);
            if (file.exists()) {
                file.delete();
            }
        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -------------------- setter & getter --------------------//

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public List<ReportParameter> getReportParameterList() {
        return reportParameterList;
    }

    public void setReportParameterList(List<ReportParameter> reportParameterList) {
        this.reportParameterList = reportParameterList;
    }

    public Map<String, Object> getReportMap() {
        return reportMap;
    }

    public void setReportMap(Map<String, Object> reportMap) {
        this.reportMap = reportMap;
    }

    public Long getReportMstrId() {
        return reportMstrId;
    }

    public void setReportMstrId(Long reportMstrId) {
        this.reportMstrId = reportMstrId;
    }

}
