package com.wcs.common.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PS database table.
 * 
 */
@Entity
@Table(name="PS")
public class PS implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=20)
	private String id;

	@Column(name="DEFUNCT_IND", nullable=false, length=1)
	private String defunctInd;

	@Column(nullable=false)
	private long pid;

	@Column(nullable=false)
	private long sid;

    public PS() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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