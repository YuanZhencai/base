package com.wcs.common.controller;

import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.wcs.common.model.Dict;
import com.wcs.common.service.CommonService;

@ManagedBean(name="commonBean")
@ApplicationScoped
public class CommonBean {
	@EJB CommonService commonService;
	
	//构造方法
	//这个托管bean主要是对字典表(DICT)的读取
	public CommonBean(){
	}
	
	//这里是以CodeCat()+"."+CodeKey()来获取相应的value值.
	//请参照数据库的字段.通过这种方法获取Value.
	public String getValueByDictCatKey(String cat_point_key){
		Locale browserLang=FacesContext.getCurrentInstance().getViewRoot().getLocale();
		//这里的cat_point_key的值是DictConsts下的常量名称.请注意.而非数据库的值.
		//下面重新组合参数.
		String cat_point_key_lang=cat_point_key+"_"+browserLang.toString();
		return commonService.getValueByDictCatKey(cat_point_key_lang);
	}
	
	//这是通过一个数据库的Code_Cat字段来获取对应的list集合,常用于下拉框.
	public List<Dict> getDictByCat(String cat) {
		Locale browserLang=FacesContext.getCurrentInstance().getViewRoot().getLocale();
		return commonService.getDictByCat(cat,browserLang.toString());
	}
	
	//刷新调用的方法
	public void refreshDictData(){
		commonService.queryDict();
	}
	
}
