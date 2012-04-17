package com.wcs.base.security.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.wcs.base.entity.IdEntity;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
public class User extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String email;
	private String loginName;
	private String name;
	private String password;
	private List<Role> roleList = new ArrayList<Role>();// 有序的关联对象集合

	public User() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginname) {
		this.loginName = loginname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// 多对多定义
	@ManyToMany
	// 中间表定义,表名采用默认命名规则
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	// 集合按id排序.
	@OrderBy("id")
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}