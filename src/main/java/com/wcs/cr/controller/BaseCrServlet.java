package com.wcs.cr.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

/**
 * Servlet implementation class HelloCrServlet
 */
public class BaseCrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BaseCrServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reportName = request.getParameter("rpt");//"HelloWorldRpt.rpt";
		String userName = request.getParameter("usr");
		String password = request.getParameter("pwd");
		if (reportName == null || "".equals(reportName.trim())) {
			System.out.println("param rpt is empty!");
			reportName = "";
		}
		ReportClientDocument reportClientDoc = new ReportClientDocument();
		try {
			reportClientDoc.open(reportName, 0);
			if (userName != null) {
				reportClientDoc.getDatabaseController().logon(userName,
						password);
			}
			request.getSession().setAttribute("reportName", reportName);
			request.getSession().setAttribute("reportSource",reportClientDoc.getReportSource());
			
			System.out.println("=========loading cr reportName:"+request.getSession().getAttribute("reportName"));
			
			// Object reportSource = session.getAttribute("reportSource");
			// reportClientDoc.getDatabaseController().logon(USERNAME, PASSWORD);
//			CrystalReportViewer viewer = new CrystalReportViewer();
//			viewer.setOwnPage(true);
//			viewer.setOwnForm(true);
//			viewer.setPrintMode(CrPrintMode.ACTIVEX);
//			viewer.setReportSource(reportClientDoc.getReportSource());
//			response.setContentType("text/html");
//	        response.setBufferSize(8192);
//			viewer.processHttpRequest(request, response, this.getServletConfig().getServletContext(), response.getWriter());
			response.sendRedirect("basecrviewer.jsp");
		} catch (ReportSDKException e) {
			e.printStackTrace();
		} catch (ReportSDKExceptionBase e) {
			e.printStackTrace();
		}
	}

}
