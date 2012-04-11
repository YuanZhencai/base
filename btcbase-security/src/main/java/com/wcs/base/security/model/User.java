package com.wcs.base.security.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;
import com.wcs.base.entity.IdEntity;

/**
 * 用户.
 * 
 * 使用JPA annotation定义ORM关系.
 * 
 * @author chris.guan
 */
@SuppressWarnings("serial")
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "users")
//默认的缓存策略.
public class User extends IdEntity {

	private String loginName;
	private String password;//为简化演示使用明文保存的密码
	private String name;
	private String email;
	private List<Role> groupList = Lists.newArrayList();//有序的关联对象集合

	//字段非空且唯一, 用于提醒Entity使用者及生成DDL.
	@Column(nullable = false, unique = true)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//多对多定义
	@ManyToMany
	//中间表定义,表名采用默认命名规则
	@JoinTable(name = "user_group", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "group_id") })
	//集合按id排序.
	@OrderBy("id")
	public List<Role> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Role> groupList) {
		this.groupList = groupList;
	}

	/**
	 * 用户拥有的权限组名称字符串, 多个权限组名称用','分隔.
	 */
	//非持久化属性.
	@Transient
	public String getGroupNames() {
		StringBuilder str = new StringBuilder();
		for (Role g : groupList){
			str.append(g.getName()).append(", ");
		}
		
		return str.substring(0, str.length()-1);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}