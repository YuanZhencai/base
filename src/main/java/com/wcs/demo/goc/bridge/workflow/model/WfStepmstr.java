package com.wcs.demo.goc.bridge.workflow.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The persistent class for the WF_STEPMSTR database table.
 * 
 */
@Entity
@Table(name="WF_STEPMSTR")
@NamedQuery(name="WfStepmstr.findAll", query="SELECT w FROM WfStepmstr w")
public class WfStepmstr extends com.wcs.base.model.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CHARGED_BY")
	private String chargedBy;

	private String code;

	@Column(name="COMPLETED_DATETIME")
	private Date completedDatetime;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATETIME")
	private Date createdDatetime;

	@Column(name="DEAL_METHOD")
	private String dealMethod;

	@Column(name="DEFUNCT_IND")
	private String defunctInd;

	@Column(name="FROM_STEP_ID")
	private long fromStepId;

	private String name;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATETIME")
	private Date updatedDatetime;

	//bi-directional many-to-one association to WfInstancemstr
	@ManyToOne
	@JoinColumn(name="WF_INSTANCEMSTR_ID")
	private WfInstancemstr wfInstancemstr;

	//bi-directional many-to-one association to WfStepmstrProperty
	@OneToMany(mappedBy="wfStepmstr")
	@MapKeyColumn(name = "NAME")
	private Map<String, WfStepmstrProperty> wfStepmstrProperties = new HashMap<String, WfStepmstrProperty>();

	public WfStepmstr() {
	}

	public String getChargedBy() {
		return this.chargedBy;
	}

	public void setChargedBy(String chargedBy) {
		this.chargedBy = chargedBy;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCompletedDatetime() {
		return this.completedDatetime;
	}

	public void setCompletedDatetime(Date completedDatetime) {
		this.completedDatetime = completedDatetime;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getDealMethod() {
		return this.dealMethod;
	}

	public void setDealMethod(String dealMethod) {
		this.dealMethod = dealMethod;
	}

	public String getDefunctInd() {
		return this.defunctInd;
	}

	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}

	public long getFromStepId() {
		return this.fromStepId;
	}

	public void setFromStepId(long fromStepId) {
		this.fromStepId = fromStepId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDatetime() {
		return this.updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public WfInstancemstr getWfInstancemstr() {
		return this.wfInstancemstr;
	}

	public void setWfInstancemstr(WfInstancemstr wfInstancemstr) {
		this.wfInstancemstr = wfInstancemstr;
	}

	public Map<String, WfStepmstrProperty> getWfStepmstrProperties() {
		return wfStepmstrProperties;
	}

	public void setWfStepmstrProperties(Map<String, WfStepmstrProperty> wfStepmstrProperties) {
		this.wfStepmstrProperties = wfStepmstrProperties;
	}

	public WfStepmstrProperty addWfStepmstrProperty(WfStepmstrProperty wfStepmstrProperty) {
		WfStepmstrProperty stepProperty = getWfStepmstrProperty(wfStepmstrProperty.getName());
		if(stepProperty != null) {
			removeWfStepmstrProperty(stepProperty);
		}
		getWfStepmstrProperties().put(wfStepmstrProperty.getName(), wfStepmstrProperty);
		wfStepmstrProperty.setWfStepmstr(this);
		return wfStepmstrProperty;
	}
	
	public WfStepmstrProperty getWfStepmstrProperty(String name) {
		return getWfStepmstrProperties().get(name);
	}
	
	public WfStepmstrProperty removeWfStepmstrProperty(WfStepmstrProperty wfStepmstrProperty) {
		getWfStepmstrProperties().remove(wfStepmstrProperty.getName());
		wfStepmstrProperty.setWfStepmstr(null);
		return wfStepmstrProperty;
	}

}