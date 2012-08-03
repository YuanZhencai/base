package com.wcs.base.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wcs.base.entity.IdEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_role")
public class UserRole extends IdEntity implements Serializable{
	
	@Column(name="user_id")
	private Long userid;

	@Column(name="role_id")
	private Long roleid;
	
	
	public Long getUserid() {
		return userid;
	}
	
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	
	public Long getRoleid() {
		return roleid;
	}
	
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	

}
