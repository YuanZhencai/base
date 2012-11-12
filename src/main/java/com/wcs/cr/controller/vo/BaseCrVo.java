/**
 * 
 */
package com.wcs.cr.controller.vo;

import java.io.Serializable;

import com.crystaldecisions.reports.sdk.ReportClientDocument;

/**
 * @author chengchao
 *
 */
public class BaseCrVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private ReportClientDocument reportClientDoc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReportClientDocument getReportClientDoc() {
		return reportClientDoc;
	}

	public void setReportClientDoc(ReportClientDocument reportClientDoc) {
		this.reportClientDoc = reportClientDoc;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
