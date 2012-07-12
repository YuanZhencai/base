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

	private String createdBy;
	private Timestamp createdDatetime;
	private Boolean defunctInd;
	private String updatedBy;
	private Timestamp updatedDatetime;

	@Column(name = "created_by", length = 30)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_datetime")
	public Timestamp getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	@Column(name = "defunct_ind")
	public Boolean getDefunctInd() {
		return this.defunctInd;
	}

	public void setDefunctInd(Boolean defunctInd) {
		this.defunctInd = defunctInd;
	}

	@Column(name = "updated_by", length = 30)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updated_datetime")
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
