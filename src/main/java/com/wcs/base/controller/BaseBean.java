package com.wcs.base.controller;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wcs.base.conf.SystemConfiguration;
import com.wcs.base.entity.BaseEntity;
import com.wcs.base.service.StatelessEntityService;
import com.wcs.base.util.ReflectionUtils;
import com.wcs.common.controller.permissions.LoginBean;

public class BaseBean<T extends BaseEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected String inputPage = null;
	protected String listPage = null;
	protected String viewPage = null;
    
    //分页选择模板
    private String rowsPerPageTemplate;

    @Inject
    protected LoginBean loginBean;
	@Inject
	protected StatelessEntityService entityService;
	private Long id;
	private T instance;        //currentEntity
    
	/**
     *
     * @param listPage  如果为null，则不改变LIST页面
     * @param inputPage 如果为null，则不改变INPUT页面
     * @param viewPage  如果为null，则不改变VIEW页面
     */
    public void setupPage(String listPage, String inputPage, String viewPage){
        if (!StringUtils.isEmpty(listPage)) this.listPage = listPage;
        if (!StringUtils.isEmpty(inputPage)) this.inputPage = inputPage;
        if (!StringUtils.isEmpty(viewPage)) this.viewPage = viewPage;
    }

	protected void initInstance() {
		if (instance == null) {
			if (id != null) {
				instance = loadInstance();
			} else {
				instance = createInstance();
			}
		}
	}


	public T loadInstance() {
		return entityService.findUnique(getClassType(), getId());
	}

	public T createInstance() {
		try {
			return getClassType().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断实体 instance 的id是否有值 
	 * @return
	 */
	public boolean idIsEmpty() {
		return getInstance().getId() != null && getInstance().getId()>0l;//修改人：liaowei
	}
	
	/**
	 * 判断 instance 是否处于EJB容器托管状态
	 * @return
	 */
	public boolean isManaged() {
		return entityService.isManaged(getInstance());
	}

	public void saveEntity() {
		if (idIsEmpty()) {
			this.instance.setUpdatedBy(loginBean.getUser().getName());
			entityService.update(getInstance());
		} else {
			if(loginBean.getUser() != null){
				this.instance.setCreatedBy(loginBean.getUser().getName());
			}
			this.getInstance().setId(null);//修改人：liaowei
			entityService.create(getInstance());
			//Validate.isTrue(entityService.isManaged(getInstance()));
		}
	}
	
	public void deleteEntity() {
        T entity = getInstance();
        entity.setDefunctInd(true);
		entityService.update(entity);
	}
	
	private Class<T> getClassType() {
		return ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	//----------------------------- set & get --------------------------------//

	public T getInstance() {
		initInstance();
		return instance;
	}
	
    public void setInstance(T instance) {
        this.instance = instance;
    } 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public String getInputPage() {
        return inputPage;
    }

    public void setInputPage(String inputPage) {
        this.inputPage = inputPage;
    }

    public String getListPage() {
        return listPage;
    }

    public void setListPage(String listPage) {
        this.listPage = listPage;
    }

    public String getViewPage() {
        return viewPage;
    }

    public void setViewPage(String viewPage) {
        this.viewPage = viewPage;
    }

	public String getRowsPerPageTemplate() {
		return SystemConfiguration.ROWS_PER_PAGE_TEMPLATE;
	}
}
