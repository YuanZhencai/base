/**
 * MyShrioCasFilter.java
 * Created: 2012-1-11 下午03:23:55
 */
package com.wcs.base.security.service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;


/**
 * <p>Project: btcbase</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:chengchao@wcs-global.com">ChengChao</a>
 */
public class BaseShrioCasFilter implements Filter {

	private Logger logger = Logger.getLogger(BaseShrioCasFilter.class.getName());
	
	public static final String NOT_APP_USER_ERROR_PAGE = "/btcbase/error.jsp";
	
	private final String DEFAULT_PASSWORD = "";

	
	@EJB
	private LoginService loginService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		logger.log(Level.INFO, BaseShrioCasFilter.class.getName()+" doFilter：" + ((HttpServletRequest) request).getUserPrincipal());
		Object userPrincipal = ((HttpServletRequest) request).getUserPrincipal();
		String userId = userPrincipal == null ? null : userPrincipal.toString();
		//TODO NO CASE 
//		userId = "wilmar_cas";
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		if (session.getAttribute(LoginService.SESSION_KEY_CURRENTUSR) == null && userId != null) {
			session.setAttribute(LoginService.SESSION_KEY_CURRENTUSR, doLogin(userId,response));
		}
		logger.log(Level.INFO, "session:" + session.getId() + ",currentUser is " + session.getAttribute(LoginService.SESSION_KEY_CURRENTUSR));
		filterChain.doFilter(request, response);
	}
	
	private Subject doLogin(String userId,ServletResponse response) throws IOException {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (userId == null) {// not login
				logger.log(Level.INFO, BaseShrioCasFilter.class.getName() + " currentUser.logout()");
				currentUser.logout();
				return null;
			} else {
				logger.log(Level.INFO, "MyShrioCasFilter currentUser.login(" + userId + ")");
				currentUser.login(new UsernamePasswordToken(userId, DEFAULT_PASSWORD));
				// judge is btcbase user?
				if (loginService.isAppUser(userId)) {
					logger.log(Level.INFO, userId + " is validated user for the app!");
					return currentUser;
				} else {
					logger.log(Level.INFO, userId + " is invalidated user for the app!");
					((HttpServletResponse) response).sendRedirect(NOT_APP_USER_ERROR_PAGE);
					return null;
				}
				// }
			}
		} catch (UnknownAccountException uae) {
			uae.printStackTrace();
		} catch (IncorrectCredentialsException ice) {
			ice.printStackTrace();
		} catch (LockedAccountException lae) {
			lae.printStackTrace();
		} catch (ExcessiveAttemptsException eae) {
			eae.printStackTrace();
		} catch (AuthenticationException ae) {
			// unexpected error?
			ae.printStackTrace();
		}
		return null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
