package com.wcs.base.security.model;

import java.io.Serializable;
import javax.persistence.*;

import com.wcs.base.security.model.Rolemstr;

import java.util.Date;


/**
 * The persistent class for the ROLERESOURCE database table.
 * 
 */
@Entity
@Table(name="ROLERESOURCE")
public class Roleresource extends com.wcs.base.entity.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CREATED_BY", length=50)
	private String createdBy;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATETIME")
	private Date createdDatetime;

	@Column(name="DEFUNCT_IND", length=1)
	private String defunctInd;

	@Column(name="UPDATED_BY", length=50)
	private String updatedBy;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATETIME")
	private Date updatedDatetime;

	//bi-directional many-to-one association to Resourcemstr
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="RESOURCEMSTR_ID")
	private Resourcemstr resourcemstr;

	//bi-directional many-to-one association to Rolemstr
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ROLEMSTR_ID")
	private Rolemstr rolemstr;

    public Roleresource() {
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

	public Resourcemstr getResourcemstr() {
		return this.resourcemstr;
	}

	public void setResourcemstr(Resourcemstr resourcemstr) {
		this.resourcemstr = resourcemstr;
	}
	
	public Rolemstr getRolemstr() {
		return this.rolemstr;
	}

	public void setRolemstr(Rolemstr rolemstr) {
		this.rolemstr = rolemstr;
	}
	
}