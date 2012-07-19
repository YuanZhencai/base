package com.wcs.base.security.model.master;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USR database table.
 * 
 */
@Entity
@Table(name="USR")
public class Usr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=200)
	private String celno;

	@Column(name="DEFUNCT_IND", length=1)
	private String defunctInd;

	@Column(name="DESC", length=200)
	private String desc;

	@Column(length=200)
	private String email;

	@Column(length=50)
	private String id;

	@Column(length=200)
	private String name;

	@Column(name="SEARCH_PHRASE", length=200)
	private String searchPhrase;

	@Column(length=200)
	private String telno;

    public Usr() {
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

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSearchPhrase() {
		return this.searchPhrase;
	}

	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public String getTelno() {
		return this.telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

}