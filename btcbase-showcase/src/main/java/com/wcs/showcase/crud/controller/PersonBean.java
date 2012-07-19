package com.wcs.showcase.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.wcs.base.controller.ViewBaseBean;
import com.wcs.base.dict.service.DictService;
import com.wcs.showcase.crud.model.Person;
import com.wcs.showcase.crud.service.PersonService;

/** 
* <p>Project: btcbase</p> 
* <p>Title: PersonBean.java</p> 
* <p>Description: </p> 
* <p>Copyright: Copyright 2011.All rights reserved.</p> 
* <p>Company: wcs.com</p> 
* @author <a href="mailto:yangshiyun@wcs-global.com">Yang Shiyun</a> 
*/

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class PersonBean extends ViewBaseBean<Person> {

	@Inject
	private PersonService personService;
	@Inject
	private DictService dictService;
	
	private Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(4);
	private LazyDataModel<Person> lazyModel;           // 动态分页使用
	private List<Person> personList = new ArrayList<Person>();
	private boolean editMode;        					// 是否修改
	private List<SelectItem> sexList = new ArrayList<SelectItem>();        // 性别下拉框
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 构造函数
	 */
	public PersonBean() {
		this.listPage = "/faces/person/list.xhtml";
		this.inputPage = "/faces/person/input.xhtml";
	}
	
	/**
	 * 构造方法执行后会自动执行此方法
	 */
	@SuppressWarnings("unused")
	@PostConstruct
	private void postConstruct() {
		this.setSexList(dictService.findWithSelectItem("SEX"));
    	this.search();	
	}
	
	/**
	 * 查找person
     */
    public void search() {
    	lazyModel = personService.findModelByMap(filterMap);
    }
    
    /**
     * 跳转到输入页面
     */
    public String input() {
    	return this.inputPage;
    }

    //-------------------- setter & getter --------------------//
    
	public Map<String, Object> getFilterMap() {
		return filterMap;
	}

	public void setFilterMap(Map<String, Object> filterMap) {
		this.filterMap = filterMap;
	}

	public LazyDataModel<Person> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Person> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public List<SelectItem> getSexList() {
		return sexList;
	}

	public void setSexList(List<SelectItem> sexList) {
		this.sexList = sexList;
	}

	public DictService getDictService() {
		return dictService;
	}

	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}

}