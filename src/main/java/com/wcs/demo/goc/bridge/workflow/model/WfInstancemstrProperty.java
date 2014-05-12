package com.wcs.demo.goc.bridge.workflow.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WF_INSTANCEMSTR_PROPERTY database table.
 * 
 */
@Entity
@Table(name="WF_INSTANCEMSTR_PROPERTY")
@NamedQuery(name="WfInstancemstrProperty.findAll", query="SELECT w FROM WfInstancemstrProperty w")
public class WfInstancemstrProperty extends com.wcs.base.model.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	@Column(name="\"VALUE\"")
	private String value;

	//bi-directional many-to-one association to WfInstancemstr
	@ManyToOne
	@JoinColumn(name="WF_INSTANCEMSTR_ID")
	private WfInstancemstr wfInstancemstr;

	public WfInstancemstrProperty() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public WfInstancemstr getWfInstancemstr() {
		return this.wfInstancemstr;
	}

	public void setWfInstancemstr(WfInstancemstr wfInstancemstr) {
		this.wfInstancemstr = wfInstancemstr;
	}

}