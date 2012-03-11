package com.wcs.common.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the P database table.
 * 
 */
@Entity
public class P extends com.wcs.base.model.IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String bukrs;

	private String celno;

	@Column(name="DEFUNCT_IND")
	private String defunctInd;

	private String email;

	private String gesch;

	private String icnum;

	private String kostl;

	private String nachn;

	private String name2;

	private String telno;

    public P() {
    }

	public String getBukrs() {
		return this.bukrs;
	}

	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}

	public String getCelno() {
		return this.celno;
	}

	public void setCelno(String celno) {
		this.celno = celno;
	}

	public String getDefunctInd() {
		return this.defunctInd;
	}

	public void setDefunctInd(String defunctInd) {
		this.defunctInd = defunctInd;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGesch() {
		return this.gesch;
	}

	public void setGesch(String gesch) {
		this.gesch = gesch;
	}

	public String getIcnum() {
		return this.icnum;
	}

	public void setIcnum(String icnum) {
		this.icnum = icnum;
	}

	public String getKostl() {
		return this.kostl;
	}

	public void setKostl(String kostl) {
		this.kostl = kostl;
	}

	public String getNachn() {
		return this.nachn;
	}

	public void setNachn(String nachn) {
		this.nachn = nachn;
	}

	public String getName2() {
		return this.name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getTelno() {
		return this.telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

}