package com.wcs.commons.security.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.wcs.commons.security.model.User;
import com.wcs.commons.security.model.master.Person;
import com.wcs.commons.security.service.UserService;

/**
 * 
 * @author Chris Guan
 */
@ManagedBean
@ViewScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(4); // 查询条件Map封装
    private LazyDataModel<User> lazyModel;
    private User instance = new User(); // 当前角色对象
    private List<Person> persons;		// adAccount LIKE对应的person 列表
    
    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
    	logger.info("UserBean=>init()");
        list();
    }

    public void list() {
        lazyModel = userService.findUsers(filterMap);
    }

    public void delete() {
        userService.deleteUser(instance);
        list();
    }
    
    public void toAdd(){
    	
    }
    
    public void add(){
    	
    }
    //--------------------------- setter & getter ----------------------------//
    
    public LazyDataModel<User> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<User> lazyModel) {
        this.lazyModel = lazyModel;
    }

	public Map<String, Object> getFilterMap() {
		return filterMap;
	}

	public void setFilterMap(Map<String, Object> filterMap) {
		this.filterMap = filterMap;
	}

	public User getInstance() {
		return instance;
	}

	public void setInstance(User instance) {
		this.instance = instance;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
}
