package com.wcs.base.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wcs.base.entity.IdEntity;

/**
 * The persistent class for the resource database table.
 * 
 */
@Entity
@Table(name = "resource")
public class Resource extends IdEntity {
	private static final long serialVersionUID = 1L;

	private Boolean isLeaf;
    private Boolean isMenu;
	private String keyName;
	private Integer level;
	private String name;
	private String number;
	private Long parentId;
	private String url;

	public Resource() {
	}

    @Column(name = "is_leaf")
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

    @Column(name = "key_name", nullable = false, length = 255)
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
    @Column(length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
    @Column(length = 255)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

    @Column(name = "parent_id")
    public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
    @Column(length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    @Column(name = "is_menu")
	public Boolean getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Boolean menu) {
		this.isMenu = menu;
	}
	
	

}