/**
 * ResourceBean.java
 * Created: 2011-7-26 下午08:36:54
 */
package com.wcs.common.controller.permissions;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import com.wcs.base.controller.ConversationBaseBean;
import com.wcs.common.model.Resource;
import com.wcs.common.service.permissions.ResourceService;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: ResourceBean.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright .All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@SuppressWarnings("serial")
@Named
@ConversationScoped
public class ResourceBean extends ConversationBaseBean<Resource> {  
    @EJB
    private ResourceService resourceService;
    
    private String searchResourceName;
    private LazyDataModel<Resource> lazyModel;
    private Resource selectedResource; 

    public ResourceBean() {}
    
    /**
     * 根据条件查询资源
     */
    public void searchResource() {
        this.lazyModel = this.resourceService.searchResource(searchResourceName);
    }


    public String getSearchResourceName() {
        return searchResourceName;
    }

    public void setSearchResourceName(String searchResourceName) {
        this.searchResourceName = searchResourceName;
    }

    public LazyDataModel<Resource> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Resource> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Resource getSelectedResource() {
        return selectedResource;
    }

    public void setSelectedResource(Resource selectedResource) {
        this.selectedResource = selectedResource;
    }
}
