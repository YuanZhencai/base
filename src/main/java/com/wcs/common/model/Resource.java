package com.wcs.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wcs.base.entity.IdEntity;

/**
 * 
 * <p>Project: cmdpms</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chenlong@wcs-global.com">chenlong</a>
 */
@Entity
@Table(name = "resource")
public class Resource extends IdEntity  implements Comparable<Resource>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 父Id*/
	private Long parentId ;
	/** 资源名称*/
	private String name = "";
	/** 资源URL*/
	private String uri = "";
	/** 资源类型*/
	private String type = "";
	/** 是否是菜单*/
	private Boolean ismenu = false;
	/** 资源等级*/
	private String level = "";
	/** 资源编号*/
	private String number = "";
	

	public Resource() {
	}

	public Resource(String level, String name, String url) {
		this.level = level;
		this.name = name;
		this.uri = url;
	}


	@Column(name = "LEVEL")
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "URI")
	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ISMENU",columnDefinition="smallint")
	public Boolean getIsmenu() {
		return ismenu;
	}

	public void setIsmenu(Boolean ismenu) {
		this.ismenu = ismenu;
	}

	@Column(name = "PARENT_ID")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "Number")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	@Override
	@Transient
	public int compareTo(Resource resource) {
	    return this.number.compareTo(resource.getNumber());
	}
}
