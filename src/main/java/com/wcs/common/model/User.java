package com.wcs.common.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wcs.base.entity.BaseEntity;

/**
 * 
 * <p>Project: cmdpms</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chenlong@wcs-global.com">chenlong</a>
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {
	/** 用户账户*/
	private String userName;
	private String userPass; // 用户密码
	/** 收购点名称*/
	private String purchaseLocName;
	/** 收购方名称*/
	private String purchaseEntityName;
	/** 姓名*/
	private String name;
	/** 手机*/
	private String cellphoneNo;
	/** 联系电话*/
	private String contactTelephoneNo;
	/** 电子邮箱*/
	private String email;
	/** 联系地址*/
	private String contactAddress;
	/** 用户角色*/
	private Set<UserRole> userRoles = new HashSet<UserRole>();

	public User() {
	}

	public User(String userName) {
		this.userName = userName;
	}

	

	@Column(name = "USER_NAME", nullable = false, length = 18)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PURCHASE_LOC_NAME", length = 60)
	public String getPurchaseLocName() {
		return this.purchaseLocName;
	}

	public void setPurchaseLocName(String purchaseLocName) {
		this.purchaseLocName = purchaseLocName;
	}

	@Column(name = "PURCHASE_ENTITY_NAME", length = 60)
	public String getPurchaseEntityName() {
		return this.purchaseEntityName;
	}

	public void setPurchaseEntityName(String purchaseEntityName) {
		this.purchaseEntityName = purchaseEntityName;
	}

	@Column(name = "NAME", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CELLPHONE_NO", length = 30)
	public String getCellphoneNo() {
		return this.cellphoneNo;
	}

	public void setCellphoneNo(String cellphoneNo) {
		this.cellphoneNo = cellphoneNo;
	}

	@Column(name = "CONTACT_TELEPHONE_NO", length = 30)
	public String getContactTelephoneNo() {
		return this.contactTelephoneNo;
	}

	public void setContactTelephoneNo(String contactTelephoneNo) {
		this.contactTelephoneNo = contactTelephoneNo;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "CONTACT_ADDRESS", length = 60)
	public String getContactAddress() {
		return this.contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Column(name = "USER_PASS", nullable = false, length = 20)
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Transient
    @Override
    public String getDisplayText() {
        return null;
    }
}
