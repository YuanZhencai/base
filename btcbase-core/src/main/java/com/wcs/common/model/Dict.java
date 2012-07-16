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

	private String codeCat;
	private String codeKey;
	private String codeVal;
	private String createdBy;
	private Date createdDatetime;
	private String defunctInd;
	private String lang;
	private String remarks;
	private long seqNo;
	private String sysInd;
	private String updatedBy;
	private Date updatedDatetime;

    public Dict() {
    }

    @Column(name="CODE_CAT", nullable=false, length=50)
    public String getCodeCat() {
		return this.codeCat;
	}

	public void setCodeCat(String codeCat) {
		this.codeCat = codeCat;
	}

    @Column(name="CODE_KEY", nullable=false, length=50)
    public String getCodeKey() {
		return this.codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

    @Column(name="CODE_VAL", nullable=false, length=100)
    public String getCodeVal() {
		return this.codeVal;
	}

	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}

    @Column(name="CREATED_BY", nullable=false, length=50)
    public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATETIME")
	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

    @Column(name="DEFUNCT_IND", nullable=false, length=1)
    public String getDefunctInd() {
		return this.defunctInd;
	}

	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}

    @Column(nullable=false, length=5)
    public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

    @Column(length=200)
    public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    @Column(name="SEQ_NO")
    public long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

    @Column(name="SYS_IND", nullable=false, length=1)
    public String getSysInd() {
		return this.sysInd;
	}

	public void setSysInd(String sysInd) {
		this.sysInd = sysInd;
	}

    @Column(name="UPDATED_BY", nullable=false, length=50)
    public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="UPDATED_DATETIME")
    public Date getUpdatedDatetime() {
		return this.updatedDatetime;
	}

	public void setUpdatedDatetime(Date updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

}