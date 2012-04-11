package com.wcs.security.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.wcs.base.service.StatelessEntityService;
import com.wcs.security.model.Resource;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: ResourceService.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright .All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@SuppressWarnings("serial")
@Stateless
public class ResourceService implements Serializable{
	@Inject
    StatelessEntityService entityService;
	
	public List<Resource> findAllSysResource(){
	    String sql = "SELECT rs FROM Resource rs  ORDER BY rs.number";
        return this.entityService.findList(sql);
	}
}
