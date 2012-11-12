package com.wcs.cr.service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.wcs.base.util.StringUtils;

/**
 * Servlet Filter implementation class BaseCrFilter
 */
public class BaseCrFilter implements Filter {

	private Logger logger = Logger.getLogger(BaseCrFilter.class.getName());
	
    /**
     * Default constructor. 
     */
    public BaseCrFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String userId = req.getParameter("user");
		String rptName = req.getParameter("rpt");
		
		Subject currentUser = SecurityUtils.getSubject();
		if (StringUtils.hasText(userId)) {
			currentUser.getSession().setAttribute("rptUserId", userId);
			logger.log(Level.INFO, "Report user: {0} has been put into session", new Object[]{userId});
		}
		if (StringUtils.hasText(rptName)) {
			currentUser.getSession().setAttribute("rptName", rptName);
			logger.log(Level.INFO, "Report: {0} has been put into session", new Object[]{rptName});
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
