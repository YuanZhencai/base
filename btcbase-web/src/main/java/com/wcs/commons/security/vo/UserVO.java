package com.wcs.commons.security.vo;

import java.io.Serializable;

import com.wcs.commons.security.model.User;
import com.wcs.commons.security.model.master.Person;

public class UserVO implements Serializable {
	private static final long serialVersionUID = 7922530371478759792L;
	private User user;
	private Person person;
	
	public UserVO(){}
	
	public UserVO(User user,Person person){
		this.user = user;
		this.person = person;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

}
