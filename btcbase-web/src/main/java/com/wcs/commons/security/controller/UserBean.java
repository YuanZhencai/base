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
import com.wcs.base.controller.ConversationBaseBean;
import com.wcs.commons.security.model.User;
import com.wcs.commons.security.service.UserService;

/**
 * 
 * @author Chris Guan
 */
@ManagedBean
@ViewScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(UserBean.class);

    private Map<String, Object> queryMap = Maps.newHashMapWithExpectedSize(4);; // 查询条件Map封装
    private LazyDataModel<User> lazyModel;
    

    @Inject
    private UserService userService;

    @PostConstruct
    public void initLazyModel() {
        list();
    }

    /**
     * 查询用户
     * @return
     */
    public void list() {
        lazyModel = userService.findUsers(queryMap);
    }

    public LazyDataModel<User> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<User> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Map<String, Object> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }

}
