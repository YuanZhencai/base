package com.wcs.common.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PS database table.
 * 
 */
@Entity
public class PS extends com.wcs.base.model.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DEFUNCT_IND")
	private String defunctInd;

	private long pid;

	private long sid;

    public PS() {
    }

	public String getDefunctInd() {
		return this.defunctInd;
	}

	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}

	public long getPid() {
		return this.pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getSid() {
		return this.sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

}