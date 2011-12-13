/**
 * ResourceBean.java
 * Created: 2011-7-26 下午08:36:54
 */
package com.wcs.common.controller.permissions;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Singleton;

import com.wcs.common.model.Resource;
import com.wcs.common.service.permissions.ResourceService;
import com.wcs.common.service.permissions.RoleService;

/**
 * 
 * <p>Project: cmdpms</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yourname@wcs-global.com">Your Name</a>
 */
@ManagedBean
@Singleton
public class ResourceBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private RoleService rolseResource;
    @EJB
    private ResourceService resourceService;

    /** Top资源菜单 */
    private List<Resource> topResource = new CopyOnWriteArrayList<Resource>();
    /** 系统全部资源 */
    private List<Resource> sysResouce = new CopyOnWriteArrayList<Resource>();

    public ResourceBean() {

    }

    @PostConstruct
    private void initTopResource() {
        if (topResource.size() == 0) {
            List<Resource> resList = rolseResource.getSysTopResource();
            Collections.sort(resList);
            this.topResource.addAll(resList);
        }
    }

    /**
     * @return the topResource
     */
    public List<Resource> getTopResource() {
        return topResource;
    }

    /**
     * @param topResource
     *            the topResource to set
     */
    public void setTopResource(List<Resource> topResource) {
        this.topResource = topResource;
    }

    /**
     * @return the sysResouce
     */
    public List<Resource> getSysResouce() {
        if (sysResouce.size() == 0) {
            sysResouce.addAll(resourceService.findAllSysResource());
        }
        return sysResouce;
    }

    /**
     * @param sysResouce
     *            the sysResouce to set
     */
    public void setSysResouce(List<Resource> sysResouce) {
        this.sysResouce = sysResouce;
    }

}
