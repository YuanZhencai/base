package com.wcs.demo;

import com.wcs.common.model.Dict;
import com.wcs.common.service.CommonService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "internationalBean")
@ViewScoped
public class InternationalBeanTest {

	@EJB
    CommonService common;

    private List<SelectItem> cnMenuItem = new ArrayList<SelectItem>();
    private List<SelectItem> enMenuItem = new ArrayList<SelectItem>();

    private String mess;

	public InternationalBeanTest() {

	}

    @PostConstruct
    public void init(){

        //获取中文列表
        List<Dict> cnDictList = common.getDictByCat("TIH.TAX.SEX","zh_CN");

        //根据需求，将需要的信息存储在List中，这里取得是dict中的codeKey,codeVal
        for(Dict dict:cnDictList){
            cnMenuItem.add(new SelectItem(dict.getCodeKey(),dict.getCodeVal()));
        }


        //获取英文列表
        List<Dict> enDictList = common.getDictByCat("TIH.TAX.SEX","en_US");

        //根据需求，将需要的信息存储在List中，这里取得是dict中的codeKey,codeVal
        for(Dict dict:enDictList){
            enMenuItem.add(new SelectItem(dict.getCodeKey(),dict.getCodeVal()));
        }

        //取message国际化文件中的信息
        mess = common.getMessage("btn_save");

    }



    public List<SelectItem> getEnMenuItem() {
        return enMenuItem;
    }

    public void setEnMenuItem(List<SelectItem> enMenuItem) {
        this.enMenuItem = enMenuItem;
    }

    public List<SelectItem> getCnMenuItem() {
        return cnMenuItem;
    }

    public void setCnMenuItem(List<SelectItem> cnMenuItem) {
        this.cnMenuItem = cnMenuItem;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

}
