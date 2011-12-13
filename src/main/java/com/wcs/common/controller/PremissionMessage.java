package com.wcs.common.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
/**
 * 
 * <p>Project: cmdpms</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2011 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chenlong@wcs-global.com">chenlong</a>
 */
@Named
public class PremissionMessage implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addMessage(String clientId, String summary, String detail,
			FacesMessage.Severity servertity) {

		FacesContext context = FacesContext.getCurrentInstance();

		FacesMessage message = new FacesMessage();
		message.setSeverity(servertity);
		message.setDetail(detail);
		message.setSummary(summary);
		if (servertity == FacesMessage.SEVERITY_ERROR) {
			context.validationFailed();
		}
		context.addMessage(clientId, message);
	}

	/**
	 * 
	 * <p>Description:成功提示！ </p>
	 * @param clientId
	 * @param summary
	 */
	public void addSuccessMessage(String clientId, String summary) {
		this.addMessage(clientId, summary, FacesMessage.SEVERITY_INFO);
	}

	public void addMessage(String clientId, String summary,
			FacesMessage.Severity servertity) {
		this.addMessage(clientId, summary, null, servertity);
	}
	
	/**
     * 
     * <p>Description: 错误提示！</p>
     * @param clientId 组建ID
     * @param summary 消息
     */
    public void addErrorMessage(String clientId, String summary) {
        this.addMessage(clientId, summary, FacesMessage.SEVERITY_ERROR);
     }
}
