package com.wcs.commons.dict.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.google.common.collect.Lists;
import com.wcs.commons.dict.model.Dict;
import com.wcs.commons.dict.service.DictService;

/**
 * 系统代码表
 * 1. 通过 category, key 能获得想要的代码值
 * 2. 通过 category 装载某一类代码值，eg. 下拉列表需要的键值对
 * 
 * @author Chris Guan
 */

@ManagedBean
@ApplicationScoped
public class DictBean implements Serializable {
    private static final long serialVersionUID = 1L;
  
    @EJB
    DictService dictService;
    
    /**
     * 通过 category 来获取对应的  List<dict> 集合,常用于下拉框.
     */
	public List<Dict> getDicts(String category) {
		Locale browserLang=FacesContext.getCurrentInstance().getViewRoot().getLocale();
		return dictService.loadDicts(category, browserLang.toString());
	}
	
	/**
     * 
     * 通过 category 来获取对应的  List<SelectItem> 集合,用于下拉框.
     */
    public List<SelectItem> getItems(String category) {
    	List<Dict> dicts = this.getDicts(category);

    	List<SelectItem> items = Lists.newArrayList();
        for (Dict d : dicts) {
            items.add(new SelectItem(d.getKey(), d.getValue()));
        }
        return items;
    }

    /**
     * 通过 category，key 来获取相应的dict
     */
    public Dict getDict(String category,String key) {
        Locale browserLang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return dictService.loadDict(category, browserLang.toString(), key);
    }
    
    /**
     * 通过 category，key 来获取相应的dict 的 value
     */
    public String getDictValue(String category,String key) {
        return this.getDict(category, key).getValue();
    }
}
