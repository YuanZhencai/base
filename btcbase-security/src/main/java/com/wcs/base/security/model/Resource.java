package com.wcs.base.security.model;

import java.io.Serializable;
import javax.persistence.*;

import com.wcs.base.entity.IdEntity;


/**
 * The persistent class for the resource database table.
 * 
 */
@Entity
@Table(name="resource")
public class Resource extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="is_leaf")
	private Boolean isLeaf;

	@Column(name="is_menu")
	private Boolean isMenu;

	@Column(name="key_name", nullable=false, length=255)
	private String keyName;
	private Integer level;

	@Column(length=40)
	private String name;

	@Column(length=255)
	private String number;

	@Column(name="parent_id")
	private Long parentId;

	@Column(length=100)
	private String url;

    public Resource() {
    }



	
	public Boolean getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}


	
	public Boolean getIsMenu() {
		return this.isMenu;
	}

	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}


	
	public String getKeyName() {
		return this.keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}


	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}


	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}