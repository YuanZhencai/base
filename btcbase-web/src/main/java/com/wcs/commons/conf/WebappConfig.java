package com.wcs.commons.conf;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.wcs.commons.security.service.ResourceCache;
import com.wcs.commons.security.vo.ResourceTree;

/**
 * 
 * @author Chris Guan
 */
@ManagedBean(name="config", eager=true)
@ApplicationScoped
public class WebappConfig {

	public final static String RES_TREE = "resTree";
	public final static String NEXT_DISPLAY_RES_CODE = "nextDisplayResCode";
	public final static String NEXT_DISPLAY_RES_PARENT_CODES = "nextDisplayParentResCodes";
	
	private Map<String, Object> appMap;
	
	private ResourceTree tree = null;
	
	@EJB
	ResourceCache resourceCache;
	
	@SuppressWarnings("unused")
	@PostConstruct
	private void init(){
		initResTree();
	}

	public void initResTree() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext ec = context.getExternalContext();
		this.appMap = ec.getApplicationMap();
		
		tree = resourceCache.getTree();
		
		appMap.put(WebappConfig.RES_TREE, tree);
	}
	

	public Map<String, Object> getAppMap() {
		return appMap;
	}
	
}
