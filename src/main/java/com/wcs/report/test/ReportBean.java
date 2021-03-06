package com.wcs.report.test;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import com.wcs.report.controller.ReportBase;




/**
 * 
 * <p>Project: ncp</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2012 Wilmar Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:yourname@wcs-global.com">Yan Song</a>
 */
@ManagedBean(name = "testReport") 
@SessionScoped

public class ReportBean extends ReportBase  implements Serializable
{

	private static final long serialVersionUID = 1L;

	
	private String exportType;
	
	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}


	public void testReportPage(ActionEvent actionEvent) {
    	String a= "a";
    	try {
        	String fileStoreLocation = "WebappReport.jrxml";
            if (fileStoreLocation == null || fileStoreLocation.equals("")) { return; }
            String jasper = fileStoreLocation.replace("jrxml", "jasper");
            String printFile = fileStoreLocation.replace("jrxml", "jrprint");
            super.setupPage(fileStoreLocation, jasper, printFile);
            super.genneralReport(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testReportPageNext(ActionEvent actionEvent) {
		pageValue = String.valueOf(Integer.parseInt(this.pageValue) + 1);
    	String a= "a";
    	try {
    		genneralReport(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testReportPagePre(ActionEvent actionEvent) {
		pageValue = String.valueOf(Integer.parseInt(this.pageValue) - 1);
    	String a= "a";
    	try {
			genneralReport(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testReportExport(ActionEvent actionEvent) {
        try {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
                    .getResponse();
            String type =  exportType;
            if(type != null ){
                String exportMethod = type.toLowerCase();
                if(exportMethod.contains("pdf")){
                    this.exportToPdf(response);
                }else if(exportMethod.contains("excel")){
                    this.exportToExcel(response);
                }else if(exportMethod.contains("html")){
                    this.exportToXHtml(response);
                }else if(exportMethod.contains("word")){
                    this.exportToDocx(response);
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
}

