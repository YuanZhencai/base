package com.wcs.base.security.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ROLEMSTR database table.
 * 
 */
@Entity
@Table(name="ROLEMSTR")
public class Rolemstr extends com.wcs.base.entity.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable=false, length=50)
	private String code;

	@Column(name="CREATED_BY", nullable=false, length=50)
	private String createdBy;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATETIME")
	private Date createdDatetime;

	@Column(name="DEFUNCT_IND", nullable=false, length=1)
	private String defunctInd;

	@Column(name="DESC", length=50)
	private String desc;

	@Column(nullable=false, length=20)
	private String name;

	@Column(name="UPDATED_BY", nullable=false, length=50)
	private String updatedBy;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATETIME")
	private Date updatedDatetime;

	//bi-directional many-to-one association to Roleresource
	@OneToMany(mappedBy="rolemstr", fetch=FetchType.EAGER)
	private List<Roleresource> roleresources;

	//bi-directional many-to-one association to Userrole
	@OneToMany(mappedBy="rolemstr", fetch=FetchType.EAGER)
	private List<Userrole> userroles;

    public Rolemstr() {
    }

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public List<Roleresource> getRoleresources() {
		return this.roleresources;
	}

	public void setRoleresources(List<Roleresource> roleresources) {
		this.roleresources = roleresources;
	}
	
	public List<Userrole> getUserroles() {
		return this.userroles;
	}

	public void setUserroles(List<Userrole> userroles) {
		this.userroles = userroles;
	}
	
}