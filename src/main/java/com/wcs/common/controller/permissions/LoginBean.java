package com.wcs.common.controller.permissions;

import com.wcs.base.conf.Constants;
import com.wcs.base.util.BaseUtils;
import com.wcs.base.util.CollectionUtils;
import com.wcs.base.util.JSFUtils;
import com.wcs.common.model.Resource;
import com.wcs.common.model.User;
import com.wcs.common.service.permissions.LoginService;
import com.wcs.common.service.permissions.ResourceService;
import com.wcs.common.service.permissions.UserService;
import com.wcs.common.util.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>Project: btcbase</p> 
 * <p>Title: LoginBean.java</p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright 2005-2011.All rights reserved.</p> 
 * <p>Company: wcs.com</p> 
 * @author <a href="mailto:yujingu@wcs-gloabl.com">Yu JinGu</a>
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	final Logger logger = LoggerFactory.getLogger(LoginBean.class);

    @Inject
    LoginService loginService;

	private List<Resource> allUserResources = new ArrayList<Resource>(); 				// 用户资源
	private boolean isManager = false; 											// 是否是管理员

	private static final String LOGIN_SUCCESS = "/template/template.xhtml";

	public LoginBean() {
        logger.info("Login => construct()");
	}

	/**
	 * 用户登录验证
	 * @return
	 */
	public String userLogin() {
        String userName = JSFUtils.getRequestParam("userName") ;

		User user = loginService.findUniqueUser(userName);
		if (user == null) {
			MessageUtils.addErrorMessage("longmessgeId", "用户无效，请检查！");
			return Constants.FAILURED;
		}

		// 用户资源初始化
		try {
            this.allUserResources = loginService.loadResourceByUser(user.getId());

			if (CollectionUtils.isEmpty( allUserResources)) {
				MessageUtils.addErrorMessage("longmessgeId", "用户没有权限，请检查！");
				return Constants.FAILURED;
			}
		} catch (Exception e) {
            e.printStackTrace();
			logger.info("用户资源初始化失败！");
			MessageUtils.addErrorMessage("longmessgeId", "用户资源初始化失败！");
			return Constants.FAILURED;
		}

        this.isManager=  loginService.isAdmin(user.getId());
        JSFUtils.getSession().put("loginName",user.getUserName());
        JSFUtils.getSession().put("userName",user.getName());
        JSFUtils.getSession().put("selectId",1);  //选中的菜单

		return Constants.SUCCESS;
	}
	
	/**
	 * 注销用户
	 */
	public String doLogout() {
		// 关闭Session
		HttpSession session = BaseUtils.getSession();
		session.invalidate();
		
		return Constants.SUCCESS;
	}

	/**
	 * 改变导航菜单
	 *
	 * @param selectId
	 * @return
	 */
	public String changeMenu(Long selectId) {
        JSFUtils.getSession().put("selectId",selectId);
		//this.selectId = selectId;
		return LOGIN_SUCCESS;
	}

	/**
	 *  资源权限判断
	 *
	 * @return
	 */
	public boolean contains(String id) {
		String uri = JSFUtils.getViewId();

        Resource urlResource = null;
        for (Resource r : this.allUserResources){
            if ( r.getUri().equals(uri) ){
                  urlResource = r;
                break;
            }
        }
        if (urlResource==null)  return false;

        for (Resource r : this.allUserResources){
            if (r.getId().equals(urlResource.getId())){
                return true;
            }
        }

        return false;
	}

	// ----------------------------- set & get -------------------------------//

	public List<Resource> getAllUserResources() {
		return allUserResources;
	}

	public void setAllUserResources(CopyOnWriteArrayList<Resource> allUserResources) {
		this.allUserResources = allUserResources;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

}
