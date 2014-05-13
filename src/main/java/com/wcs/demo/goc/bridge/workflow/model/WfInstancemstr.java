package com.wcs.demo.goc.bridge.workflow.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The persistent class for the WF_INSTANCEMSTR database table.
 * 
 */
@Entity
@Table(name = "WF_INSTANCEMSTR")
@NamedQuery(name = "WfInstancemstr.findAll", query = "SELECT w FROM WfInstancemstr w")
public class WfInstancemstr extends com.wcs.base.model.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATETIME")
	private Date createdDatetime;

	@Column(name = "DEFUNCT_IND")
	private String defunctInd;

	private String no;

	@Column(name = "REQUEST_BY")
	private String requestBy;

	private String status;

	@Column(name = "SUBMIT_DATETIME")
	private Date submitDatetime;

	private String type;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATETIME")
	private Date updatedDatetime;

	// bi-directional many-to-one association to WfInstancemstrProperty
	@OneToMany(mappedBy = "wfInstancemstr", cascade = CascadeType.ALL)
	@MapKeyColumn(name = "NAME")
	private Map<String, WfInstancemstrProperty> wfInstancemstrProperties = new HashMap<String, WfInstancemstrProperty>();

	// bi-directional many-to-one association to WfStepmstr
	@OneToMany(mappedBy = "wfInstancemstr")
	private List<WfStepmstr> wfStepmstrs;

	public WfInstancemstr() {
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

	public String getDefunctInd() {
		return this.defunctInd;
	}

	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getRequestBy() {
		return this.requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmitDatetime() {
		return this.submitDatetime;
	}

	public void setSubmitDatetime(Date submitDatetime) {
		this.submitDatetime = submitDatetime;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public WfInstancemstrProperty addWfInstancemstrProperty(WfInstancemstrProperty wfInstancemstrProperty) {
		WfInstancemstrProperty instancemstrProperty = getWfInstancemstrProperty(wfInstancemstrProperty.getName());
		if(instancemstrProperty != null) {
			removeWfInstancemstrProperty(instancemstrProperty);
		}
		getWfInstancemstrProperties().put(wfInstancemstrProperty.getName(), wfInstancemstrProperty);
		wfInstancemstrProperty.setWfInstancemstr(this);

		return wfInstancemstrProperty;
	}
	
	public WfInstancemstrProperty getWfInstancemstrProperty(String name) {
		return getWfInstancemstrProperties().get(name);
	}

	public WfInstancemstrProperty removeWfInstancemstrProperty(WfInstancemstrProperty wfInstancemstrProperty) {
		getWfInstancemstrProperties().remove(wfInstancemstrProperty.getName());
		wfInstancemstrProperty.setWfInstancemstr(null);
		return wfInstancemstrProperty;
	}

	public List<WfStepmstr> getWfStepmstrs() {
		return this.wfStepmstrs;
	}

	public void setWfStepmstrs(List<WfStepmstr> wfStepmstrs) {
		this.wfStepmstrs = wfStepmstrs;
	}

	public WfStepmstr addWfStepmstr(WfStepmstr wfStepmstr) {
		getWfStepmstrs().add(wfStepmstr);
		wfStepmstr.setWfInstancemstr(this);

		return wfStepmstr;
	}

	public WfStepmstr removeWfStepmstr(WfStepmstr wfStepmstr) {
		getWfStepmstrs().remove(wfStepmstr);
		wfStepmstr.setWfInstancemstr(null);

		return wfStepmstr;
	}

	public Map<String, WfInstancemstrProperty> getWfInstancemstrProperties() {
		return wfInstancemstrProperties;
	}

	public void setWfInstancemstrProperties(Map<String, WfInstancemstrProperty> wfInstancemstrProperties) {
		this.wfInstancemstrProperties = wfInstancemstrProperties;
	}

}