package com.wcs.demo;

import sun.security.validator.ValidatorException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name = "validateBean")
@ViewScoped
public class ValidateBeanTest {

    private String validateStr1;
    private String validateStr2;
    private String validateStr3;
    private String validateStr4;

    public ValidateBeanTest() {

    }

    /**
     * 验证validateStr3是否符合预期
     *
     * @param context
     * @param componentToValidate
     * @param value
     */
    public void validInput(FacesContext context, UIComponent componentToValidate, Object value) throws ValidatorException {

        if (null == value) {
            context.addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR,"validateStr3输入不能为空（指定自定义验证的方法）",""));
        }

        int length = value.toString().length();

        if (3 > length || length > 6) {
            context.addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR,"validateStr3输入参数在2-6字符内（指定自定义验证的方法）",""));
        }

    }

    public String testInput(){
        return null;
    }

    public String getValidateStr1() {
        return validateStr1;
    }

    public void setValidateStr1(String validateStr1) {
        this.validateStr1 = validateStr1;
    }

    public String getValidateStr2() {
        return validateStr2;
    }

    public void setValidateStr2(String validateStr2) {
        this.validateStr2 = validateStr2;
    }

    public String getValidateStr3() {
        return validateStr3;
    }

    public void setValidateStr3(String validateStr3) {
        this.validateStr3 = validateStr3;
    }

    public String getValidateStr4() {
        return validateStr4;
    }

    public void setValidateStr4(String validateStr4) {
        this.validateStr4 = validateStr4;
    }
}
