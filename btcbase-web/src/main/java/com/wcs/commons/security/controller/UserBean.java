package com.wcs.commons.security.controller;

import java.io.Serializable;
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
import com.wcs.commons.security.service.UserService;
import com.wcs.commons.security.vo.UserVO;

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
    private LazyDataModel<UserVO> lazyModel;
    private User instance = new User(); // 当前角色对象
    

    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
    	logger.info("UserBean=>init()");
        list();
    }

    /**
     * 查询用户
     * @return
     */
    public void list() {
        lazyModel = userService.findUsers(filterMap);
    }

    public LazyDataModel<UserVO> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<UserVO> lazyModel) {
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
	
}
