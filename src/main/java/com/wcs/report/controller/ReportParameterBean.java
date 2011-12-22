/** * ReportParameterBean.java 
* Created on 2011-12-6 下午1:47:10 
*/

package com.wcs.report.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import com.wcs.base.controller.ViewBaseBean;
import com.wcs.common.service.DictService;
import com.wcs.report.model.ReportMstr;
import com.wcs.report.model.ReportParameter;
import com.wcs.report.service.ReportParameterService;

/** 
* <p>Project: btcbase</p> 
* <p>Title: ReportParameterBean.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2011.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yangshiyun@wcs-global.com">Yang Shiyun</a> 
*/

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ReportParameterBean extends ViewBaseBean<ReportParameter>  {
	@Inject
	ReportParameterService reportParameterService;
	@Inject
	public DictService dictService;
	private List<ReportParameter> reportParameterList = new ArrayList<ReportParameter>();
	private boolean disable;
	
	private List<SelectItem> controlTypeList = new ArrayList<SelectItem>();    // 控件类型下拉框
	private List<SelectItem> javaDataTypeList = new ArrayList<SelectItem>();    // 控件类型下拉框
	
	/**
	 * 构造函数
	 */
	public ReportParameterBean() {
		
	}
	
	/**
	 * 构造方法执行后会自动执行此方法
	 */
	@SuppressWarnings("unused")
	@PostConstruct
	private void postConstruct() {
		this.initSelectItem();
	}
	
	/**
     * 初始化报表分类、报表模式下拉框
     */
    public void initSelectItem() {
    	this.setControlTypeList(dictService.findWithSelectItem("CONT"));
    	this.setJavaDataTypeList(dictService.findWithSelectItem("JADT"));
    }
	
	/**
	 * 保存报表参数信息
	 * @see com.wcs.base.controller.ViewBaseBean#save()
	 */
	public void saveParameter() {
		super.saveEntity();
		this.findReportParameterList();
		this.clearParameter();
		this.setCheckBoxDefaultValue();
		this.setControlStatus();
	}
	
	/**
	 * 删除报表参数信息
	 */
	public void deleteParameter() {
		super.deleteEntity();
		this.findReportParameterList();
		this.clearParameter();
		this.setControlStatus();
	}
	
    /**
     * 清空报表参数信息
     */
	public void clearParameter() {
		ReportMstr reportMstr = this.getInstance().getReportMstr();	
		this.setInstance(null);
		this.getInstance().setReportMstr(reportMstr);
		this.setControlStatus();
	}
	
	/**
	 * 查询报表参数列表
	 */
	public void findReportParameterList() {
		ReportMstr rm = this.getInstance().getReportMstr();
		if(rm == null) {
			return;
		} else {
			Long reportMstrId = rm.getId();
			reportParameterList = reportParameterService.findReportParameterList(reportMstrId);
			//设置复选框默认值和按钮状态
			this.setCheckBoxDefaultValue();
			this.setControlStatus();
		}
	}
	
	/**
	 * 设置checkbox默认值
	 */
	public void setCheckBoxDefaultValue() {
		 ReportParameter reportParameter = this.getInstance();
		 reportParameter.setDisplayInd(true);
		 reportParameter.setEditInd(true);
		 reportParameter.setMandatoryInd(true);
	}
	
	/**
	 * 当参数列表行选中，然后调用此单击方法
	 * @param event
	 */
	public void onRowSelect(SelectEvent event) { 
		ReportParameter reportParameter = (ReportParameter)event.getObject();
		this.setInstance(reportParameter);
		this.setControlStatus();
	}
	
	/**
	 * 判断按钮是否显示
	 */
	public void setControlStatus() {
		Long id = this.getInstance().getId();
		if(id != null) {
			this.setDisable(true);
		} else {
			this.setDisable(false);
		}
		
	}

	//-------------------- setter & getter --------------------//
	
	public ReportParameterService getReportParameterService() {
		return reportParameterService;
	}

	public void setReportParameterService(ReportParameterService reportParameterService) {
		this.reportParameterService = reportParameterService;
	}

	public List<ReportParameter> getReportParameterList() {
		return reportParameterList;
	}

	public void setReportParameterList(List<ReportParameter> reportParameterList) {
		this.reportParameterList = reportParameterList;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public List<SelectItem> getControlTypeList() {
		return controlTypeList;
	}

	public void setControlTypeList(List<SelectItem> controlTypeList) {
		this.controlTypeList = controlTypeList;
	}

	public List<SelectItem> getJavaDataTypeList() {
		return javaDataTypeList;
	}

	public void setJavaDataTypeList(List<SelectItem> javaDataTypeList) {
		this.javaDataTypeList = javaDataTypeList;
	}

}
