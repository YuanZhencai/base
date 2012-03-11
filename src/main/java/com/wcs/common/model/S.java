package com.wcs.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * The persistent class for the S database table.
 * 
 */
@Entity
public class S extends com.wcs.base.model.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "DEFUNCT_IND")
	private String defunctInd;

	private String kostl;

	private long oid;

	private String stext;

	private String zhrtxxlid;

	private String zhrtxxlms;

	public S() {
	}

	public String getDefunctInd() {
		return this.defunctInd;
	}

	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}

	public String getKostl() {
		return this.kostl;
	}

	public void setKostl(String kostl) {
		this.kostl = kostl;
	}

	public long getOid() {
		return this.oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getStext() {
		return this.stext;
	}

	public void setStext(String stext) {
		this.stext = stext;
	}

	public String getZhrtxxlid() {
		return this.zhrtxxlid;
	}

	public void setZhrtxxlid(String zhrtxxlid) {
		this.zhrtxxlid = zhrtxxlid;
	}

	public String getZhrtxxlms() {
		return this.zhrtxxlms;
	}

	public void setZhrtxxlms(String zhrtxxlms) {
		this.zhrtxxlms = zhrtxxlms;
	}

}