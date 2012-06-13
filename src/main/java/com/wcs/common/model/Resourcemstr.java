package com.wcs.common.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the RESOURCEMSTR database table.
 * 
 */
@Entity
@Table(name="RESOURCEMSTR")
public class Resourcemstr extends com.wcs.base.model.IdEntity implements Serializable {
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

	@Column(nullable=false, length=20)
	private String name;

	@Column(name="PARENT_ID")
	private long parentId;

	@Column(name="SEQ_NO", length=255)
	private String seqNo;

	@Column(name="TYPE", nullable=false, length=50)
	private String type;

	@Column(name="UPDATED_BY", nullable=false, length=50)
	private String updatedBy;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATETIME")
	private Date updatedDatetime;

	@Column(length=200)
	private String uri;

	//bi-directional many-to-one association to Roleresource
	@OneToMany(mappedBy="resourcemstr", fetch=FetchType.EAGER)
	private List<Roleresource> roleresources;

    public Resourcemstr() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return this.parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<Roleresource> getRoleresources() {
		return this.roleresources;
	}

	public void setRoleresources(List<Roleresource> roleresources) {
		this.roleresources = roleresources;
	}
	
}