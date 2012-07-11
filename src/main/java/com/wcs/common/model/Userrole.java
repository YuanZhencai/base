package com.wcs.common.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the USERROLE database table.
 * 
 */
@Entity
@Table(name="USERROLE")
public class Userrole extends com.wcs.base.model.IdEntity implements Serializable {
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

	//bi-directional many-to-one association to Rolemstr
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ROLEMSTR_ID")
	private Rolemstr rolemstr;

	//bi-directional many-to-one association to Usermstr
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USERMSTR_ID")
	private Usermstr usermstr;

    public Userrole() {
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

	public Rolemstr getRolemstr() {
		return this.rolemstr;
	}

	public void setRolemstr(Rolemstr rolemstr) {
		this.rolemstr = rolemstr;
	}
	
	public Usermstr getUsermstr() {
		return this.usermstr;
	}

	public void setUsermstr(Usermstr usermstr) {
		this.usermstr = usermstr;
	}
	
}