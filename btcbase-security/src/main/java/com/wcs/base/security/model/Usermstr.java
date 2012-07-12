package com.wcs.base.security.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USERMSTR database table.
 * 
 */
@Entity
@Table(name="USERMSTR")
public class Usermstr extends com.wcs.base.entity.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AD_ACCOUNT", length=50)
	private String adAccount;

	@Column(name="CREATED_BY", nullable=false, length=50)
	private String createdBy;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATETIME")
	private Date createdDatetime;

	@Column(name="DEFUNCT_IND", nullable=false, length=1)
	private String defunctInd;

	@Column(length=20)
	private String pernr;

	@Column(name="UPDATED_BY", nullable=false, length=50)
	private String updatedBy;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATETIME")
	private Date updatedDatetime;

	//bi-directional many-to-one association to Userrole
	@OneToMany(mappedBy="usermstr",fetch=FetchType.EAGER)
	private List<Userrole> userroles;

    public Usermstr() {
    }
    
	public String getAdAccount() {
		return this.adAccount;
	}

	public void setAdAccount(String adAccount) {
		this.adAccount = adAccount;
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

	public String getPernr() {
		return this.pernr;
	}

	public void setPernr(String pernr) {
		this.pernr = pernr;
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

	public List<Userrole> getUserroles() {
		return this.userroles;
	}

	public void setUserroles(List<Userrole> userroles) {
		this.userroles = userroles;
	}
	
}