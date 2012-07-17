package com.wcs.base.security.model;

import com.wcs.base.entity.IdEntity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ROLEMSTR database table.
 * 
 */
@Entity
@Table(name="ROLEMSTR")
public class Rolemstr extends IdEntity implements Serializable {
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
	private List<RoleResource> roleresources;

	//bi-directional many-to-one association to Userrole
	@OneToMany(mappedBy="rolemstr", fetch=FetchType.EAGER)
	private List<UserRole> userroles;

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

	public List<RoleResource> getRoleresources() {
		return this.roleresources;
	}

	public void setRoleresources(List<RoleResource> roleresources) {
		this.roleresources = roleresources;
	}
	
	public List<UserRole> getUserroles() {
		return this.userroles;
	}

	public void setUserroles(List<UserRole> userroles) {
		this.userroles = userroles;
	}
	
}