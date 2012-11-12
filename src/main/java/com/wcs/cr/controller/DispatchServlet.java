package com.wcs.cr.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.wcs.base.util.StringUtils;

/**
 * Servlet implementation class DispatchServlet
 */
public class DispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Subject currentUser = SecurityUtils.getSubject();
		String rptUserId = null;
		String rptName = null;
		if (currentUser.getSession().getAttribute("rptUserId") != null) {
			rptUserId = String.valueOf(currentUser.getSession().getAttribute("rptUserId"));
		}
		if (currentUser.getSession().getAttribute("rptName") != null) {
			rptName = String.valueOf(currentUser.getSession().getAttribute("rptName"));
		}
		if (StringUtils.hasText(rptUserId) && StringUtils.hasText(rptName)) {
			response.sendRedirect("./crfaces/input.xhtml");
		} else {
			response.sendRedirect("./404.xhtml");
		}
	}

}
