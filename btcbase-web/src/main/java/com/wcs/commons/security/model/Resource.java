package com.wcs.commons.security.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wcs.base.entity.IdEntity;


/**
 * 
 * @author Chris Guan
 *
 */
@Entity
@Table(name="resource")
public class Resource extends IdEntity {
	
    public enum ResourceType {
    	MENU, COMPONENT
    }
    
	@Column(nullable=false, length=50)
	private String code;

	@Column(nullable=false, length=20)
	private String name;
	
	@Column(name="PARENT_ID")
	private Long parentId;	
	
	@Column(name="SEQ_NO", length=255)
	private Short seqNo;
	
	@Column(nullable=false, length=50)
	@Enumerated(EnumType.STRING)
	private ResourceType type;	//MENU,LEAF_MENU,COMPONENT
	
	@Column(length=200)
	private String uri;
	
/*	@OneToMany(mappedBy="resource", fetch=FetchType.EAGER)
	private List<RoleResource> roleResources;*/
	
	@Transient
	private Integer level;

	//--------------------- setter & getter -------------------//
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Short getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Short seqNo) {
		this.seqNo = seqNo;
	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

/*	public List<RoleResource> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(List<RoleResource> roleResources) {
		this.roleResources = roleResources;
	}*/

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}