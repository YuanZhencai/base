package com.wcs.base.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

/**
 * 基本的抽象实体父类。
 * 它实现了公共的主键 Id 、创建时间和更新时间。并自动生成创建时间和更新时间。
 * 同时，它还实现了基于主键 Id 的 hashcode 和 equals 方法，可以用来比较两个对象。
 * <p/>
 * 该类实现了一个抽象的方法 getDisplayText() ， 该方法提供了一个描述该类的入口，
 * 用户可以根据自己的要求实现。
 *
 * @author Chris Guan
 * 
 */
@MappedSuperclass
public abstract class BaseEntity extends IdEntity {
	private static final long serialVersionUID = 1L;

    @Column(name = "created_by", length = 30)
	private String createdBy;
    
    @Column(name = "created_datetime")
	private Timestamp createdDatetime;
    
    @Column(name = "defunct_ind")
	private Boolean defunctInd;
    
    @Column(name = "updated_by", length = 30)
	private String updatedBy;
    
    @Column(name = "updated_datetime")
	private Timestamp updatedDatetime;

	
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
	public Timestamp getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	
	public Boolean getDefunctInd() {
		return this.defunctInd;
	}

	public void setDefunctInd(Boolean defunctInd) {
		this.defunctInd = defunctInd;
	}

	
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	public Timestamp getUpdatedDatetime() {
		return this.updatedDatetime;
	}

	public void setUpdatedDatetime(Timestamp updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	@Transient
	public abstract String getDisplayText();
	
	@PrePersist
	public void initTimeStamps() {
		if (createdDatetime == null) {
			Timestamp ts = new Timestamp(new Date().getTime()); 
			createdDatetime = ts;
		}
		updatedDatetime = createdDatetime;
        defunctInd = false;
	}
	
	@PreUpdate
	public void updateTimeStamp() {
		updatedDatetime = new Timestamp(new Date().getTime());
	}


}
