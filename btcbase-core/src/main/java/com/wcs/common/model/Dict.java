package com.wcs.common.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the DICT database table.
 * 
 */
@Entity
@Table(name="DICT")
public class Dict extends com.wcs.base.entity.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name="CODE_CAT", nullable=false, length=50)
	private String codeCat;

	@Column(name="CODE_KEY", nullable=false, length=50)
	private String codeKey;

	@Column(name="CODE_VAL", nullable=false, length=100)
	private String codeVal;

	@Column(name="CREATED_BY", nullable=false, length=50)
	private String createdBy;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATETIME")
	private Date createdDatetime;

	@Column(name="DEFUNCT_IND", nullable=false, length=1)
	private String defunctInd;

	@Column(nullable=false, length=5)
	private String lang;

	@Column(length=200)
	private String remarks;

	@Column(name="SEQ_NO")
	private long seqNo;

	@Column(name="SYS_IND", nullable=false, length=1)
	private String sysInd;

	@Column(name="UPDATED_BY", nullable=false, length=50)
	private String updatedBy;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATETIME")
	private Date updatedDatetime;

    public Dict() {
    }

	public String getCodeCat() {
		return this.codeCat;
	}

	public void setCodeCat(String codeCat) {
		this.codeCat = codeCat;
	}

	public String getCodeKey() {
		return this.codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeVal() {
		return this.codeVal;
	}

	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
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

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public String getSysInd() {
		return this.sysInd;
	}

	public void setSysInd(String sysInd) {
		this.sysInd = sysInd;
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

}