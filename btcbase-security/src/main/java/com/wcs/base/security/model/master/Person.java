package com.wcs.base.security.model.master;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.google.common.collect.Lists;
import com.wcs.base.security.model.Role;


/**
 * The persistent class for the P database table.
 * 
 */
@Entity
@Table(name="P")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, length=20)
	private String id;

	@Column(length=200)
	private String bukrs;

	@Column(length=200)
	private String celno;

	@Column(name="DEFUNCT_IND", length=1)
	private String defunctInd;

	@Column(length=200)
	private String email;

	@Column(length=200)
	private String gesch;

	@Column(length=200)
	private String icnum;

	@Column(length=200)
	private String kostl;

	@Column(length=200)
	private String nachn;

	@Column(length=200)
	private String name2;

	@Column(length=200)
	private String orgeh;

	@Column(length=200)
	private String telno;

	@ManyToMany
	@JoinTable(name = "PS", joinColumns = { @JoinColumn(name = "pid") }, inverseJoinColumns = { @JoinColumn(name = "sid") })
	private List<Station> stationList = Lists.newArrayList();

	//--------------------- setter & getter -------------------//
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getOrgeh() {
		return this.orgeh;
	}

	public void setOrgeh(String orgeh) {
		this.orgeh = orgeh;
	}

	public String getTelno() {
		return this.telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}
	
}